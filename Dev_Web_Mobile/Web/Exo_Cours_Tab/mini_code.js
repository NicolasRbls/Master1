function checkCode() {
    // Votre code secret
    const secretCode = ['0', '4', '2'];
    let userCode = [
      document.getElementById('input1').value,
      document.getElementById('input2').value,
      document.getElementById('input3').value
    ];
  
    if (userCode.length === 3 && userCode.every(num => num !== "")) {
      if (userCode[0] === secretCode[0] && userCode[1] === secretCode[1] && userCode[2] === secretCode[2]) {
        document.getElementById('result').textContent = 'Le code est correct !';
      } else {
        document.getElementById('result').textContent = 'Code incorrect, essayez encore.';
      }
    } else {
      document.getElementById('result').textContent = 'Veuillez entrer un numéro dans chaque case.';
    }
  }
  