from chien import Chien
from chat import Chat
from lion import Lion
from pinguin import Pinguin
from zoo import Zoo
from animal import Animal

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
