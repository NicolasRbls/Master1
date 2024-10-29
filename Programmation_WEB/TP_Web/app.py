from flask import Flask, render_template, request, redirect, url_for, session, flash
from flask_mysqldb import MySQL
from werkzeug.security import generate_password_hash, check_password_hash
from datetime import datetime
from flask import jsonify

app = Flask(__name__)
app.secret_key = 'votre_cle_secrete'  # Changer pour une clé secrète sécurisée

# Configuration de la base de données MySQL
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'pong_game'

mysql = MySQL(app)


@app.route('/update_score', methods=['POST'])
def update_score():
    data = request.get_json()
    username = data.get('username')
    new_score = data.get('score')

    if 'loggedin' in session and session['username'] == username:
        cur = mysql.connection.cursor()

        # Récupérer le score actuel du joueur
        cur.execute("SELECT high_score FROM utilisateurs WHERE nom_utilisateur = %s", (username,))
        current_high_score = cur.fetchone()[0] or 0  # Utiliser 0 si le score est None

        # Si le nouveau score est supérieur au score actuel, on le met à jour
        if new_score > current_high_score:
            cur.execute(
                "UPDATE utilisateurs SET high_score = %s, high_score_date = %s WHERE nom_utilisateur = %s",
                (new_score, datetime.now(), username)
            )
            mysql.connection.commit()
            message = 'Nouveau record !'
        else:
            message = 'Score inchangé.'

        cur.close()
        return jsonify({'message': message})
    return jsonify({'message': 'Erreur d\'authentification.'}), 403


@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        username = request.form['username']
        email = request.form['email']
        password = generate_password_hash(request.form['password'])

        cur = mysql.connection.cursor()
        # Insérer un score initial de 0 pour les nouveaux utilisateurs
        cur.execute("INSERT INTO utilisateurs (nom_utilisateur, email, mot_de_passe, high_score) VALUES (%s, %s, %s, %s)", 
                    (username, email, password, 0))
        mysql.connection.commit()
        cur.close()
        flash('Inscription réussie ! Vous pouvez maintenant vous connecter.', 'success')
        return redirect(url_for('login'))
    return render_template('register.html')


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        email = request.form['email']
        password = request.form['password']

        cur = mysql.connection.cursor()
        cur.execute("SELECT * FROM utilisateurs WHERE email = %s", [email])
        user = cur.fetchone()
        cur.close()

        if user and check_password_hash(user[3], password):  # user[3] est le mot de passe
            session['loggedin'] = True
            session['username'] = user[1]
            flash('Connexion réussie !', 'success')
            return redirect(url_for('home'))
        else:
            flash('Identifiants incorrects, veuillez réessayer.', 'danger')
    return render_template('login.html')

@app.route('/home')
def home():
    if 'loggedin' in session:
        cur = mysql.connection.cursor()

        # Récupère les informations du joueur connecté
        cur.execute("SELECT nom_utilisateur, high_score, high_score_date FROM utilisateurs WHERE nom_utilisateur = %s", (session['username'],))
        user = cur.fetchone()

        # Récupère les 10 meilleurs scores
        cur.execute("SELECT nom_utilisateur, high_score, high_score_date FROM utilisateurs ORDER BY high_score DESC LIMIT 10")
        leaderboard = cur.fetchall()
        cur.close()

        # S'assurer que user est bien trouvé
        if user:
            return render_template('home.html', user=user, leaderboard=leaderboard)
        else:
            flash('Erreur: utilisateur introuvable.', 'danger')
            return redirect(url_for('login'))

    return redirect(url_for('login'))


@app.route('/pong')
def pong():
    if 'loggedin' in session:
        return render_template('pong.html')
    return redirect(url_for('login'))

@app.route('/diff')
def diff():  # Renommez ici
    if 'loggedin' in session:
        return render_template('diff.html')
    return redirect(url_for('login'))


@app.route('/logout')
def logout():
    # Effacer toutes les variables de session
    session.clear()  
    flash('Vous êtes maintenant déconnecté.', 'success')
    return redirect(url_for('login'))  # Redirige vers la page de connexion


if __name__ == '__main__':
    app.run(debug=True)


