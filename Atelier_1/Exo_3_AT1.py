import math

def discriminant(a: float, b: float, c: float) -> float:
    delta = b**2 - 4*a*c
    return delta

def racine_unique(a: float, b: float) -> float:
    if a == 0:
        raise ValueError("Le coefficient 'a' ne peut pas être égal à zéro.")

    x = -b / (2 * a)
    return x

def racine_double(a: float, b: float, delta: float, num: int) -> float:
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
    discri = discriminant(a,b,c)
    message = f"Solution de l'équation {a}x² + {b}x + {c} = 0\n"

    if discri < 0:
        message += "Pas de racine réelle"
    elif discri == 0:
        x1 =racine_unique(a,b)
        message += f"Racine unique : x = {x1}"
    else:
        x1 =racine_double(a,b,discri,1)
        x2 =racine_double(a,b,discri,2)
        message += f"Deux racines :\nx1 = {x1}\nx2 = {x2}"

    return message

def equation(a,b,c):
    print(solution_equation(a,b,c))



