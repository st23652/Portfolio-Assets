import random

def wordChoice():
    wordBank = ["black", "red", "orange", "yellow", "white", "blue", "green", "pink", "purple"]
    return random.choice(wordBank)

def display(word, guessedLetters):
    return " ".join([letter if letter in guessedLetters else "_" for letter in word])

def wordGuessGame():
    word = wordChoice()
    guessedLetters = set()
    attempts = 6
    print("Welcome to Guess the Word Game!")
    
    while attempts > 0:
        print("\nWord to guess:", display(word, guessedLetters))
        guess = input("Guess a letter: ").lower()

        if len(guess) != 1 or not guess.isalpha():
            print("Please enter a single alphabet letter.")
            continue

        if guess in guessedLetters:
            print("You have already guessed that letter. Try again.")
            continue

        guessedLetters.add(guess)

        if guess in word:
            print(f"Good job! '{guess}' is in the word.")
        else:
            attempts -= 1
            print(f"Sorry, '{guess}' is not in the word. You have {attempts} attempts left.")

        if set(word).issubset(guessedLetters):
            print("\nCongratulations! You have guessed the word:", word)
            break
    else:
        print("\nGame Over! You have used all your attempts. The word was:", word)

if __name__ == "__main__":
    wordGuessGame()
