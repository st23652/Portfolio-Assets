# Minimum Spanning Tree & Graph Analyzer 🕸️

## Overview
This project implements robust graph traversal and optimization algorithms in Java to analyze both specific datasets (such as the simulated "Graph of Essex") and randomly generated grid networks. The core application computes Spanning Trees by efficiently managing vertices, tracking edge weights, and maintaining graph connectivity.

## Key Features
* **Component Tracking:** Determines the total number of connected components within a graph utilizing a recursive Depth-First Search (DFS) algorithm to track visited vertices.
* **Random Graph Generation:** Dynamically builds undirected grid graphs of dimensions $x \times y$. Adjacent nodes (right and below) are connected based on a configurable probability parameter ($p$) and assigned random weights.
* **Preprocessing Optimization:** Implements a queue-based preprocessing sequence to temporarily identify, detach, and store degree-1 edges. This streamlines the primary spanning tree calculation by reducing the graph's complexity.
* **Spanning Tree Algorithm:** Computes the MST by sorting all edges in descending order of weight. The system iterates through the sorted list, strategically deleting edges as long as the removal does not alter the total number of connected components, effectively running a reverse-delete algorithm. Preprocessed edges are safely re-attached at the conclusion.

## Statistical Testing & Output
The application's execution module features a comprehensive statistical testing suite. It processes $1000$ randomly generated $10 \times 10$ graphs (with $p = 2/3$) to output the following metrics:
* Average total edge weight before preprocessing.
* Average edge weight after degree-1 pruning.
* Average number of connected components.
* The average total edge weight of the final calculated Minimum Spanning Trees.