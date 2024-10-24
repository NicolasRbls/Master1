from abc import ABC, abstractmethod

class Animal(ABC):
    _compteur_animaux = 0  # Compteur d'animaux (attribut de classe)

    def __init__(self, nom, age):
        self.__nom = nom  # Attributs privés avec deux underscores
        self.__age = age
        Animal._compteur_animaux += 1

    @abstractmethod
    def parler(self):
        pass

    # Getter et setter pour l'âge
    @property
    def age(self):
        return self.__age

    @age.setter
    def age(self, nouvel_age):
        if nouvel_age > 0:
            self.__age = nouvel_age
        else:
            raise ValueError("L'âge doit être un nombre positif.")

    # Getter pour le nom (lecture seule)
    @property
    def nom(self):
        return self.__nom

    @classmethod
    def nombre_animaux(cls):
        return cls._compteur_animaux
