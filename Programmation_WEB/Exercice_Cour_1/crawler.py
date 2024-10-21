from flask import Flask
import requests
from bs4 import BeautifulSoup
import urllib.parse
import time
from flask_mysqldb import MySQL
import MySQLdb
import re
from collections import Counter
import json

app = Flask(__name__)

# Configuration de la base de données MySQL via phpMyAdmin (localhost)
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''  # Pas de mot de passe par défaut pour phpMyAdmin
app.config['MYSQL_DB'] = 'crawler_db'  # Base de données créée dans phpMyAdmin
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'
app.config['MYSQL_CHARSET'] = 'utf8mb4'


mysql = MySQL(app)

# Liste étendue de stopwords en français
french_stopwords = set([
    'le', 'la', 'les', 'de', 'des', 'du', 'un', 'une', 'et', 'à', 'au', 'aux', 'en', 'par', 'pour', 
    'avec', 'sur', 'sous', 'dans', 'que', 'qui', 'quoi', 'dont', 'ce', 'cet', 'cette', 'ces', 'l', 
    'd', 'm', 't', 'y', 'n', 'il', 'elle', 'ils', 'elles', 'nous', 'vous', 'on', 'ne', 'pas', 
    'plus', 'moins', 'ou', 'mais', 'donc', 'or', 'ni', 'car', 'se', 'sa', 'son', 'ses', 'être', 
    'avoir', 'faire', 'été', 'être', 'avant', 'après', 'entre', 'tout', 'comme', 'ici', 'cela'
])

# Fonction pour nettoyer et extraire les mots du texte
def clean_text(text):
    # Supprimer les caractères spéciaux, les chiffres, etc.
    text = re.sub(r'\W+', ' ', text)
    words = text.lower().split()  # Mettre en minuscule et diviser en mots
    
    # Filtrer les stopwords, les mots de moins de 3 caractères et les mots numériques
    words = [word for word in words if word not in french_stopwords and len(word) > 3 and not word.isdigit()]
    
    return words

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

        # Extraire le texte de la page
        page_text = soup.get_text(separator=' ')
        words = clean_text(page_text)

        # Compter les 10 mots les plus fréquents
        word_counts = Counter(words).most_common(10)

        # Créer un dictionnaire avec les 10 mots les plus fréquents
        word_dict = dict(word_counts)

        # Parcourir toutes les balises <a> avec un href
        for a_tag in soup.find_all('a', href=True):
            link = a_tag['href']
            # Convertir les liens relatifs en absolus
            full_link = urllib.parse.urljoin(url, link)

            # Filtrer les liens vers les pages des livres, pages de catégories et de pagination
            if '/catalogue/' in full_link or 'page-' in full_link or 'category/books' in full_link:
                links.add(full_link)

        return links, word_dict

    except requests.RequestException as e:
        print(f"Erreur lors de la récupération de la page {url}: {e}")
        return set(), {}

# Fonction pour enregistrer les résultats dans la base de données MySQL
def save_to_database(url, num_links, word_dict):
    cursor = mysql.connection.cursor()
    try:
        # Convertir le dictionnaire en JSON pour le stocker, désactiver l'échappement des caractères Unicode
        word_dict_json = json.dumps(word_dict, ensure_ascii=False)  # Désactiver ensure_ascii pour ne pas échapper les caractères

        query = "INSERT INTO crawled_urls (url, num_links, word_frequencies) VALUES (%s, %s, %s)"
        cursor.execute(query, (url, num_links, word_dict_json))
        mysql.connection.commit()
    except MySQLdb.Error as e:
        print(f"Erreur lors de l'insertion dans la base de données : {e}")
    finally:
        cursor.close()


# Fonction principale du crawler
def crawl(start_url, depth):  # Augmenter la profondeur d'exploration
    to_crawl = {start_url}
    crawled = set()

    depth_count = 0
    while to_crawl and depth_count < depth:
        url = to_crawl.pop()
        if url not in crawled:
            print(f"Exploring: {url}")

            # Obtenir les liens spécifiques et les mots les plus fréquents
            links, word_dict = get_specific_links(url)

            # Afficher le dictionnaire des 10 mots les plus fréquents
            print(f"Top 10 mots les plus fréquents pour {url}: {word_dict}")

            # Enregistrer les résultats dans la base de données (URL + nombre de liens trouvés + mots les plus fréquents)
            save_to_database(url, len(links), word_dict)

            # Ajouter les nouveaux liens à explorer
            to_crawl.update(links - crawled)

            # Marquer l'URL comme explorée
            crawled.add(url)

            # Attendre un peu avant la prochaine requête pour éviter de surcharger le serveur
            time.sleep(1)

        depth_count += 1

    print(f"Exploration terminée : {len(crawled)} pages explorées.")

# URL de départ (exemple pour L'Équipe)
start_url = "https://www.lequipe.fr/"

# Lancer le crawler avec une profondeur 
with app.app_context():
    crawl(start_url, depth=3)
