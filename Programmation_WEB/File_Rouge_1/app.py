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


#route pour la déconnexion d'un utilisateur 
@app.route('/logout')
def logout():
    # Supprimer les informations de session pour l'utilisateur
    session.pop('loggedin', None)  # Retirer l'état de connexion
    session.pop('user_id', None)    # Retirer l'ID de l'utilisateur
    session.pop('username', None)    # Retirer le nom d'utilisateur
    
    # Afficher un message de confirmation indiquant que l'utilisateur est déconnecté
    flash('Vous êtes déconnecté')
    
    # Rediriger l'utilisateur vers la page de connexion
    return redirect(url_for('login'))


@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        # Récupérer les informations fournies par l'utilisateur via le formulaire
        username = request.form['username']
        password = request.form['password']
        email = request.form['email']
        
        # Vérifier si l'utilisateur existe déjà dans la base de données
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM user WHERE user_login = %s', (username,))
        account = cursor.fetchone()

        # Vérifier si l'adresse email existe déjà
        cursor.execute('SELECT * FROM user WHERE user_mail = %s', (email,))
        email_account = cursor.fetchone()

        if account:
            # Si l'utilisateur existe déjà, afficher un message d'erreur
            flash('Cet utilisateur existe déjà !')
        elif email_account:
            # Si l'email est déjà utilisé, afficher un message d'erreur
            flash('Cette adresse email est déjà utilisée !')
        elif not re.match(r'[^@]+@[^@]+\.[^@]+', email):
            # Vérifier si l'adresse email est valide
            flash('Adresse email invalide !')
        elif not username or not password or not email:
            # Vérifier si tous les champs sont remplis
            flash('Veuillez remplir tous les champs !')
        else:
            # Obtenir le dernier compte_id pour l'utilisateur
            cursor.execute('SELECT MAX(user_compte_id) as max_compte_id FROM user')
            result = cursor.fetchone()
            last_compte_id = result['max_compte_id']
            
            # Incrémenter le compte_id de 1 pour le nouvel utilisateur
            if last_compte_id is not None:
                new_compte_id = last_compte_id + 1
            else:
                new_compte_id = 1  # Si c'est le premier enregistrement dans la base de données

            # Hachage du mot de passe pour la sécurité
            hashed_password = generate_password_hash(password)
            # Insérer le nouvel utilisateur dans la base de données
            cursor.execute('INSERT INTO user (user_login, user_password, user_compte_id, user_mail) VALUES (%s, %s, %s, %s)', (username, hashed_password, new_compte_id, email))
            mysql.connection.commit()  # Valider la transaction dans la base de données
            # Afficher un message de succès après l'inscription
            flash('Inscription réussie, vous pouvez maintenant vous connecter !')
            return redirect(url_for('login'))  # Rediriger vers la page de connexion
    
    # Rendre le template d'inscription si la méthode est GET
    return render_template('register.html')


if __name__ == '__main__':
    app.run(debug=True)
