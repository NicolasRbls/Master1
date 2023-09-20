lettre_verte = {20: 1.16, 100: 2.32, 250: 4.00, 500: 6.00, 1000: 7.5, 3000: 10.5}
lettre_prioritaire = {20: 1.43, 100: 2.86, 250: 5.26, 500: 7.89, 3000: 10.5}
ecopli = {20: 1.14, 100: 2.28, 250: 3.92}


while True :
    type_lettre_entre = input("Entrez le type de lettre (verte / prioritaire / ecopli) : ")
    poids_lettre_entre = float(input("Entrez le poids de la lettre (en grammes) : "))
    sticker_suivi_entre = input("Voulez-vous un sticker de suivi ? (oui / non) : ")
    if ((type_lettre_entre=="verte" or type_lettre_entre=="prioritaire" or type_lettre_entre=="ecopli")
    and (poids_lettre_entre <= 3000) and (sticker_suivi_entre=="oui" or sticker_suivi_entre =="non")):
        break
    else :
        print("Mauvaise entré veuillez réessayer")


def calculer_tarif_affranchissement(type_lettre, poids_lettre, sticker_suivi):
    tarif = 0

    if type_lettre == "verte":
        if poids_lettre < 20:
            tarif = lettre_verte[20]
        elif 20 < poids_lettre <= 100:
            tarif = lettre_verte[100]
        elif 100 < poids_lettre <= 250:
            tarif = lettre_verte[250]
        elif 250 < poids_lettre <= 500:
            tarif = lettre_verte[500]
        elif 500 < poids_lettre <= 1000:
            tarif = lettre_verte[1000]
        elif 1000 < poids_lettre <= 3000:
            tarif = lettre_verte[3000]

    elif type_lettre == "prioritaire":
        if poids_lettre < 20:
            tarif = lettre_prioritaire[20]
        elif 20 < poids_lettre <= 100:
            tarif = lettre_prioritaire[100]
        elif 100 < poids_lettre <= 250:
            tarif = lettre_prioritaire[250]
        elif 250 < poids_lettre <= 500:
            tarif = lettre_prioritaire[500]
        elif 500 < poids_lettre <= 1000:
            tarif = lettre_prioritaire[1000]
        elif 1000 < poids_lettre <= 3000:
            tarif = lettre_prioritaire[3000]

    elif type_lettre == "ecopli":
        if poids_lettre < 20:
            tarif = ecopli[20]
        elif 20 < poids_lettre <= 100:
            tarif = ecopli[100]
        elif 100 < poids_lettre <= 250:
            tarif = ecopli[250]

    if sticker_suivi.lower() == "oui":
        tarif += 0.5

    return tarif

tarif = calculer_tarif_affranchissement(type_lettre_entre, poids_lettre_entre, sticker_suivi_entre)
print(f"Le tarif d'affranchissement de votre lettre est de {tarif} euros.")
