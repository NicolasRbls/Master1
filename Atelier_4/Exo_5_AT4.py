import random
import time
import Exo_2_AT4
import matplotlib.pyplot as plt
import Exo_4_AT4

#------------------------------------------------QUESTION 1------------------------------------------------------


def perf_mix(func1: callable, func2: callable, list_sizes: list, num_executions: int):
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
list_sizes = [10, 500, 5000, 50000, 100000]


# Nombre d'exécutions moyen pour calculer la moyenne des temps
num_executions = 10

# Obtenez les résultats des temps d'exécution moyens pour mix_list et random.shuffle
results = perf_mix(Exo_2_AT4.mix_list, random.shuffle, list_sizes, num_executions)

# Extrayez les résultats pour chaque fonction
times_mix_list = results[0]
times_shuffle = results[1]

# Tracer les courbes des temps d'exécution en fonction de la taille des listes
plt.figure()
plt.plot(list_sizes, times_mix_list, label='mix_list', marker='o')
plt.plot(list_sizes, times_shuffle, label='random.shuffle', marker='x')
plt.xlabel('Taille de la liste')
plt.ylabel('Temps d\'exécution moyen (secondes)')
plt.title('Comparaison des temps d\'exécution de mix_list et random.shuffle')
plt.legend()
plt.grid(True)



#------------------------------------------------QUESTION 2------------------------------------------------------



def perf_mix_V2(func1: callable, func2: callable, list_sizes: list, num_executions: int , nb_elm: int):
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
            func1(input_list , nb_elm)
            end_time = time.perf_counter()
            total_time_func1 += end_time - start_time

            # Recréez la liste pour func2 car elle modifie la liste en place
            input_list = [random.randint(0, 1000) for y in range(size)]

            # Mesurez le temps d'exécution pour func2
            start_time = time.perf_counter()
            func2(input_list , nb_elm)
            end_time = time.perf_counter()
            total_time_func2 += end_time - start_time

        # Calculez le temps d'exécution moyen pour chaque fonction
        avg_time_func1 = total_time_func1 / num_executions
        avg_time_func2 = total_time_func2 / num_executions

        results_func1.append(avg_time_func1)
        results_func2.append(avg_time_func2)

    return (results_func1, results_func2)



# Liste de tailles de listes à tester
list_sizes = [10, 500, 5000, 50000, 100000]


# Nombre d'exécutions moyen pour calculer la moyenne des temps
num_executions = 10

# Nombre d'éléments à extraire de chaque liste
int_nbr_of_element_to_extract = 5

# Obtenez les résultats des temps d'exécution moyens pour extract_elements_list et random.sample
results_2 = perf_mix_V2(Exo_4_AT4.extract_elements_list, random.sample, list_sizes, num_executions, int_nbr_of_element_to_extract)

# Extrayez les résultats pour chaque fonction
times_extract_elements_list = results_2[0]
times_sample = results_2[1]

# Tracer les courbes des temps d'exécution en fonction de la taille des listes
plt.figure()
plt.plot(list_sizes, times_extract_elements_list, label='extract_elements_list', marker='o')
plt.plot(list_sizes, times_sample, label='random.sample', marker='x')
plt.xlabel('Taille de la liste')
plt.ylabel('Temps d\'exécution moyen (secondes)')
plt.title('Comparaison des temps d\'exécution de extract_elements_list et random.sample')
plt.legend()
plt.grid(True)




plt.show()


