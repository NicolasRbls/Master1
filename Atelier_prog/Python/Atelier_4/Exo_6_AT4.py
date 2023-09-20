import random
import time
import matplotlib.pyplot as plt

def sort_list(tab: list)->list:
    """
        Trie une liste d'éléments dans l'ordre croissant en utilisant l'algorithme de tri par sélection.

        :param tab: Une liste d'éléments à trier.
        :return: Une nouvelle liste contenant les éléments triés.
    """
    tab_return = tab.copy()
    for i in range(len(tab_return)):
        # Trouver le min
        minimum = i
        for j in range(i + 1, len(tab_return)):
            if tab_return[minimum] > tab_return[j]:
                minimum = j

        tab_return[i], tab_return[minimum] = tab_return[minimum], tab_return[i]
    return tab_return

def perf_tri(func1: callable, func2: callable, list_sizes: list, num_executions: int):
    """
    Compare les temps d'exécution moyens de deux fonctions de mélange pour différentes tailles de listes.

    :param func1: La première fonction à tester.
    :param func2: La deuxième fonction à tester.
    :param list_sizes: Une liste d'entiers représentant les tailles de liste pour lesquelles on effectue la comparaison.
    :param num_executions: Le nombre d'exécutions moyen nécessaire au calcul de la moyenne des temps.
    :return: Un doublet de listes de temps d'exécution moyens pour chaque fonction.
    """
    results_func1 = []  # Liste pour stocker les temps d'exécution moyens de func1
    results_func2 = []  # Liste pour stocker les temps d'exécution moyens de func2

    for size in list_sizes:
        total_time_func1 = 0.0
        total_time_func2 = 0.0

        for i in range(num_executions):
            # Créez une liste de taille 'size' avec des éléments aléatoires
            input_list = [random.randint(0, 1000) for y in range(size)]

            # Mesurez le temps d'exécution pour func1
            start_time = time.perf_counter()
            func1(input_list)
            end_time = time.perf_counter()
            total_time_func1 += end_time - start_time

            # Recréez la liste pour func2 car elle modifie la liste en place
            input_list = [random.randint(0, 1000) for y in range(size)]

            # Mesurez le temps d'exécution pour func2
            start_time = time.perf_counter()
            func2(input_list)
            end_time = time.perf_counter()
            total_time_func2 += end_time - start_time

        # Calculez le temps d'exécution moyen pour chaque fonction
        avg_time_func1 = total_time_func1 / num_executions
        avg_time_func2 = total_time_func2 / num_executions

        results_func1.append(avg_time_func1)
        results_func2.append(avg_time_func2)

    return (results_func1, results_func2)



# Liste de tailles de listes à tester
list_sizes = [10, 20, 50, 80, 100]

# Nombre d'exécutions moyen pour calculer la moyenne des temps
num_executions = 10

# Obtenez les résultats des temps d'exécution moyens pour extract_elements_list et random.sample
results_2 = perf_tri(sort_list, sorted, list_sizes, num_executions)
# Extrayez les résultats pour chaque fonction
times_sort_list = results_2[0]
times_sorted = results_2[1]

# Tracer les courbes des temps d'exécution en fonction de la taille des listes

plt.plot(list_sizes, times_sort_list, label='sort_list', marker='o')
plt.plot(list_sizes, times_sorted, label='sorted', marker='x')
plt.xlabel('Taille de la liste')
plt.ylabel('Temps d\'exécution moyen (secondes)')
plt.title('Comparaison des temps d\'exécution de sort_list et sorted')
plt.legend()
plt.grid(True)
plt.show()