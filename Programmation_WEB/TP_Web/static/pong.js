const gameContainer = document.querySelector(".game-container");
const ball = document.getElementById("ball");
const playerPaddle = document.getElementById("player-paddle");
const aiPaddle = document.getElementById("ai-paddle");

let ballX = 400;
let ballY = 200;
const initialBallSpeedX = parseInt(sessionStorage.getItem("ballSpeed")) ;
const initialBallSpeedY = initialBallSpeedX;
let ballSpeedX = initialBallSpeedX;
let ballSpeedY = initialBallSpeedY;

const aiSpeed = parseInt(sessionStorage.getItem("aiDifficulty"));

const ballDiameter = 15;
const containerWidth = gameContainer.offsetWidth;
const containerHeight = gameContainer.offsetHeight;
const paddleHeight = 80;
const paddleWidth = 10;

let playerPaddleY = 160;
let aiPaddleY = 160;
let gameStarted = false;

let animationFrameId;

let playerScore = 0;
let aiScore = 0;

let playerMoveUp = false;
let playerMoveDown = false;

let isGamePaused = false;

// Récupérer le nom de l'utilisateur depuis sessionStorage
const username = sessionStorage.getItem("username") || "Joueur";
// Initialisation de la série de victoires depuis sessionStorage (ou 0 par défaut)
let winStreak = parseInt(sessionStorage.getItem("winStreak")) || 0;

let totalScore = parseInt(sessionStorage.getItem("totalScore")) || 0;

document.addEventListener("keydown", (event) => {
    if (event.key === "Enter" && !gameStarted) {
        startGame();
    }
});

window.addEventListener("beforeunload", () => {
    // Envoie le score cumulé à la base de données avant de quitter si c'est un record
    updateHighScore(totalScore);
    sessionStorage.removeItem("totalScore");
    sessionStorage.removeItem("winStreak");
    sessionStorage.removeItem("ballSpeed");
    sessionStorage.removeItem("aiDifficulty");
});

// Fonction pour démarrer le jeu
function startGame() {
    gameStarted = true;
    const startScreen = document.getElementById("start-screen");
    if (startScreen) startScreen.remove(); // Supprime le message de démarrage
    resetBall();
    cancelAnimationFrame(animationFrameId);
    gameLoop();
}

document.addEventListener("keydown", (event) => {
    if (event.key === "ArrowUp") playerMoveUp = true;
    if (event.key === "ArrowDown") playerMoveDown = true;
});

document.addEventListener("keyup", (event) => {
    if (event.key === "ArrowUp") playerMoveUp = false;
    if (event.key === "ArrowDown") playerMoveDown = false;
});

function updatePaddlePosition() {
    if (playerMoveUp) playerPaddleY = Math.max(playerPaddleY - 4, 0);
    if (playerMoveDown) playerPaddleY = Math.min(playerPaddleY + 4, containerHeight - paddleHeight);
    playerPaddle.style.top = playerPaddleY + "px";
}

function resetBall() {
    ballX = containerWidth / 2;
    ballY = containerHeight / 2;

    ballSpeedX = initialBallSpeedX * (Math.random() > 0.5 ? 1 : -1);
    ballSpeedY = (Math.random() - 0.5) * initialBallSpeedY * 2;
}

function updateScore() {
    document.getElementById("player-score").innerText = `${username}: ${playerScore}`;
    document.getElementById("ai-score").innerText = `IA: ${aiScore}`;
}

