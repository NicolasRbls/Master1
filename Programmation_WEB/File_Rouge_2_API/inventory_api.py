from flask import Flask, jsonify, request, render_template, redirect, url_for
from flask_mysqldb import MySQL
import MySQLdb.cursors  # Importer MySQLdb.cursors pour DictCursor

app = Flask(__name__)

# Configuration de la base de données MySQL
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = '2024_m1'

mysql = MySQL(app)

# Route pour afficher la page d'inventaire
@app.route('/')
def inventory_page():
    cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cursor.execute("SELECT * FROM inventory")
    inventory = cursor.fetchall()
    cursor.close()
    return render_template('inventory.html', inventory=inventory)

# GET: Récupérer l'inventaire complet en JSON (API)
@app.route('/inventory', methods=['GET'])
def get_inventory():
    cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cursor.execute("SELECT * FROM inventory")
    inventory = cursor.fetchall()
    cursor.close()

    inventory_dict = {item['id']: {"type": item['type'], "quantity": item['quantity']} for item in inventory}
    return jsonify(inventory_dict), 200

# GET: Récupérer un objet spécifique par son id (API)
@app.route('/inventory/<int:item_id>', methods=['GET'])
def get_item(item_id):
    cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cursor.execute("SELECT * FROM inventory WHERE id = %s", (item_id,))
    item = cursor.fetchone()
    cursor.close()

    if item:
        return jsonify({"type": item['type'], "quantity": item['quantity']}), 200
    else:
        return jsonify({"message": "Objet non trouvé"}), 404

# POST: Ajouter un nouvel objet à l'inventaire
@app.route('/inventory', methods=['POST'])
def add_item():
    new_type = request.form.get("type")
    new_quantity = int(request.form.get("quantity"))

    cursor = mysql.connection.cursor()
    cursor.execute("INSERT INTO inventory (type, quantity) VALUES (%s, %s)", (new_type, new_quantity))
    mysql.connection.commit()
    cursor.close()

    return redirect(url_for('inventory_page'))

# POST: Mettre à jour ou supprimer un objet via un formulaire HTML ou une API
@app.route('/inventory/<int:item_id>', methods=['POST'])
def update_or_delete_item(item_id):
    cursor = mysql.connection.cursor()

    # Si la méthode est DELETE, on supprime l'objet
    if request.form.get('_method') == 'DELETE':
        cursor.execute("DELETE FROM inventory WHERE id = %s", (item_id,))
        mysql.connection.commit()
        cursor.close()
        return redirect(url_for('inventory_page'))

    # Si la méthode est PUT, on met à jour l'objet
    elif request.form.get('_method') == 'PUT':
        new_quantity = int(request.form.get('quantity'))
        cursor.execute("UPDATE inventory SET quantity = %s WHERE id = %s", (new_quantity, item_id))
        mysql.connection.commit()
        cursor.close()
        return redirect(url_for('inventory_page'))

    cursor.close()
    return jsonify({"message": "Objet non trouvé"}), 404

if __name__ == '__main__':
    app.run(debug=True)
