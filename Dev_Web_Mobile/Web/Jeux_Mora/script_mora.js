let playerScore = 0;
let computerScore = 0;
let playerPseudo = "Player";

function updatePseudo() {
    document.getElementById('player-score').textContent = `${playerPseudo}: 0`;
  }

function startGame() {
    updatePseudo();
    document.getElementById('play-button').addEventListener('click', playGame);
  }
  
  

function playGame() {
  const playerFingers = parseInt(document.getElementById('player-fingers').value);
  const playerGuess = parseInt(document.getElementById('player-guess').value);
  const computerFingers = Math.floor(Math.random() * 6);
  const computerGuess = Math.floor(Math.random() * 11);
  const totalFingers = playerFingers + computerFingers;

  document.getElementById('computer-play').innerHTML = `Computer showed ${computerFingers} finger(s) and guessed ${computerGuess}.`;

  let resultMessage = `${playerPseudo} showed ${playerFingers} finger(s) and guessed ${playerGuess}.<br>`;
  resultMessage += `Total fingers: ${totalFingers}.<br>`;

  if (playerGuess === totalFingers && computerGuess !== totalFingers) {
    resultMessage += `${playerPseudo} wins this round!`;
    playerScore++;
  } else if (computerGuess === totalFingers && playerGuess !== totalFingers) {
    resultMessage += "Computer wins this round!";
    computerScore++;
  } else {
    resultMessage += "No one wins this round, try again!";
  }

  document.getElementById('game-result').innerHTML = `<h2>Result</h2><p>${resultMessage}</p>`;
  document.getElementById('player-score').textContent = `${playerPseudo}: ${playerScore}`;
  document.getElementById('computer-score').textContent = `${computerScore}`;

  if (playerScore === 10) {
    alert(`${playerPseudo} wins the game!`);
    resetGame();
  } else if (computerScore === 10) {
    alert("Computer wins the game!");
    resetGame();
  }
}


function enableGame() {
const pseudoInput = document.getElementById('player-pseudo').value.trim();
if (pseudoInput && pseudoInput !== playerPseudo) {
    playerPseudo = pseudoInput;
    document.getElementById('player-fingers').disabled = false;
    document.getElementById('player-guess').disabled = false;
    document.getElementById('play-button').disabled = false;
    updatePseudo();
    resetGame(); // Réinitialise le jeu si le pseudo change
    }
}

function resetGame() {
    playerScore = 0;
    computerScore = 0;
    document.getElementById('player-score').textContent = `${playerPseudo}: 0`;
    document.getElementById('computer-score').textContent = `0`;
    document.getElementById('game-result').innerHTML = `<h2>Result</h2><p>Make your guess and see what happens!</p>`;
    document.getElementById('player-fingers').value = '';
    document.getElementById('player-guess').value = '';
}


document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('start-button').addEventListener('click', startGame);
    document.getElementById('start-button').addEventListener('click', enableGame);
    startGame();
});


