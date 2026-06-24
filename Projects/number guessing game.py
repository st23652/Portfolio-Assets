import random

n = random.randrange(1,100)

guess = int(input("enter your number: "))

while n != guess:
    if guess < n:
        print("your guess is too low")
        guess = int(input("enter your number: "))
    elif guess > n:
        print("your guess is too high")
        guess = int(input("enter your number: "))
    else:
        break
print("you have guessed it right!")
        
