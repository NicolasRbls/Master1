const football = [
    {club: 'SCB', joueur: 'SANTELLI', but: 2},
    {club: 'SCB', joueur: 'MAGRI', but: 1},
    {club: 'HAC', joueur: 'KITALA', but: 3},
    {club: 'SCB', joueur: 'ROBIC', but: 3},
    {club: 'BORDEAUX', joueur: 'MAJA', but: 4},
  ];
  
  function calculerButsSCB(tableau) {
    // Ajouter 1 but à chaque joueur
    tableau.forEach(joueur => joueur.but += 1);
  
    // Calculer la somme des buts pour le club SCB
    const sommeButsSCB = tableau
      .filter(joueur => joueur.club === 'SCB')
      .reduce((acc, joueur) => acc + joueur.but, 0);
  
    return sommeButsSCB;
  }
  
  console.log(calculerButsSCB(football));
  