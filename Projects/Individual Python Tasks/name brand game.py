import random
import string
from nltk.corpus import words

import nltk
nltk.download('words')

def instructions():
    print("Welcome to the Name, Place, Animal, Brand Game!")
    print("A letter will be given and you need to give an animal name, brand, place, and name starting with that letter.")
    print("Type 'exit' if you want to end the game.")
    print("If your words match with the computer's, you will lose; otherwise, you win.\n")

def get_random_letter():
    return random.choice(string.ascii_uppercase)

def get_input(prompt):
    user_input = input(prompt).strip()
    if user_input.lower() == 'exit':
        print("Thank you for playing!")
        exit()
    return user_input

def generate_random_word_starting_with(letter):
    # Filter English words based on the given letter
    filtered_words = [word for word in words.words() if word.lower().startswith(letter.lower())]
    return random.choice(filtered_words) if filtered_words else ''

def get_computer_entries(letter):
    return {
        'name': generate_random_word_starting_with(letter),
        'place': generate_random_word_starting_with(letter),
        'animal': generate_random_word_starting_with(letter),
        'brand': generate_random_word_starting_with(letter)
    }

def play_game():
    instructions()
    
    while True:
        letter = get_random_letter()
        print(f"\nThe letter is: {letter}")
        
        user_name = get_input("Name: ")
        user_place = get_input("Place: ")
        user_animal = get_input("Animal: ")
        user_brand = get_input("Brand: ")
        
        computer_entries = get_computer_entries(letter)
        
        print("\nYour entries:")
        print(f"Name: {user_name}")
        print(f"Place: {user_place}")
        print(f"Animal: {user_animal}")
        print(f"Brand: {user_brand}")
        
        print("\nComputer's entries:")
        print(f"Name: {computer_entries['name']}")
        print(f"Place: {computer_entries['place']}")
        print(f"Animal: {computer_entries['animal']}")
        print(f"Brand: {computer_entries['brand']}")
        
        if (user_name.lower() == computer_entries['name'].lower() or
            user_place.lower() == computer_entries['place'].lower() or
            user_animal.lower() == computer_entries['animal'].lower() or
            user_brand.lower() == computer_entries['brand'].lower()):
            print("\nYou lose! Some of your entries matched with the computer's.")
        else:
            print("\nYou win! None of your entries matched with the computer's.")
        
        play_again = input("\nDo you want to play another round? (yes/no): ").strip().lower()
        if play_again != 'yes':
            print("Thanks for playing!")
            break

if __name__ == "__main__":
    play_game()
