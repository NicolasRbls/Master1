#----------------------------------QUESTION 1---------------------------------------------------------------------------
conversion_Celsius_vers_Fahrenheit = lambda celsius: (celsius * 9/5) + 32

temperature_Celsius = 25
temperature_Fahrenheit = conversion_Celsius_vers_Fahrenheit(temperature_Celsius)

print(f"{temperature_Celsius} degrés Celsius équivalent à {temperature_Fahrenheit} degrés Fahrenheit.")

#----------------------------------QUESTION 2---------------------------------------------------------------------------
conversion_majuscules_minuscules = lambda s: s[0].upper() + s[1:].lower()

mot = "pYTHON"
nouveau_mot = conversion_majuscules_minuscules(mot)

print(f"Avant : {mot}")
print(f"Après : {nouveau_mot}")

#----------------------------------QUESTION 3---------------------------------------------------------------------------

liste = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

nombres_impairs = list(filter(lambda x: x % 2 != 0, liste))

print(nombres_impairs)
