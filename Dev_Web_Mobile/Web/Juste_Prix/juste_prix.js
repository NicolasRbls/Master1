document.addEventListener('DOMContentLoaded', function () {
    const correctPrice = Math.floor(Math.random() * 10) + 1;

    const nameInput = document.getElementById('nameInput');
    const guessInput = document.getElementById('guessInput');
    const guessSubmit = document.getElementById('guessSubmit');
    const result = document.getElementById('result'); 
    let attempts = 0;

    guessSubmit.addEventListener('click', function () {
        const userGuess = parseInt(guessInput.value);

        if (!isNaN(userGuess)) {
            attempts++;
            result.style.display = 'block'; 
            if (userGuess === correctPrice) {
                const playerName = nameInput.value.trim();
                if (playerName !== "") {
                    result.innerHTML = `Bravo, ${playerName} ! Vous avez trouvé le juste prix en ${attempts} tentatives.`;
                } 
                result.style.color = 'green';
                guessInput.disabled = true;
                guessSubmit.disabled = true;
            } else if (userGuess < correctPrice) {
                result.textContent = `Trop bas. Essayez encore.`;
                result.style.color = 'red';
            } else {
                result.textContent = `Trop élevé. Essayez encore.`;
                result.style.color = 'red';
            }
        } else {
            result.style.display = 'block'; 
            result.textContent = `Veuillez entrer un nombre valide.`;
            result.style.color = 'red';
        }
    });
});
