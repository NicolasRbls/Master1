from flask import Flask, jsonify, request

app = Flask(__name__)

# Exemple d'inventaire avec des objets initiaux
inventory = {
    1: {"type": "potion", "quantity": 10},
    2: {"type": "arme", "quantity": 5},
    3: {"type": "cle", "quantity": 1},
    4: {"type": "plante", "quantity": 3},
    5: {"type": "piece d'armure", "quantity": 0}
}

# GET: Récupérer l'inventaire complet
@app.route('/inventory', methods=['GET'])
def get_inventory():
    return jsonify(inventory), 200

# GET: Récupérer un objet spécifique par son id
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
    new_item = request.json
    item_id = max(inventory.keys()) + 1 if inventory else 1
    inventory[item_id] = new_item
    return jsonify({"message": "Objet ajouté", "id": item_id}), 201

# PUT: Mettre à jour la quantité d'un objet
@app.route('/inventory/<int:item_id>', methods=['PUT'])
def update_item(item_id):
    if item_id in inventory:
        update_data = request.json
        inventory[item_id].update(update_data)
        return jsonify({"message": "Objet mis à jour"}), 200
    else:
        return jsonify({"message": "Objet non trouvé"}), 404

# DELETE: Supprimer un objet de l'inventaire
@app.route('/inventory/<int:item_id>', methods=['DELETE'])
def delete_item(item_id):
    if item_id in inventory:
        del inventory[item_id]
        return jsonify({"message": "Objet supprimé"}), 200
    else:
        return jsonify({"message": "Objet non trouvé"}), 404

if __name__ == '__main__':
    app.run(debug=True)
