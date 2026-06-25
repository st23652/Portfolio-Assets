# CIFAR-10 Image Classification with PyTorch 🖼️🧠

## Overview
This project involves the design, training, and evaluation of a Convolutional Neural Network (CNN) built with PyTorch to classify the CIFAR-10 image dataset. It focuses on practical deep learning principles, including dataset preprocessing, preventing overfitting through architecture design, and evaluating model performance using comprehensive metrics. 

## Key Features & Methodology
* **Data Augmentation & Preprocessing:** Implements robust data transformations on the training set—such as `RandomCrop(32, padding=4)` and `RandomHorizontalFlip()`—alongside strict channel normalization to improve model generalization.
* **Custom CNN Architecture:** Features a three-block convolutional structure designed to balance expressiveness and computational efficiency:
  * **Conv Blocks:** Utilizes `Conv2d` layers (scaling from 32 to 128 filters), `BatchNorm` for training stability, `ReLU` activations to prevent vanishing gradients, and `MaxPool` layers for spatial reduction.
  * **Classifier:** Connects to a `Flatten` layer, a 256-unit fully connected layer, dropout for regularization, and a final 10-unit output layer.
* **Rigorous Evaluation:** Evaluates the model using a curated 4,000-image test set. Performance is tracked beyond simple accuracy, utilizing Precision, Recall, F1-score, and detailed Confusion Matrices to analyze class-specific behavior.
* **Structured Data Splitting:** The 60,000 images are strategically divided into 50,000 for training, 6,000 for development/validation (hyperparameter tuning), and 4,000 for final testing to ensure unbiased evaluation.

## Technologies Used
* **Framework:** PyTorch (`torch`, `torchvision`, `torchaudio`)
* **Language:** Python (Jupyter Notebook environment)
* **Data Analysis & Visualization:** `scikit-learn` (for metrics), `matplotlib`, `seaborn`

## Future Scope
Identified areas for potential architectural enhancements include implementing more sophisticated networks (like ResNet-18), introducing learning rate schedulers (e.g., cosine annealing), and applying advanced augmentation methods like CutMix to further improve validation accuracy.