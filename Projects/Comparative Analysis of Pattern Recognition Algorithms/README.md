# Pattern Recognition & Machine Learning Evaluation
Comparative study of classification performance on image databases.

## Implementation: Learner Pipeline
I implemented a standardized training and testing workflow to systematically compare the performance of Support Vector Machines (SVM) and Multi-Layer Perceptrons (MLP).

```python
# Standardized learner pipeline
from sklearn import svm
from sklearn.neural_network import MLPClassifier

# Training the learner
# SVM is configured with a specific gamma for image data
clf = svm.SVC(gamma=0.001)
clf.fit(train_data, train_labels)

# Testing predictions on unseen data
predictions = clf.predict(test_data)

# Evaluating accuracy
accuracy = fact.evaluate(predictions, test_labels)

```

How it was done
Data Preparation: Used the Olivetti face database and the MNIST digit dataset, retaining specific image subsets for training versus testing to ensure unbiased evaluation.

Comparison: Systematically trained both Support Vector Machines (SVM) and Multi-Layer Perceptrons (MLP) on the same data partitions.

Evaluation: Utilized classification accuracy metrics (FACT) to perform a comparative study, identifying which learner architecture was most effective under specific dataset size constraints.

![Image of a neural network architecture diagram](image.png)