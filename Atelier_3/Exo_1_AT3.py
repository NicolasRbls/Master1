def full_name(str_arg: str) -> str:
    test_espace = False
    for caractere in str_arg:
        if not caractere.isspace():
            test_espace = True
    if test_espace == False:
        return str_arg

    # Divise la chaîne en nom et prénom en utilisant l'espace comme séparateur
    parts = str_arg.split()

    # Extrait le nom et le prénom
    nom = parts[0].upper()
    prenom = parts[1].capitalize()

    # Renvoie la chaîne formatée
    return f"{nom} {prenom}"

def is_mail(str_arg: str) -> (int, int):
    # Vérifie si la chaîne contient le caractère '@'
    if "@" not in str_arg:
        return (0, 2)  # Il manque l'@

    # Divise la chaîne en nom d'utilisateur et domaine en utilisant '@' comme séparateur
    username, domain = str_arg.split("@")

    # Vérifie si le domaine contient un '.'
    if "." not in domain:
        return (0, 4)  # Il manque le '.'

    # Vérifie si le nom d'utilisateur est vide
    if not username or (username.startswith(".") or username.endswith(".") or ".." in username) :
        return (0, 1)  # Le nom d'utilisateur n'est pas valide

    if not  domain or (domain.startswith(".") or domain.endswith(".") or ".." in domain):
        return (0, 3)  # Le domaine n'est pas valide

    # Si toutes les vérifications sont réussies, l'adresse e-mail est valide
    return (1, 0)


#---------------------------TEST--------------------------------

def test_is_mail():
    str_variable2test = "bisgambiglia_paul@univ-corse.fr"
    resultat = is_mail(str_variable2test)
    if resultat == (1, 0):
        print("Test 1 réussi.")
    else:
        print(f"Échec du test 1 : {resultat}")

    str_variable2test = "bisgambiglia_paulOuniv-corse.fr"
    resultat = is_mail(str_variable2test)
    if resultat == (0, 2):
        print("Test 2 réussi.")
    else:
        print(f"Échec du test 2 : {resultat}")

    str_variable2test = "bisgambiglia_paul@univ-corsePOINTfr"
    resultat = is_mail(str_variable2test)
    if resultat == (0, 4):
        print("Test 3 réussi.")
    else:
        print(f"Échec du test 3 : {resultat}")

    str_variable2test = "@univ-corse.fr"
    resultat = is_mail(str_variable2test)
    if resultat == (0, 1):
        print("Test 4 réussi.")
    else:
        print(f"Échec du test 4 : {resultat}")

def test_full_name():
    str_variable2test = "bisgambiglia paul"
    resultat = full_name(str_variable2test)
    if resultat == "BISGAMBIGLIA Paul":
        print("Test 1 réussi.")
    else:
        print(f"Échec du test 1 : {resultat}")

    str_variable2test = "john doe"
    resultat = full_name(str_variable2test)
    if resultat == "JOHN Doe":
        print("Test 2 réussi.")
    else:
        print(f"Échec du test 2 : {resultat}")

    str_variable2test = "JANE DOE"
    resultat = full_name(str_variable2test)
    if resultat == "JANE Doe":
        print("Test 3 réussi.")
    else:
        print(f"Échec du test 3 : {resultat}")

    str_variable2test = "    "
    resultat = full_name(str_variable2test)
    if resultat == "    ":  # La chaîne est vide, donc elle doit rester inchangée
        print("Test 4 réussi.")
    else:
        print(f"Échec du test 4 : {resultat}")

# Appel des fonctions de test
test_is_mail()
test_full_name()
