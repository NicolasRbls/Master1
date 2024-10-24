import pygame
import random
import numpy as np

pygame.init()

# Définir les dimensions de l'écran
width, height = 600, 400
snake_block = 10
game_display = pygame.display.set_mode((width, height))

# Définir les couleurs
white = (255, 255, 255)
black = (0, 0, 0)
red = (213, 50, 80)
green = (0, 255, 0)

# Vitesse du serpent
snake_speed = 15
clock = pygame.time.Clock()

# Fonction pour dessiner le serpent
def draw_snake(snake_block, snake_list):
    for x in snake_list:
        pygame.draw.rect(game_display, green, [x[0], x[1], snake_block, snake_block])

# Fonction de gestion de l'état
def get_state(snake_list, food):
    head = snake_list[0]
    food_x, food_y = food[0], food[1]
    head_x, head_y = head[0], head[1]

    # Direction de la nourriture par rapport à la tête
    food_left = 1 if food_x < head_x else 0
    food_right = 1 if food_x > head_x else 0
    food_up = 1 if food_y < head_y else 0
    food_down = 1 if food_y > head_y else 0

    # Proximité des murs
    danger_left = 1 if head_x - snake_block < 0 else 0
    danger_right = 1 if head_x + snake_block >= width else 0
    danger_up = 1 if head_y - snake_block < 0 else 0
    danger_down = 1 if head_y + snake_block >= height else 0

    # L'état simplifié ne contient que ces informations
    state = (
        food_left, food_right, food_up, food_down,  # Position relative de la nourriture
        danger_left, danger_right, danger_up, danger_down  # Proximité des murs
    )
    
    return state

# Fonction de récompense
def reward_function(game_over, snake_ate_food, head, food):
    if game_over:
        return -40  # Punition pour la mort
    elif snake_ate_food:
        return 5  # Récompense pour avoir mangé
    else:
        # Récompense ou pénalité en fonction de la distance à la nourriture
        distance_to_food = abs(head[0] - food[0]) + abs(head[1] - food[1])
        return -0.1 + (1.0 / distance_to_food)  # Plus il est proche, mieux c'est

# Fonction principale du jeu
def game_step(agent, state, snake_list, x_change, y_change, foodx, foody):
    previous_direction = None
    if x_change == -snake_block:
        previous_direction = 0  # Gauche
    elif x_change == snake_block:
        previous_direction = 1  # Droite
    elif y_change == -snake_block:
        previous_direction = 2  # Haut
    elif y_change == snake_block:
        previous_direction = 3  # Bas

    # Choisir la nouvelle action avec l'agent en fonction de la direction précédente
    action = choose_action(agent, state, previous_direction)

    # Mettre à jour la position de la tête du serpent selon l'action
    if action == 0:  # Gauche
        x_change = -snake_block
        y_change = 0
    elif action == 1:  # Droite
        x_change = snake_block
        y_change = 0
    elif action == 2:  # Haut
        y_change = -snake_block
        x_change = 0
    elif action == 3:  # Bas
        y_change = snake_block
        x_change = 0

    # Mettre à jour la position de la tête du serpent
    head = [snake_list[0][0] + x_change, snake_list[0][1] + y_change]

    # Vérification des collisions avec les murs ou le corps
    game_over = head[0] >= width or head[0] < 0 or head[1] >= height or head[1] < 0 or head in snake_list[1:]

   
    # Ajout de la nouvelle tête à la liste
    snake_list.insert(0, head)

    # Vérification si le serpent mange de la nourriture
    snake_ate_food = False
    if head[0] == foodx and head[1] == foody:
        foodx = round(random.randrange(0, width - snake_block) / 10.0) * 10.0
        foody = round(random.randrange(0, height - snake_block) / 10.0) * 10.0
        snake_ate_food = True
    else:
        # Si le serpent ne mange pas, retirer la queue
        snake_list.pop()

    return snake_list, x_change, y_change, foodx, foody, game_over, snake_ate_food


class QLearningAgent:
    def __init__(self, state_size, action_size):
        self.state_size = state_size
        self.action_size = action_size
        self.q_table = np.zeros((2, 2, 2, 2, 2, 2, 2, 2, action_size))
        self.learning_rate = 0.1
        self.discount_rate = 0.95
        self.epsilon = 1.0  # Exploration
        self.epsilon_decay = 0.99
        self.epsilon_min = 0.01

    def choose_action(self, state):
        if np.random.rand() < self.epsilon:
            return np.random.choice(self.action_size)  # Choisir une action aléatoire (exploration)
        return np.argmax(self.q_table[state])  # Choisir l'action avec la plus grande valeur (exploitation)
    
    def learn(self, state, action, reward, next_state, done):
        next_state = tuple(min(max(s, 0), limit - 1) for s, limit in zip(next_state, self.q_table.shape[:-1]))

        target = reward + self.discount_rate * np.max(self.q_table[next_state]) * (1 - done)
        self.q_table[state + (action,)] += self.learning_rate * (target - self.q_table[state + (action,)])

        if done:
            self.epsilon = max(self.epsilon_min, self.epsilon * self.epsilon_decay)

