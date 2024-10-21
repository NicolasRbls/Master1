import pandas as pd
import matplotlib.pyplot as plt
import geopandas as gpd
from shapely.geometry import Point
from mpl_toolkits.axes_grid1 import make_axes_locatable

# Charger les données depuis ton fichier CSV (assure-toi que ton chemin est correct)
file_path = 'csv-lang-fr-and-refine-region-3a-2294-22-and-timezone-europe-2fberlin-and-use_la.csv'
data = pd.read_csv(file_path, delimiter=';')

# Extraire les colonnes nécessaires et traiter les données
data_filtered = data[['Libellé commune ou ARM', 'Chômeurs 15-64 ans en 2012', 'coordonnees']].dropna()
data_filtered[['latitude', 'longitude']] = data_filtered['coordonnees'].str.split(',', expand=True)
data_filtered['latitude'] = data_filtered['latitude'].astype(float)
data_filtered['longitude'] = data_filtered['longitude'].astype(float)

# Créer des points géométriques à partir des coordonnées
data_filtered['geometry'] = data_filtered.apply(lambda row: Point(row['longitude'], row['latitude']), axis=1)
gdf = gpd.GeoDataFrame(data_filtered, geometry='geometry')

# Charger le fichier Shapefile téléchargé depuis Natural Earth Data
shapefile_path = 'C:/Users/nicol/Downloads/ne_110m_admin_0_countries/ne_110m_admin_0_countries.shp'
world = gpd.read_file(shapefile_path)

# Re-projeter le shapefile en EPSG:3857 (Mercator)
world = world.to_crs(epsg=3857)

# Créer un point pour centrer sur la Corse
center_corsica = gpd.GeoSeries([Point(8.5, 42)], crs='EPSG:4326').to_crs(epsg=3857)

# Créer un buffer plus petit autour de la Corse pour zoomer davantage (réduit à 250 km)
corsica_bounds = center_corsica.buffer(180000)  # 250 km autour du centre

# Filtrer pour avoir uniquement la Corse
corsica = world[world.intersects(corsica_bounds.geometry[0])]

# Re-projeter les données de chômage en EPSG:3857
gdf = gdf.set_crs(epsg=4326)
gdf = gdf.to_crs(epsg=3857)

# Vérification des valeurs de chômage (pour éviter des valeurs anormalement élevées ou basses)
if gdf['Chômeurs 15-64 ans en 2012'].isnull().any() or (gdf['Chômeurs 15-64 ans en 2012'] <= 0).any():
    print("Attention : certaines valeurs de chômage sont nulles ou incorrectes.")

# Création de la carte
fig, ax = plt.subplots(figsize=(10, 10))
corsica.boundary.plot(ax=ax, linewidth=1)  # Afficher les frontières de la Corse

# Superposer la heatmap du chômage
scatter = ax.scatter(gdf['geometry'].x, gdf['geometry'].y, c=gdf['Chômeurs 15-64 ans en 2012'], cmap='hot', s=100, edgecolor='k', alpha=0.75)

# Ajuster les limites de la carte pour centrer sur la Corse
ax.set_xlim([corsica_bounds.total_bounds[0], corsica_bounds.total_bounds[2]])
ax.set_ylim([corsica_bounds.total_bounds[1], corsica_bounds.total_bounds[3]])

# Ajouter une barre de couleur pour indiquer l'échelle du chômage
divider = make_axes_locatable(ax)
cax = divider.append_axes("right", size="5%", pad=0.1)
plt.colorbar(scatter, cax=cax, label='Nombre de chômeurs')

# Ajouter des titres et labels
ax.set_title('Carte de Corse - Nombre de Chômeurs par Commune')
ax.set_xlabel('Longitude')
ax.set_ylabel('Latitude')

# Afficher la carte
plt.show()
