import requests
import csv
from bs4 import BeautifulSoup

# Remplacez 'YOUR_API_KEY_HERE' avec votre clé API de NewsAPI
API_KEY = '8c305ed3a1e641d7ab725fc13135248f'

def fetch_articles(theme):
    url = f"https://newsapi.org/v2/everything?q={theme}&apiKey={API_KEY}"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()['articles']
    else:
        print("Erreur lors de la récupération des articles")
        return []
    
def fetch_full_article_text(url):
    try:
        response = requests.get(url, headers={'User-Agent': 'Mozilla/5.0'})
        if response.status_code == 200:
            soup = BeautifulSoup(response.content, 'html.parser')
            # Chercher d'abord dans les balises <article>
            article_body = soup.find('article')
            if not article_body:
                # Si aucune balise <article>, chercher dans les <div> qui contiennent souvent le contenu
                article_body = soup.find('div', attrs={'class': 'article-content'})  # Exemple de classe; à adapter
            if article_body:
                paragraphs = article_body.find_all('p')
                article_text = '\n'.join(paragraph.text for paragraph in paragraphs)
                return article_text.strip()
            else:
                return "Contenu de l'article non trouvé"
        else:
            return "Erreur lors de l'accès à l'article"
    except Exception as e:
        return f"Erreur lors du fetching de l'article: {e}"
    

def save_articles_to_csv(articles, filename='Bot_recherche_stockage/articles_data.csv'):
    with open(filename, mode='w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(['Title', 'Author', 'Description', 'URL', 'Full Text'])
        for article in articles:
            full_text = fetch_full_article_text(article['url'])
            writer.writerow([article['title'], article['author'], article['description'], article['url'], full_text])


def main():
    theme = "La corse"
    articles = fetch_articles(theme)
    if articles:
        save_articles_to_csv(articles)
        print(f"{len(articles)} articles sauvegardés dans le fichier CSV.")
    else:
        print("Aucun article trouvé.")

if __name__ == "__main__":
    main()

