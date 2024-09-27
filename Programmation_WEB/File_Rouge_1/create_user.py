from flask import Flask
from flask_mysqldb import MySQL
from werkzeug.security import generate_password_hash

# Création de l'application Flask
app = Flask(__name__)

# Configuration de la base de données
app.config['MYSQL_HOST'] = 'localhost'  # Hôte de la base de données
app.config['MYSQL_USER'] = 'root'       # Nom d'utilisateur pour la base de données
app.config['MYSQL_PASSWORD'] = ''        # Mot de passe pour la base de données
app.config['MYSQL_DB'] = '2024_M1'      # Nom de la base de données

# Initialisation de l'extension MySQL
mysql = MySQL(app)

def create_user(username, password, compte_id, email):
    # Hachage du mot de passe pour la sécurité
    hashed_password = generate_password_hash(password)
    
    # Création d'un curseur pour exécuter des requêtes SQL
    cursor = mysql.connection.cursor()
    
    # Exécution de la requête d'insertion pour ajouter un nouvel utilisateur
    cursor.execute('INSERT INTO user (user_login, user_password, user_compte_id, user_mail) VALUES (%s, %s, %s, %s)', (username, hashed_password, compte_id, email))
    
    # Validation des changements dans la base de données
    mysql.connection.commit()
    
    # Affichage d'un message de confirmation dans la console
    print(f"Utilisateur {username} créé avec succès.")

if __name__ == "__main__":
    # Création d'un nouvel utilisateur dans le contexte de l'application
    with app.app_context():
        create_user('test2', 'test2', 3, 'test2@example.com')