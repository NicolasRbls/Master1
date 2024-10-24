from avatar import Hero, Mobs
from stats import Stat
from race import Race
from classe import Classe
from item import Equipment, Bag, Item
from quest import Quest

def main():
    # Configuration des races
    statElfe = Stat({'strength': 5, 'magic': 10, 'agility': 10, 'speed': 7, 'charisma': 5, 'chance': 5})  
    elfe = Race('Elfe', statElfe)
    
    statOrc = Stat({'strength': 10, 'magic': 0, 'agility': 5, 'speed': 5, 'charisma': 2, 'chance': 3})  
    orc = Race('Orc', statOrc)
    
    # Configuration des classes
    statWizard = Stat({'strength': 0, 'magic': 10, 'agility': 0, 'speed': 0, 'charisma': 10, 'chance': 10})
    wizard = Classe('Wizard', statWizard)

    statWarrior = Stat({'strength': 10, 'magic': 0, 'agility': 5, 'speed': 5, 'charisma': 5, 'chance': 5})
    warrior = Classe('Warrior', statWarrior)
    
    # Création d'un sac
    myBag = Bag({"sizeMax": 10, "items": []})
    
    # Création du héros
    hero = Hero({'name': 'Jean', 'race': elfe, 'classe': wizard, 'bag': myBag, 'equipment': [], 'element': 'Fire', 'profession': 'Mage'})
    
    # Création des ennemis
    enemy1 = Mobs({'name': 'Orc1', 'race': orc, 'classe': warrior, 'bag': myBag, 'equipment': [], 'element': 'Earth', 'type': 'Soldier'})
    enemy2 = Mobs({'name': 'Orc2', 'race': orc, 'classe': warrior, 'bag': myBag, 'equipment': [], 'element': 'Earth', 'type': 'Soldier'})
    
    # Lancement de la quête
    sword = Equipment({'classList': ['Warrior'], 'place': 'hand', 'name': 'Épée du dragon', 'type': 'sword', 'space': 2}, Stat({'strength': 5, 'magic': 0, 'agility': 5, 'speed': 5, 'charisma': 0, 'chance': 5}))
    
    quest = Quest({'lAvatar': [enemy1, enemy2], 'lvl': 1, 'gift': sword})
    quest.run(hero)

if __name__ == "__main__":
    main()
