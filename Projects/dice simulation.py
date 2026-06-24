import random

again = "do you want to play again"

while True:  
  dice = random.randint(1, 6)  
  print("rolling the dice: ", dice)
  answer = input(again).lower()  

  if answer == "y" or answer == "yes":
    continue  
  else:
    break  

print("game over")
