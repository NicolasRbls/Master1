from flask import Flask, render_template, request, redirect, url_for, session, flash
from flask_mysqldb import MySQL
from werkzeug.security import check_password_hash, generate_password_hash
import MySQLdb.cursors
import re

app = Flask(__name__)
app.secret_key = 'ma_super_cle_secrete_1234'

# Configuration de la base de données
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = '2024_M1'

mysql = MySQL(app)

# Route pour la page de login
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        # Récupérer les informations fournies par l'utilisateur
        username = request.form['username']
        password = request.form['password']

        # Se connecter à la base de données et vérifier les informations
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM user WHERE user_login = %s', (username,))
        user = cursor.fetchone()

        # Vérification du mot de passe
        if user and check_password_hash(user['user_password'], password):
            # Créer une session utilisateur
            session['loggedin'] = True
            session['user_id'] = user['user_id']
            session['username'] = user['user_login']
            # Mettre à jour la date de connexion
            cursor.execute('UPDATE user SET user_date_login = NOW() WHERE user_id = %s', (user['user_id'],))
            mysql.connection.commit()
            return redirect(url_for('home'))
        else:
            flash('Identifiant ou mot de passe incorrect')

    return render_template('login.html')

# Route pour la page d'accueil (après connexion)
@app.route('/home')
def home():
    if 'loggedin' in session:
        # Renvoyer la variable username au template
        return render_template('home.html', username=session['username'])
    return redirect(url_for('login'))


# Ce code définit une route pour la déconnexion d'un utilisateur dans une application Flask. 
# Lorsqu'un utilisateur se déconnecte, les informations de session sont supprimées et un message de confirmation est affiché. 
# Enfin, l'utilisateur est redirigé vers la page de connexion.

@app.route('/logout')
def logout():
    session.pop('loggedin', None)
    session.pop('user_id', None)
    session.pop('username', None)
    flash('Vous êtes déconnecté')
    return redirect(url_for('login'))

if __name__ == '__main__':
    app.run(debug=True)
