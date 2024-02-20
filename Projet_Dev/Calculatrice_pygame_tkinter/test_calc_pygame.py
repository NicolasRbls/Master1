import pygame
import sys

# Initialisation de Pygame
pygame.init()

# Couleurs
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
GRAY = (200, 200, 200)

# Configuration de la fenêtre
size = (400, 600)
screen = pygame.display.set_mode(size)
pygame.display.set_caption("Calculatrice")

# Variables globales pour la logique de la calculatrice
current_number = ""
previous_number = None
operation = None
result_shown = False


# Fonctions de la calculatrice
def add_number(num):
    global current_number, result_shown
    if result_shown:
        current_number = str(num)
        result_shown = False
    else:
        current_number += str(num)


def set_operation(op):
    global current_number, previous_number, operation, result_shown
    if current_number != "":
        if not result_shown:
            previous_number = int(current_number)
        current_number = ""
        operation = op
        result_shown = False


def calculate():
    global current_number, previous_number, operation, result_shown
    if previous_number is not None and current_number != "" and operation is not None:
        if operation == "+":
            result = previous_number + int(current_number)
        elif operation == "-":
            result = previous_number - int(current_number)
        previous_number = result
        current_number = str(result)
        result_shown = True
    return None


# Fonction pour dessiner les boutons
def draw_button(text, position):
    font = pygame.font.Font(None, 36)
    text_render = font.render(text, True, BLACK)
    text_rect = text_render.get_rect(center=position)
    screen.blit(text_render, text_rect)


# Fonction principale
def main():
    global current_number
    running = True

    while running:
        screen.fill(WHITE)

        # Affichage du nombre actuel
        font = pygame.font.Font(None, 72)
        text_render = font.render(current_number, True, BLACK)
        screen.blit(text_render, (10, 10))

        # Dessin des boutons
        buttons = {
            "1": (50, 150), "2": (150, 150), "3": (250, 150),
            "4": (50, 250), "+": (150, 250), "-": (250, 250),
            "=": (150, 350)
        }
        for text, pos in buttons.items():
            draw_button(text, pos)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                for text, pos in buttons.items():
                    if pygame.Rect(pos[0] - 50, pos[1] - 50, 100, 100).collidepoint(event.pos):
                        if text in "1234":
                            add_number(text)
                        elif text in "+-":
                            set_operation(text)
                        elif text == "=":
                            calculate()

        pygame.display.flip()

    pygame.quit()
    sys.exit()


if __name__ == "__main__":
    main()
