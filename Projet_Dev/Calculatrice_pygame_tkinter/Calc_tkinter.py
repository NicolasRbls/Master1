import tkinter as tk

class Calculator:
    def __init__(self, root):
        self.root = root
        root.title("Calculatrice")
        root.configure(bg='#f2f2f2')  # Couleur de fond de la fenêtre

        self.expression = ""

        # Écran de la calculatrice
        self.display = tk.Entry(root, font=("Arial", 24), bd=12, relief="ridge", justify="right", bg="#e6e6e6")
        self.display.grid(row=0, column=0, columnspan=4, padx=10, pady=10)

        # Couleurs des boutons
        number_btn_color = '#ffffff'
        operator_btn_color = '#cccccc'
        clear_btn_color = '#ff6666'

        # Boutons
        buttons = [
            ('7', 1, 0, number_btn_color), ('8', 1, 1, number_btn_color), ('9', 1, 2, number_btn_color),
            ('/', 1, 3, operator_btn_color),
            ('4', 2, 0, number_btn_color), ('5', 2, 1, number_btn_color), ('6', 2, 2, number_btn_color),
            ('*', 2, 3, operator_btn_color),
            ('1', 3, 0, number_btn_color), ('2', 3, 1, number_btn_color), ('3', 3, 2, number_btn_color),
            ('-', 3, 3, operator_btn_color),
            ('0', 4, 0, number_btn_color), ('.', 4, 1, number_btn_color), ('+', 4, 2, operator_btn_color),
            ('^', 4, 3, operator_btn_color),
            ('=', 5, 0, operator_btn_color, 2), ('√', 5, 2, operator_btn_color), ('C', 5, 3, clear_btn_color)
        ]

        for button in buttons:
            text, row, col, bg_color = button[:4]
            col_span = button[4] if len(button) > 4 else 1
            btn = tk.Button(root, text=text, font=("Arial", 14), bg=bg_color,
                            command=lambda t=text: self.on_button_click(t))
            btn.grid(row=row, column=col, columnspan=col_span, sticky="nsew", padx=5, pady=5)

        # Uniformiser la taille des boutons
        for i in range(6):
            root.grid_rowconfigure(i, weight=1)
            root.grid_columnconfigure(i, weight=1)

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
