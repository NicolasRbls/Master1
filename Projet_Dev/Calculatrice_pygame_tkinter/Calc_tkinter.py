import tkinter as tk

class Calculator:
    def __init__(self, root):
        self.root = root
        root.title("Calculatrice")
        root.configure(bg='#96B4A8')  # Couleur de fond de la fenêtre

        self.expression = ""

        # Label "KAZIO" en haut à gauche
        label_kazio = tk.Label(root, text="KAZIO", font=("Helvetica", 16, "bold"), bg='#96B4A8', fg="#000000")
        label_kazio.grid(row=0, column=0, columnspan=2, sticky="w", padx=20)

        # Label "jy-20" en haut à droite
        label_jy20 = tk.Label(root, text="jy-20", font=("Helvetica", 14, "italic"), bg='#96B4A8', fg="#ff0000")
        label_jy20.grid(row=0, column=2, columnspan=2, sticky="e", padx=20)

        # Écran de la calculatrice, ajusté pour être juste en dessous des labels
        self.display = tk.Entry(root, font=("Helvetica", 24), bd=0, bg="#ffffff", justify="right")
        self.display.grid(row=1, column=0, columnspan=4, padx=20, pady=0,sticky="ew")  # Pady ajusté pour réduire l'espace

        # couleur boutons
        operator_btn_color = '#3B4A53'
        clear_btn_color = '#DDBF3B'
        number_btn_color = '#3B4A53'

        # Style des boutons, plus proche du style Apple (Notez que 'bg' est retiré ici)
        btn_params = {
            'font': ("Helvetica", 16),
            'bd': 0,
            'fg': "#ffffff",
            'activebackground': "#dddddd",
            'relief': 'flat',
            'highlightthickness': 0,
            'highlightbackground': "#f0f0f0",
            'padx': 10,
            'pady': 4
        }

        # Boutons
        buttons = [
            # Ajustez les numéros de ligne pour commencer à partir de row=2 (ou plus si nécessaire)
            ('7', 2, 0, number_btn_color), ('8', 2, 1, number_btn_color), ('9', 2, 2, number_btn_color),
            ('/', 2, 3, operator_btn_color),
            ('4', 3, 0, number_btn_color), ('5', 3, 1, number_btn_color), ('6', 3, 2, number_btn_color),
            ('*', 3, 3, operator_btn_color),
            ('1', 4, 0, number_btn_color), ('2', 4, 1, number_btn_color), ('3', 4, 2, number_btn_color),
            ('-', 4, 3, operator_btn_color),
            ('0', 5, 0, number_btn_color), ('.', 5, 1, number_btn_color), ('+', 5, 2, operator_btn_color),
            ('^', 5, 3, operator_btn_color),
            ('=', 6, 0, operator_btn_color, 2), ('√', 6, 2, operator_btn_color), ('C', 6, 3, clear_btn_color)
        ]

        for button in buttons:
            text, row, col, bg_color = button[:4]  # Cela extrait toujours les 4 premiers éléments
            col_span = button[4] if len(
                button) == 5 else 1  # Utilise la 5e valeur si elle existe, sinon définit col_span à 1

            button = tk.Button(root, text=text, command=lambda t=text: self.on_button_click(t), bg=bg_color,
                               **btn_params)
            button.grid(row=row, column=col, columnspan=col_span, sticky="nsew", padx=10, pady=10)
            root.grid_columnconfigure(col, weight=1)

        # Assurez-vous de mettre à jour la configuration des lignes pour que les boutons s'étendent de manière égale
        for i in range(8):  # Ajustez le nombre total de lignes si nécessaire
            root.grid_rowconfigure(i, weight=1)

    def on_button_click(self, char):
        if char in '0123456789.':
            if self.expression and self.expression[-1] == '√':
                self.expression += f"({char})**0.5"
            else:
                self.expression += char
            self.update_display()
        elif char in '+-*/^':
            self.expression += '**' if char == '^' else char
            self.update_display()
        elif char == '√':
            self.expression += '√'
            self.update_display()
        elif char == '=':
            try:
                result = str(eval(self.expression.replace('√', '')))
                self.expression = result
                self.update_display()
            except Exception as e:
                self.expression = "Error"
                self.update_display()
            finally:
                self.expression = ""
        elif char == 'C':
            self.expression = ""
            self.update_display()

    def update_display(self):
        self.display.delete(0, tk.END)
        self.display.insert(0, self.expression)


if __name__ == "__main__":
    root = tk.Tk()
    app = Calculator(root)
    root.mainloop()
