import requests

# URL de base de l'API
base_url = "http://127.0.0.1:5000/inventory"

# Demander l'ID de l'objet et gérer les erreurs
while True:
    try:
        item_id = int(input("Entrez l'ID de l'objet à modifier ou créer (un nombre entier) : "))
        break
    except ValueError:
        print("Erreur : L'ID doit être un nombre entier. Veuillez réessayer.")

# Demander l'action (ajouter ou retirer) et gérer les erreurs
while True:
    action = input("Souhaitez-vous ajouter ou retirer une quantité ? (entrez 'ajouter' ou 'retirer') : ").strip().lower()
    if action in ['ajouter', 'retirer']:
        break
    else:
        print("Erreur : Vous devez entrer 'ajouter' ou 'retirer'. Veuillez réessayer.")

# Demander la quantité et gérer les erreurs
while True:
    try:
        quantity = int(input("Entrez la quantité (un nombre entier) : "))
        if quantity < 0:
            raise ValueError("La quantité ne peut pas être négative.")
        break
    except ValueError as e:
        print(f"Erreur : {e}. Veuillez entrer un nombre entier positif.")

# Vérifier si l'objet existe déjà (avec un slash entre l'URL de base et l'ID)
response = requests.get(base_url + "/" + str(item_id))

if response.status_code == 200:
    item = response.json()
    current_quantity = item["quantity"]

    if action == 'ajouter':
        # Ajouter la quantité et mettre à jour
        new_quantity = current_quantity + quantity
    elif action == 'retirer':
        # Retirer la quantité sans tomber en dessous de zéro
        new_quantity = max(0, current_quantity - quantity)

    # Mettre à jour avec la nouvelle quantité
    update_data = {"quantity": new_quantity}
    update_response = requests.put(base_url + "/" + str(item_id), json=update_data)
    
    if update_response.status_code == 200:
        print(f"Quantité mise à jour à {new_quantity} pour l'objet ID {item_id}")
    else:
        print(f"Erreur lors de la mise à jour. Code de réponse: {update_response.status_code}")
        print(f"Message de l'erreur : {update_response.text}")
        
elif response.status_code == 404:
    # Si l'objet n'existe pas, proposer d'en ajouter un nouveau
    print(f"Objet avec ID {item_id} non trouvé.")
    add_new = input("Voulez-vous ajouter cet objet ? (oui/non) : ").strip().lower()
    
    if add_new == 'oui':
        # Demander le type et la quantité pour le nouvel objet
        new_type = input("Entrez le type du nouvel objet (ex: potion, plante, etc.) : ")
        new_quantity = max(0, quantity)  # Assurer que la quantité soit au moins 0

        # Créer un nouvel objet SANS spécifier l'ID
        new_item = {"type": new_type, "quantity": new_quantity}
        create_response = requests.post(base_url, json=new_item)

        if create_response.status_code == 201:
            print(f"Nouvel objet créé avec quantité {new_quantity}.")
        else:
            print(f"Erreur lors de la création du nouvel objet. Code de réponse: {create_response.status_code}")
            print(f"Message de l'erreur : {create_response.text}")
    else:
        print("Aucune action effectuée.")
else:
    print("Erreur lors de la récupération de l'objet.")
