# AI Search Algorithms: 8-Puzzle Solver 🧩🤖

## Overview
This project is an Artificial Intelligence search algorithm simulator built in Java to solve the classic 8-puzzle problem. It explores the mathematical efficiency of state-space search by implementing and comparing an uninformed search method (Uniform Cost Search) against an informed, heuristic-driven method (A* Search). The system systematically generates valid board states, prevents cyclical loops, and outputs the optimal sequence of moves to reach the goal state.

## Key Features
* **A* Search with Heuristics:** Implements the A* algorithm ($f(n) = g(n) + h(n)$), utilizing the Manhattan Distance heuristic to estimate the optimal path to the goal, drastically reducing the number of nodes the system needs to expand.
* **Uniform Cost Search (UCS):** Includes a baseline UCS algorithm that explores all possible moves with the lowest current cost, serving as an experimental control to demonstrate the efficiency of heuristic functions.
* **Dynamic State Generation:** The engine algorithmically identifies the empty tile ('0') and calculates all valid adjacent moves (Up, Down, Left, Right), strictly enforcing board boundary conditions to generate legal successor states.
* **Statistical Performance Logging:** The program tracks and outputs the total execution time, the number of moves in the optimal solution, and the total counts of both expanded and unexpanded nodes to comparative text files.

## Project Architecture
* `PuzzleState.java`: The foundational data model. It represents the 3x3 board matrix, handles deep cloning of arrays for state creation, and generates the list of valid successor moves.
* `PuzzleNode.java`: The structural component of the search tree. It extends the base state to include algorithmic tracking variables like `goalCost` ($g$), `heuristicCost` ($h$), and parent node references to allow for backtracking the final solution path.
* `PuzzleSolver.java`: The execution engine. It contains the core logic for the search queue, the Manhattan distance calculator, the user interaction menu, and the file I/O operations for logging results.

## Performance Analysis
As documented in the project's analytical report, the A* Search algorithm demonstrated significant optimization over the Uniform Cost Search. For a standard 30-move solution, the Manhattan Distance heuristic guided the A* algorithm to a solution in a fraction of the time (approx. 226ms) compared to the unguided UCS approach (approx. 1496ms), expanding far fewer unnecessary nodes in the process.

## Technologies
* **Language:** Java
* **Concepts:** Artificial Intelligence, Heuristic Evaluation, Graph/Tree Traversal, State-Space Modeling