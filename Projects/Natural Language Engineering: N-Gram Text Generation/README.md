# Natural Language Text Generation & Analysis 🗣️🤖

## Overview
This project explores the core concepts of Natural Language Engineering (NLE) by implementing and evaluating statistical language models. The system is designed to train on text corpora, calculate probability distributions for word sequences, and generate novel text. To contextualize the model's output, the generated sentences and metrics were woven into an original, creative science-fiction narrative that highlights the amusing and disjointed nature of probabilistic text generation.

## Key Features
* **Probabilistic Language Modeling:** Implements text generation algorithms (likely N-gram models) that predict and output subsequent words based on the statistical frequency of preceding word sequences in the training corpus.
* **Metric Evaluation:** Calculates foundational NLP metrics (such as sentence probabilities and perplexity) to quantitatively evaluate the predictability and performance of the trained language model.
* **Creative Text Integration:** Features a unique storytelling component where raw, AI-generated sentences—ranging from medical billing fragments to radiation counters—are embedded into a narrative about a scientist ("Dr. Aris") attempting to communicate with a fractured AI ("Unit 9"). 
* **Data Preprocessing & Tokenization:** Includes the necessary text normalization, tokenization, and vocabulary building required to process raw text corpora into machine-readable formats.

## System Outputs
As documented in the `sentences_and_metrics.txt` and `generated_story.txt` files, the model generates sentences like:
> *"When you are mending your daughter's coat, you are supplying the family buttons have fallen off the field of extra-sensory perception..."*

While lacking global semantic coherence, the output accurately reflects the local syntactical structures learned during the model's training phase, demonstrating a successful implementation of Markov assumption-based text generation.

## Technologies Used
* **Language:** Python
* **Environment:** Jupyter Notebook (`nle_assignment.ipynb`)
* **Concepts:** Natural Language Processing (NLP), N-gram Language Models, Tokenization, Text Generation Metrics