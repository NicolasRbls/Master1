from abc import ABC, abstractmethod

# 1.classe abstraite Animal
class Animal(ABC):
    _compteur_animaux = 0  # 3.Compteur d'animaux (attribut de classe)

    def __init__(self, nom, age):
        self.__nom = nom  # 4. Attributs privés avec deux underscores
        self.__age = age
        Animal._compteur_animaux += 1

    @abstractmethod
    def parler(self):
        pass

    # 5.(getter et setter)
    @property
    def age(self):
        return self.__age

    @age.setter
    def age(self, nouvel_age):
        if nouvel_age > 0:
            self.__age = nouvel_age
        else:
            raise ValueError("L'âge doit être un nombre positif.")

    # Propriété pour accéder au nom (getter seulement)
    @property
    def nom(self):
        return self.__nom

    @classmethod
    def nombre_animaux(cls):
        return cls._compteur_animaux

# 2.classes dérivées
class Chien(Animal):
    def parler(self):
        return "Woof"

class Chat(Animal):
    def parler(self):
        return "Miaou"

class Lion(Animal):
    def parler(self):
        return "Roaaar"

class Pinguin(Animal):
    def parler(self):
        return "Hyaa"

# 6.classe Zoo pour gérer plusieurs animaux
class Zoo:
    def __init__(self):
        self._animaux = []

    def ajouter_animal(self, animal):
        self._animaux.append(animal)

    def afficher_animaux(self):
        for animal in self._animaux:
            print(f"Nom: {animal.nom}, Age: {animal.age}, Son: {animal.parler()}")

#utilisation
if __name__ == "__main__":
    zoo = Zoo()

    chien = Chien("Rex", 5)
    chat = Chat("Whiskers", 3)
    lion = Lion("Simba", 7)
    pinguin = Pinguin("Pingu", 9)

    zoo.ajouter_animal(chien)
    zoo.ajouter_animal(chat)
    zoo.ajouter_animal(lion)
    zoo.ajouter_animal(pinguin)

    print(f"Nombre total d'animaux dans le zoo: {Animal.nombre_animaux()}")
    zoo.afficher_animaux()
