# AI Puzzle Solver: Fox, Goose, & Corn 🦊🦆🌽

## Overview
This project is an algorithmic solver for the classic river crossing logic puzzle, built in Java. It demonstrates foundational Artificial Intelligence concepts by representing the game as a state-space graph and deploying a Breadth-First Search (BFS) algorithm to find the optimal solution. The program evaluates safe versus unsafe states, prevents cyclical moves, and tracks algorithm performance metrics.

## Key Features
* **State-Space Representation:** The `Game` class digitally models the positions of the Farmer, Fox, Goose, and Corn (Left or Right bank) and algorithmically determines if a state is "safe" (e.g., ensuring the Fox and Goose are never left alone without the Farmer).
* **Breadth-First Search (BFS):** The `Solver` class implements a complete and optimal BFS algorithm, utilizing queue-based data structures to systematically explore unexpanded nodes while keeping a record of previously visited states.
* **Optimal Path Generation:** The `Node` class encapsulates the game state alongside its parent node and traversal cost. Once the goal state is reached, the system traces the parent references backward to output the exact sequence of optimal moves.
* **Performance Analytics:** Upon completion, the solver outputs the exact number of moves required, alongside the total number of expanded and unexpanded nodes, providing insight into the algorithm's time and space complexity.

## Project Architecture
* `Game.java`: The data model and rules engine. It handles board cloning, state validation, and the generation of all legal child moves from a current position.
* `Node.java`: The structural component of the search tree, managing state payloads, path costs, and historical lineage (parent tracking).
* `Solver.java`: The core execution engine containing the BFS logic, state initialization, and the final solution reporting system.

## Concepts Demonstrated
* Artificial Intelligence (Classical Search)
* Graph and Tree Traversal Algorithms
* State-Space Modeling and Constraints Validation
* Java Data Structures (ArrayList, Objects, Graph Nodes)