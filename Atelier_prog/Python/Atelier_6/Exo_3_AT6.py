from datetime import datetime

def filtrer_par_date(transactions: list, date_debut: str, date_fin: str) -> list:
    """
    Filtre les transactions qui ont eu lieu entre la date de début et la date de fin (inclus).

    Args:
        transactions (List): Liste de transactions (chaque transaction est un dictionnaire).
        date_debut (str): Date de début au format 'JJ/MM/AAAA'.
        date_fin (str): Date de fin au format 'JJ/MM/AAAA'.

    Returns:
        List[Dict[str, str]]: Liste des transactions filtrées.
    """
    date_debut_obj = datetime.strptime(date_debut, '%d/%m/%Y').date()
    date_fin_obj = datetime.strptime(date_fin, '%d/%m/%Y').date()

    transactions_filtrees = [transaction for transaction in transactions if date_debut_obj <= datetime.strptime(transaction['date'],'%d/%m/%Y').date() <= date_fin_obj]
    return transactions_filtrees


def solde_client(transactions: list, client_id: int) -> float:
    """
    Calcule le solde net d'un client (somme des dépôts moins somme des retraits).

    Args:
        transactions (List): Liste de transactions (chaque transaction est un dictionnaire).
        client_id (int): Identifiant du client.

    Returns:
        float: Solde net du client.
    """
    # Liste en compréhension pour les dépôts du client
    depots = [transaction['montant'] for transaction in transactions if transaction['client_id'] == client_id and transaction['type'] == 'dépôt']

    # Liste en compréhension pour les retraits du client
    retraits = [transaction['montant'] for transaction in transactions if transaction['client_id'] == client_id and transaction['type'] == 'retrait']

    # Calcul du solde net
    solde_net = sum(depots) - sum(retraits)

    return solde_net

def transactions_max(transactions: list, n: int) -> list:
    """
    Renvoie les n transactions ayant le plus grand montant.

    Args:
        transactions (List): Liste de transactions (chaque transaction est un dictionnaire).
        n (int): Nombre d'opérations à renvoyer.

    Returns:
        List Liste des n transactions avec les montants les plus élevés.
    """
    # Tri des transactions par montant de la plus grande à la plus petite
    transactions_triees = sorted(transactions, key=lambda transaction: transaction['montant'], reverse=True)

    # Extraction des n premières transactions
    transactions_max = transactions_triees[:n]

    return transactions_max


def clients_sans_retraits(transactions: list) -> list:
    """
    Renvoie la liste des clients (identifiants) qui n'ont effectué aucun retrait.

    Args:
        transactions (List): Liste de transactions (chaque transaction est un dictionnaire).

    Returns:
        List: Liste des identifiants des clients sans retraits.
    """
    # Obtention de la liste des identifiants des clients avec retraits
    clients_avec_retraits = [transaction['client_id'] for transaction in transactions if transaction['type'] == 'retrait']

    # Filtrage des clients sans retraits
    clients_sans_retraits = list(filter(lambda client_id: client_id not in clients_avec_retraits,set([transaction['client_id'] for transaction in transactions])))

    return clients_sans_retraits

def convertisseur(transactions: list, taux_conversion: dict[str, float]) -> list:
    """
    Convertit les montants des transactions en euros en utilisant les taux de conversion fournis.

    Args:
        transactions (List): Liste de transactions (chaque transaction est un dictionnaire).
        taux_conversion (Dict[str, float]): Dictionnaire des taux de conversion par devise.

    Returns:
        List: Liste des transactions avec les montants convertis en euros.
    """
    transactions_converties = []

    for transaction in transactions:
        montant = transaction['montant']
        devise = transaction['devise']

        # Utilisation d'une expression lambda pour effectuer la conversion en euros
        montant_euros = montant * taux_conversion.get(devise, 1.0)

        # Création d'une nouvelle transaction avec le montant converti en euros
        nouvelle_transaction = {
            "client_id": transaction['client_id'],
            "date": transaction['date'],
            "type": transaction['type'],
            "montant": montant_euros,
            "devise": 'EUR'  # Toutes les conversions sont en euros
        }

        transactions_converties.append(nouvelle_transaction)

    return transactions_converties


def retraits_supérieurs_a(transactions: list, montant: float) -> list:
    """
    Renvoie la liste des transactions qui sont des retraits d'un montant supérieur ou égal au montant donné.

    Args:
        transactions (List[Dict[str, any]]): Liste de transactions (chaque transaction est un dictionnaire).
        montant (float): Montant minimum des retraits à rechercher.

    Returns:
        List[Dict[str, any]]: Liste des transactions correspondantes.
    """
    # Utilisation de filter avec une expression lambda pour filtrer les retraits
    retraits_supérieurs = list(
        filter(lambda transaction: transaction['type'] == 'retrait' and transaction['montant'] >= montant,transactions))

    return retraits_supérieurs


#-------------------------------------------------TEST------------------------------------------------------------------
transactions = [
    {'client_id': 1, 'date': '01/01/2022', 'type': 'dépôt', 'montant': 1000,'devise': 'EUR'},
    {'client_id': 2, 'date': '03/01/2022', 'type': 'retrait', 'montant': 200,'devise': 'USD'},
    {'client_id': 1, 'date': '04/01/2022', 'type': 'dépôt', 'montant': 500,'devise': 'EUR'},
    {'client_id': 2, 'date': '05/01/2022', 'type': 'dépôt', 'montant': 300,'devise': 'USD'},
    {'client_id': 3, 'date': '05/01/2022', 'type': 'dépôt', 'montant': 700,'devise': 'EUR'}
]
# Taux de conversion pour la devise par rapport à l'EUR
taux_conversion = {'USD': 0.85, 'GBP': 1.1}
# Tests
# 1. filtrer_par_date
print("Transactions entre le '02/01/2022' et le '05/01/2022':")
print("----------------------------------------------------")
print(filtrer_par_date(transactions, '02/01/2022', '05/01/2022'))
print()
# 2. solde_client
print("Solde du client avec ID 1:")
print("-------------------------")
print(solde_client(transactions, 1))
print()
# 3. transactions_max
print("Les 2 transactions avec le plus grand montant:")
print("---------------------------------------------")
print(transactions_max(transactions, 2))
print()
# 4. clients_sans_retraits
print("IDs des clients sans transactions de retrait:")
print("--------------------------------------------")
print(clients_sans_retraits(transactions))
print()
# 5. convertisseur
print("Transactions converties en EUR selon les taux donnés:")
print("-----------------------------------------------------")
print(convertisseur(transactions, taux_conversion))
print()
# 6. retraits_supérieurs_a
print("Retraits supérieurs ou égaux à 250:")
print("-----------------------------------")
print(retraits_supérieurs_a(transactions, 250))
