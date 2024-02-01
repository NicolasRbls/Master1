// script.js
let phrases = [
    "La vie est belle.",
    "Le soleil brille.",
    "Les oiseaux chantent.",
    "La programmation est amusante."
];
let tempsDebut;
let timerInterval;
let enCours = false;
let partiesJouees = 0;
let scores = [];
let scoreFinal = 0; // Ajout de la variable pour le score final

window.onload = () => {
    nouvellePartie();
    document.getElementById("inputUser").addEventListener("input", verifierEntree);
}

function nouvellePhrase() {
    let phraseIndex = Math.floor(Math.random() * phrases.length);
    document.getElementById("phrase").textContent = phrases[phraseIndex];
}


function verifierEntree() {
    let inputVal = document.getElementById("inputUser").value;
    let phraseActuelle = document.getElementById("phrase").textContent;
    if (!enCours) {
        tempsDebut = new Date();
        enCours = true;
        timerInterval = setInterval(mettreAJourChrono, 100); // Mettre à jour toutes les 100 millisecondes
    }

    if (inputVal === phraseActuelle) {
        clearInterval(timerInterval); // Arrêter le chronomètre
        let tempsFin = new Date();
        let tempsEcoule = (tempsFin - tempsDebut) / 1000;
        let taillePhrase = phraseActuelle.length;
        let score = Math.round((taillePhrase / tempsEcoule) * 100); // Calcul du score
        scores.push(score); // Ajouter le score à la liste des scores
        document.getElementById("temps").textContent = tempsEcoule.toFixed(2); // Afficher le temps avec deux décimales
        document.getElementById("score").textContent = `Score: ${score}`; // Afficher le score
        alert(`Bravo ! Vous avez terminé en ${tempsEcoule.toFixed(2)} secondes. Votre score est de ${score}.`);
        enCours = false;
        partiesJouees++;
        if (partiesJouees < 5) {
            nouvellePartie(); // Commencer une nouvelle partie
        } else {
            scoreFinal = scores.reduce((a, b) => a + b, 0); // Calculer le score final
            alert(`Parties terminées ! Score final : ${scoreFinal}`);
            document.getElementById("inputUser").value = ""; // Réinitialiser le champ de saisie
            document.getElementById("temps").textContent = "0.00"; // Réinitialiser le temps
            document.getElementById("score").textContent = ""; // Réinitialiser le score
            enCours = false;
            partiesJouees = 0; // Réinitialiser le nombre de parties jouées
            scores = []; // Réinitialiser la liste des scores
            scoreFinal = 0; // Réinitialiser le score final
            document.getElementById("inputUser").disabled = false; // Activer le champ de saisie
        }
    }
}

function mettreAJourChrono() {
    if (enCours) {
        let tempsActuel = new Date();
        let tempsEcoule = (tempsActuel - tempsDebut) / 1000;
        document.getElementById("temps").textContent = tempsEcoule.toFixed(2); // Mettre à jour le temps en direct avec deux décimales
    }
}

function nouvellePartie() {
    nouvellePhrase();
    document.getElementById("inputUser").value = ""; // Réinitialiser le champ de saisie
    enCours = false;
    document.getElementById("inputUser").disabled = false; // Activer le champ de saisie
}
