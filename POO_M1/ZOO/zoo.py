class Zoo:
    def __init__(self):
        self._animaux = []

    def ajouter_animal(self, animal):
        self._animaux.append(animal)

    def afficher_animaux(self):
        for animal in self._animaux:
            print(f"Nom: {animal.nom}, Age: {animal.age}, Son: {animal.parler()}")
