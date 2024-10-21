from flask import Flask
import requests
from bs4 import BeautifulSoup
import urllib.parse
import time
from flask_mysqldb import MySQL
import MySQLdb

app = Flask(__name__)

# Configuration de la base de données MySQL via phpMyAdmin (localhost)
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''  # Pas de mot de passe par défaut pour phpMyAdmin
app.config['MYSQL_DB'] = 'crawler_db'  # Base de données créée dans phpMyAdmin

mysql = MySQL(app)

# Fonction pour récupérer les liens spécifiques vers les livres, les catégories et les pages de pagination
def get_specific_links(url):
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"
    }
    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()

        soup = BeautifulSoup(response.content, 'html.parser')
        links = set()

        # Parcourir toutes les balises <a> avec un href
        for a_tag in soup.find_all('a', href=True):
            link = a_tag['href']
            # Convertir les liens relatifs en absolus
            full_link = urllib.parse.urljoin(url, link)

            # Filtrer les liens vers les pages des livres (qui contiennent /catalogue/), pages de catégories et de pagination
            if '/catalogue/' in full_link or 'page-' in full_link or 'category/books' in full_link:
                links.add(full_link)

        return links

    except requests.RequestException as e:
        print(f"Erreur lors de la récupération de la page {url}: {e}")
        return set()

# Fonction pour enregistrer les résultats dans la base de données MySQL
def save_to_database(url, num_links):
    cursor = mysql.connection.cursor()
    try:
        query = "INSERT INTO crawled_urls (url, num_links) VALUES (%s, %s)"
        cursor.execute(query, (url, num_links))
        mysql.connection.commit()
    except MySQLdb.Error as e:
        print(f"Erreur lors de l'insertion dans la base de données : {e}")
    finally:
        cursor.close()

# Fonction principale du crawler
def crawl(start_url, depth=10):  # Augmenter la profondeur d'exploration
    to_crawl = {start_url}
    crawled = set()

    depth_count = 0
    while to_crawl and depth_count < depth:
        url = to_crawl.pop()
        if url not in crawled:
            print(f"Exploring: {url}")

            # Obtenir uniquement les liens spécifiques vers les livres, la pagination et les catégories
            links = get_specific_links(url)

            # Enregistrer les résultats dans la base de données (URL + nombre de liens trouvés)
            save_to_database(url, len(links))

            # Ajouter les nouveaux liens à explorer
            to_crawl.update(links - crawled)

            # Marquer l'URL comme explorée
            crawled.add(url)

            # Attendre un peu avant la prochaine requête pour éviter de surcharger le serveur
            time.sleep(1)

        depth_count += 1

    print(f"Exploration terminée : {len(crawled)} pages explorées.")

# URL de départ (Books to Scrape)
start_url = "https://books.toscrape.com/"

# Lancer le crawler avec une profondeur de 10 pour explorer plus de pages
with app.app_context():
    crawl(start_url, depth=10)
