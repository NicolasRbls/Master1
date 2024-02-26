square :: Int -> Int
square x = x * x

quad :: Int -> Int
quad = square . square

greater :: Int -> Int -> Int
greater x y = if x > y then x else y

area :: Float -> Float
area r = pi * r * r

main = do
  n <- readLn
  let result = quad n
  putStrLn $ show result

  putStrLn "Entrer le premier nombre:"
  n1 <- readLn
  putStrLn "Entrer le deuxième nombre:"
  n2 <- readLn
  let result = greater n1 n2
  putStrLn $ "Le plus grand nombre est: " ++ show result

  putStrLn "Entrer le rayon du cercle:"
  r <- readLn
  let result = area r
  putStrLn $ "L'aire du cercle est : " ++ show result