function checkWinner() {
    if (playerScore >= 6 || aiScore >= 6) {
        const winner = playerScore >= 6 ? `${username}, vous avez gagné !` : "L'IA a gagné !";
        let finalScore = 0; // Initialise finalScore

        if (playerScore >= 6) {
            // Si victoire
            winStreak++;
            sessionStorage.setItem("winStreak", winStreak);
            const scoreMultiplier = (parseInt(sessionStorage.getItem("ballSpeed")) / 2) * (parseInt(sessionStorage.getItem("aiDifficulty")) / 2);
            const scoreDifference = Math.abs(playerScore - aiScore);
            finalScore = scoreDifference * scoreMultiplier * winStreak;

            totalScore += finalScore; // Cumul des scores de victoires consécutives
            sessionStorage.setItem("totalScore", totalScore);

            // Mettre à jour le score, cela vérifiera également si c'est un nouveau record
            updateHighScore(totalScore);
        } else {
            // En cas de défaite, réinitialiser winStreak et totalScore
            winStreak = 0;
            totalScore = 0;
            sessionStorage.setItem("winStreak", winStreak);
            sessionStorage.setItem("totalScore", totalScore);
        }

        // Afficher l'écran de fin
        const endScreen = document.createElement("div");
        endScreen.id = "end-screen";
        endScreen.innerHTML = `
            <p>${winner}</p>
            <p>Score de cette partie: ${finalScore.toFixed(0)} | </p>
            <p>Score cumulé: ${totalScore.toFixed(0)}</p>
            <button onclick="restartGame()">Rejouer</button>
            <button id="home-button">Retour à l'accueil</button>
        `;
        endScreen.style.cssText = "position: absolute; width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; background-color: rgba(0, 0, 0, 0.8); color: white; font-size: 24px;";
        document.body.appendChild(endScreen);

        // Gérer l'événement de clic pour le bouton "Retour à l'accueil"
        document.getElementById('home-button').addEventListener('click', () => {
            // Rediriger vers la page d'accueil
            location.href = '/home';
        });

        isGamePaused = true; // Met le jeu en pause
        gameStarted = false; // Indique que le jeu n'est plus en cours
        cancelAnimationFrame(animationFrameId); // Arrête la boucle d'animation
    }
}


function restartGame() {
    // Réinitialiser les scores
    playerScore = 0;
    aiScore = 0;
    updateScore();

    // Supprimer l'écran de fin
    const endScreen = document.getElementById("end-screen");
    if (endScreen) endScreen.remove();

    // Réinitialiser l'état du jeu
    isGamePaused = false;
    gameStarted = true;
    resetBall(); // Réinitialiser la position de la balle
    gameLoop();  // Démarrer la boucle de jeu
}


function gameLoop() {
    if (isGamePaused) return;

    updatePaddlePosition();

    ballX += ballSpeedX;
    ballY += ballSpeedY;

    if (ballY <= 0 || ballY >= containerHeight - ballDiameter) {
        ballSpeedY = -ballSpeedY;
    }

    if (ballX <= paddleWidth && ballY >= playerPaddleY && ballY <= playerPaddleY + paddleHeight) {
        ballSpeedX = -ballSpeedX;
    }

    if (ballX >= containerWidth - paddleWidth - ballDiameter && ballY >= aiPaddleY && ballY <= aiPaddleY + paddleHeight) {
        ballSpeedX = -ballSpeedX;
    }

    if (ballX <= 0) {
        aiScore++;
        resetBall();
        updateScore();
        checkWinner();
    } else if (ballX >= containerWidth - ballDiameter) {
        playerScore++;
        resetBall();
        updateScore();
        checkWinner();
    }

    ball.style.left = ballX + "px";
    ball.style.top = ballY + "px";

    if (ballY > aiPaddleY + paddleHeight / 2) {
        aiPaddleY = Math.min(aiPaddleY + aiSpeed, containerHeight - paddleHeight);
    } else if (ballY < aiPaddleY + paddleHeight / 2) {
        aiPaddleY = Math.max(aiPaddleY - aiSpeed, 0);
    }
    aiPaddle.style.top = aiPaddleY + "px";

    animationFrameId = requestAnimationFrame(gameLoop);
}

// Fonction pour afficher le message de démarrage
function showStartScreen() {
    const startScreen = document.createElement("div");
    startScreen.id = "start-screen";
    startScreen.innerHTML = "<p>Appuyez sur 'Entrée' pour commencer le jeu</p>";
    startScreen.style.cssText = "position: absolute; width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; background-color: rgba(0, 0, 0, 0.8); color: white; font-size: 24px;";
    document.body.appendChild(startScreen);
}

function updateHighScore(score) {
    // Appel AJAX pour mettre à jour le score dans la base de données
    fetch('/update_score', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: sessionStorage.getItem("username"), score: score })
    })
    .then(response => response.json())
    .then(data => {
        console.log(data.message); // Affiche un message de confirmation
    })
    .catch(error => console.error('Erreur lors de la mise à jour du score:', error));
}

updateScore();
showStartScreen();