def train_agent():
    episodes = 10000
    action_size = 4  # Gauche, Droite, Haut, Bas
    agent = QLearningAgent((2, 2, 2, 2, 2, 2, 2, 2), action_size)

    load_q_table(agent)

    for e in range(episodes):
        snake_list = [[300, 200]]
        x_change = 0
        y_change = 0
        foodx = round(random.randrange(0, width - snake_block) / 10.0) * 10.0
        foody = round(random.randrange(0, height - snake_block) / 10.0) * 10.0
        done = False
        state = get_state(snake_list, [foodx, foody])
        move_count = 0
        max_moves = 10000

        while not done:
            action = agent.choose_action(state)
            snake_list, x_change, y_change, foodx, foody, game_over, snake_ate_food = game_step(
            agent, state, snake_list, x_change, y_change, foodx, foody)


            reward = reward_function(game_over, snake_ate_food, snake_list[0], [foodx, foody])
            next_state = get_state(snake_list, [foodx, foody])

            agent.learn(state, action, reward, next_state, game_over)

            state = next_state

            move_count += 1
            if game_over or move_count > max_moves:
                done = True
                

        if e % 100 == 0:
            score = len(snake_list) - 1
            print(f"Épisode {e}, Score: {score}, Epsilon: {agent.epsilon}")

    save_q_table(agent)
    return agent

def play_after_training(agent):
    game_running = True
    snake_list = [[300, 200]]
    x_change = 0
    y_change = 0
    foodx = round(random.randrange(0, width - snake_block) / 10.0) * 10.0
    foody = round(random.randrange(0, height - snake_block) / 10.0) * 10.0
    done = False
    state = get_state(snake_list, [foodx, foody])
    move_count = 0
    max_moves = 1000

    while not done:
        game_display.fill(black)
        draw_snake(snake_block, snake_list)
        pygame.draw.rect(game_display, red, [foodx, foody, snake_block, snake_block])
        pygame.display.update()

        action = agent.choose_action(state)
        snake_list, x_change, y_change, foodx, foody, game_over, snake_ate_food = game_step(
    agent, state, snake_list, x_change, y_change, foodx, foody)


        state = get_state(snake_list, [foodx, foody])

        move_count += 1
        if game_over or move_count > max_moves:
            done = True

        clock.tick(snake_speed)

# Sauvegarde et chargement de la table Q
def save_q_table(agent):
    np.save("q_table.npy", agent.q_table)

def load_q_table(agent):
    try:
        agent.q_table = np.load("q_table.npy")
    except FileNotFoundError:
        print("Aucune table Q trouvée. Entrainement nécessaire.")

def choose_action(agent, state, previous_direction):
    """
    Modifie la fonction de choix de l'action pour éviter que l'IA revienne sur ses pas.
    """
    action = agent.choose_action(state)

    # Ne pas revenir directement en arrière
    if previous_direction == 0 and action == 1:  # Si le serpent allait à gauche, ne pas aller à droite
        action = np.random.choice([2, 3])  # Choisir entre monter ou descendre
    elif previous_direction == 1 and action == 0:  # Si le serpent allait à droite, ne pas aller à gauche
        action = np.random.choice([2, 3])
    elif previous_direction == 2 and action == 3:  # Si le serpent montait, ne pas aller en bas
        action = np.random.choice([0, 1])
    elif previous_direction == 3 and action == 2:  # Si le serpent descendait, ne pas monter
        action = np.random.choice([0, 1])

    return action



# Pour lancer le jeu après l'entraînement
if __name__ == "__main__":
    game_running = True
    while game_running:
        print("Entraînement de l'agent...")
        agent = train_agent()

        print("Affichage d'une partie après l'entraînement...")
        result = play_after_training(agent)

        # Vérification si l'utilisateur veut arrêter le programme
        print("Appuyez sur 'q' à tout moment pour quitter.")
        
        # Boucle qui continue l'entraînement sauf si 'q' est pressé
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    game_running = False
                    pygame.quit()
                    exit()
        
        # L'entraînement redémarre automatiquement après chaque partie
        print("Redémarrage de l'entraînement après le jeu...")
