from flask import Flask
from flask_mysqldb import MySQL
from werkzeug.security import generate_password_hash

app = Flask(__name__)

# Configuration de la base de données
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = '2024_M1'

mysql = MySQL(app)

def create_user(username, password, compte_id, email):
    hashed_password = generate_password_hash(password)
    cursor = mysql.connection.cursor()
    cursor.execute('INSERT INTO user (user_login, user_password, user_compte_id, user_mail) VALUES (%s, %s, %s, %s)', (username, hashed_password, compte_id, email))
    mysql.connection.commit()
    print(f"Utilisateur {username} créé avec succès.")

if __name__ == "__main__":
    with app.app_context():
        create_user('test', 'test', 2, 'test@example.com')
