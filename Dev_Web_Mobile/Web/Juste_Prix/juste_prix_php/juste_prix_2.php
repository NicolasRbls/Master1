<?php
session_start();

if (!isset($_SESSION["correctPrice"])) {
    $_SESSION["correctPrice"] = rand(1, 10);
}

$correctPrice = $_SESSION["correctPrice"];
$playerName = (!empty($_SESSION["playerName"])) ? $_SESSION["playerName"] : "";
$message = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $userGuess = $_POST["guessInput"];
    $_SESSION["playerName"] = $_POST["nameInput"];

    if (!is_numeric($userGuess)) {
        $message = "Veuillez entrer un nombre valide.";
    } else {
        if ($userGuess == $correctPrice) {
            $message = "Bravo, $playerName ! Vous avez trouvé le juste prix !";
            unset($_SESSION["correctPrice"]); 
            unset($_SESSION["playerName"]); 

        } else {
            if ($userGuess < $correctPrice) {
                $message = "Trop bas. Essayez encore.";
            } else {
                $message = "Trop élevé. Essayez encore.";
            }
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Juste Prix</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        input[type="text"], input[type="number"] {
            padding: 10px;
            font-size: 18px;
        }
        #result {
            font-size: 24px;
            font-weight: bold;
            margin-top: 10px;
            border: 2px solid #000;
            padding: 10px;
            display: <?php echo !empty($message) ? 'block' : 'none'; ?>;
            color: #333;
            background-color: #fff;
        }
    </style>
</head>
<body>
    <h1>Juste Prix</h1>
    
    <form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
        <p>Saisissez un pseudo :</p>
        <input type="text" name="nameInput" <?php if (!empty($_SESSION["playerName"])) echo 'value="' . $_SESSION["playerName"] . '"'; ?> required><br>
        <p>Devinez le prix de l'objet :</p>
        <p>Indice : Entre 1 et 10</p>
        <input type="number" name="guessInput" required>
        <input type="submit" value="Devinez!">
    </form>
    
    <div id="result"><?php echo $message; ?></div>
</body>
</html>
