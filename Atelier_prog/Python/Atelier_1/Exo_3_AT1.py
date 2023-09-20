import math

def discriminant(a: float, b: float, c: float) -> float:
    """
        Calcule le discriminant d'une équation du second degré ax² + bx + c = 0.

        Args:
            a (float): Coefficient a (doit être différent de zéro).
            b (float): Coefficient b.
            c (float): Coefficient c.

        Returns:
            float: La valeur du discriminant (b² - 4ac).

        Raises:
            ValueError: Si le coefficient 'a' est égal à zéro.
    """
    if a == 0:
        raise ValueError("Le coefficient 'a' ne peut pas être égal à zéro.")

    delta = b**2 - 4*a*c
    return delta

def racine_unique(a: float, b: float) -> float:
    """
       Calcule la racine unique d'une équation du second degré ax² + bx + c = 0.

       Args:
           a (float): Coefficient a (doit être différent de zéro).
           b (float): Coefficient b.

       Returns:
           float: La valeur de la racine unique x.

       Raises:
           ValueError: Si le coefficient 'a' est égal à zéro.
    """
    if a == 0:
        raise ValueError("Le coefficient 'a' ne peut pas être égal à zéro.")

    x = -b / (2 * a)
    return x

def racine_double(a: float, b: float, delta: float, num: int) -> float:
    """
        Calcule les racines d'une équation du second degré ax² + bx + c = 0.

        Args:
            a (float): Coefficient a (doit être différent de zéro).
            b (float): Coefficient b.
            delta (float): Discriminant (b² - 4ac).
            num (int): Numéro de la racine (1 ou 2).

        Returns:
            float: La valeur de la racine correspondante (x1 ou x2).

        Raises:
            ValueError: Si le coefficient 'a' est égal à zéro, si le numéro de racine n'est pas valide
            ou si le discriminant est négatif.
    """
    if a == 0:
        raise ValueError("Le coefficient 'a' ne peut pas être égal à zéro.")

    if num != 1 and num != 2:
        raise ValueError("Le numéro de racine doit être 1 ou 2.")

    if delta < 0:
        raise ValueError("Le discriminant doit être positif ou nul pour avoir des racines réelles.")

    if num == 1:
        x1 = (-b + math.sqrt(delta)) / (2 * a)
        return x1
    elif num == 2:
        x2 = (-b - math.sqrt(delta)) / (2 * a)
        return x2


def str_equation(a: float, b: float, c:float)->str:
    """
       Convertit les coefficients d'une équation du second degré en une chaîne de caractères.

       Args:
           a (float): Coefficient a (doit être différent de zéro).
           b (float): Coefficient b.
           c (float): Coefficient c.

       Returns:
           str: La chaîne de caractères représentant l'équation (au format "ax² + bx + c = 0").

       Raises:
           ValueError: Si le coefficient 'a' est égal à zéro.
    """
    equation_str = ""
    if a == 0:
        raise ValueError("Le coefficient 'a' ne peut pas être égal à zéro.")

    if a == 1:
        equation_str += "x² "
    elif a == -1:
        equation_str += "-x² "
    else:
        equation_str += f"{a}x² "

    if b != 0:
        if b > 0:
            equation_str += f"+ {b}x "
        else:
            equation_str += f"- {-b}x "

    if c != 0:
        if c > 0:
            equation_str += f"+ {c}"
        else:
            equation_str += f"- {-c}"

    equation_str += "= 0"
    return equation_str


def solution_equation(a: float,b: float,c: float)->str:
    """
        Résout une équation du second degré ax² + bx + c = 0 et retourne un message.

        Args:
            a (float): Coefficient a (doit être différent de zéro).
            b (float): Coefficient b.
            c (float): Coefficient c.

        Returns:
            str: Un message représentant les solutions de l'équation.

        Raises:
            ValueError: Si le coefficient 'a' est égal à zéro.
    """
    discri = discriminant(a,b,c)
    message = str_equation(a,b,c)

    if discri < 0:
        message += " Pas de racine réelle"
    elif discri == 0:
        x1 =racine_unique(a,b)
        message += f" Racine unique : x = {x1}"
    else:
        x1 =racine_double(a,b,discri,1)
        x2 =racine_double(a,b,discri,2)
        message += f" Deux racines :\nx1 = {x1}\nx2 = {x2}"

    return message

def equation(a: float,b: float,c: float):
    """
        Affiche le résultat de la résolution de l'équation du second degré ax² + bx + c = 0.

        Args:
            a (float): Coefficient a (doit être différent de zéro).
            b (float): Coefficient b.
            c (float): Coefficient c.

        Returns:
            str: Le message représentant les solutions de l'équation.
    """
    result = solution_equation(a,b,c)
    print(result)
    return result

def test_solution_equation():
    """
        Effectue plusieurs tests sur la résolution de l'équation du second degré et vérifie les résultats.
    """
    test_cases = {
        # Test 1 : Équation sans solution réelle
        (1, 2, 5): "x² + 2x + 5= 0 Pas de racine réelle",

        # Test 2 : Équation avec une racine unique
        (1, -4, 4): "x² - 4x + 4= 0 Racine unique : x = 2.0",

        # Test 3 : Équation avec deux racines réelles
        (1, -5, 6): "x² - 5x + 6= 0 Deux racines :\nx1 = 3.0\nx2 = 2.0",

        # Test 4 : Équation avec a = 0 (doit lever une exception)
        (0, 3, 4): "Le coefficient 'a' ne peut pas être égal à zéro.",

        # Test 5 : Équation avec discriminant négatif (pas de racine réelle)
        (2, 1, 3): "2x² + 1x + 3= 0 Pas de racine réelle",
    }

    for params, expected_result in test_cases.items():
        try:
            result = equation(*params)
            if result == expected_result:
                print(f"Test réussi pour {params}. Résultat obtenu : {result}")
            else:
                print(f"Test échoué pour {params}. Résultat obtenu : {result}. Résultat attendu : {expected_result}")
        except ValueError as e:
            if str(e) == expected_result:
                print(f"Test réussi pour {params}. Erreur obtenue : {str(e)}")
            else:
                print(f"Test échoué pour {params}. Erreur obtenue : {str(e)}. Erreur attendue : {expected_result}")

    print("Tous les tests ont été effectués.")

# Appeler la fonction de test
test_solution_equation()



