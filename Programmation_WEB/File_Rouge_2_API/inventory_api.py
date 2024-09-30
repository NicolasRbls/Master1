from flask import Flask, jsonify, request, render_template, redirect, url_for

app = Flask(__name__)

# Exemple d'inventaire avec des objets initiaux
inventory = {
    1: {"type": "potion", "quantity": 10},
    2: {"type": "arme", "quantity": 5},
    3: {"type": "cle", "quantity": 1},
    4: {"type": "plante", "quantity": 3},
    5: {"type": "piece d'armure", "quantity": 0}
}

# Route pour afficher la page d'inventaire
@app.route('/')
def inventory_page():
    return render_template('inventory.html', inventory=inventory)

# GET: Récupérer l'inventaire complet en JSON (API)
@app.route('/inventory', methods=['GET'])
def get_inventory():
    return jsonify(inventory), 200

# GET: Récupérer un objet spécifique par son id (API)
@app.route('/inventory/<int:item_id>', methods=['GET'])
def get_item(item_id):
    item = inventory.get(item_id)
    if item:
        return jsonify(item), 200
    else:
        return jsonify({"message": "Objet non trouvé"}), 404

# POST: Ajouter un nouvel objet à l'inventaire
@app.route('/inventory', methods=['POST'])
def add_item():
    new_item = {
        "type": request.form.get("type"),
        "quantity": int(request.form.get("quantity"))
    }
    item_id = max(inventory.keys()) + 1 if inventory else 1
    inventory[item_id] = new_item
    return redirect(url_for('inventory_page'))

# POST: Mettre à jour ou supprimer un objet via un formulaire HTML ou une API
@app.route('/inventory/<int:item_id>', methods=['POST'])
def update_or_delete_item(item_id):
    # Si la méthode est DELETE, on supprime l'objet
    if request.form.get('_method') == 'DELETE':
        if item_id in inventory:
            del inventory[item_id]
            return redirect(url_for('inventory_page'))
        return jsonify({"message": "Objet non trouvé"}), 404
    # Si la méthode est PUT, on met à jour l'objet
    elif request.form.get('_method') == 'PUT':
        if item_id in inventory:
            inventory[item_id]['quantity'] = int(request.form.get('quantity'))
            return redirect(url_for('inventory_page'))
        return jsonify({"message": "Objet non trouvé"}), 404

if __name__ == '__main__':
    app.run(debug=True)
