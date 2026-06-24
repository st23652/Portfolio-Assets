import time

para = ("This is a test paragraph for the typing speed game. I hope your day went well. I hope your day still goes well.")

def speedTest():
    print("You should try typing the sentences as quickly as possible:")
    print(para)
    print("Press enter when ready:")
    input()

    start = time.time()

    textTyped = input("Start the typing here:\n")

    end = time.time()

    totalTime = end - start

    totalWords = len(para.split())
    wordsPerMin = (totalWords / totalTime) * 60

    matchedWords = sum(1 for x, y in zip(para, textTyped) if x == y)
    accuracy = (matchedWords / len(para)) * 100

    print(f"Total time taken: {totalTime:.2f} seconds")
    print(f"Speed of typing: {wordsPerMin:.2f} words per minute")
    print(f"Accuracy: {accuracy:.2f}%")

if __name__ == "__main__":
    speedTest()
