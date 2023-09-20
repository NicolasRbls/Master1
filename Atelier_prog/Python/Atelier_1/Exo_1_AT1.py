try:
    imc = float(input("Rentrez votre IMC : "))
except ValueError:
    print("Erreur : Veuillez entrer un nombre réel valide en tant qu'IMC.")


def message_imc(imc):
    """
        Détermine l'interprétation de l'IMC en fonction de sa valeur.

        Args:
            imc (float): L'Indice de Masse Corporelle (IMC) à interpréter.

        Returns:
            str: Une interprétation de l'IMC, telle que "dénutrition ou famine",
                 "maigreur", "corpulence normale", "surpoids", "obésité modérée",
                 "obésité sévère", "obésité morbide", ou "IMC en dehors des plages connues".
    """
    interpretations = {
        (0, 16.5): "dénutrition ou famine",
        (16.5, 18.5): "maigreur",
        (18.5, 25): "corpulence normale",
        (25, 30): "surpoids",
        (30, 35): "obésité modérée",
        (35, 40): "obésité sévère",
        (40, 130): "obésité morbide"
    }

    for imc_range, interpretation in interpretations.items():
        if imc_range[0] <= imc < imc_range[1]:
            return interpretation

    return "IMC en dehors des plages connues"

interpretation = message_imc(imc)
print(f"L'IMC de {imc} correspond à : {interpretation}")

def tester_message_imc():
    """
        Effectue un test de la fonction message_imc en utilisant différentes valeurs d'IMC
        et affiche les résultats des tests.
    """
    test_cases = {
        15.0: "dénutrition ou famine",
        17.5: "maigreur",
        22.0: "corpulence normale",
        28.5: "surpoids",
        32.0: "obésité modérée",
        37.5: "obésité sévère",
        42.0: "obésité morbide",
        -12: "IMC en dehors des plages connues",
        140: "IMC en dehors des plages connues"
    }

    for imc, expected_result in test_cases.items():
        interpretation = message_imc(imc)
        if interpretation == expected_result:
            print(f"L'IMC de {imc} correspond à : {interpretation} (Correct)")
        else:
            print(f"L'IMC de {imc} correspond à : {interpretation} (Incorrect, attendu : {expected_result})")

# Appeler la fonction de test
tester_message_imc()

