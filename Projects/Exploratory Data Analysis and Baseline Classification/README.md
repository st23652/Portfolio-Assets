# Exploratory Data Analysis and Baseline Classification
A project demonstrating proficiency in the Python data science stack and fundamental classification algorithms.

## Features
- Data cleaning and preprocessing with Pandas.
- Numerical vector operations and matrix manipulation using NumPy.
- Comparative analysis of probabilistic (Naive Bayes) and margin-based (SVM) classifiers.
- Visualization of decision boundaries to interpret classifier behavior.

## Technical Implementation
The project workflow follows standard `scikit-learn` practices for dataset loading, training/testing splits, and model evaluation.

```python
# Load and split dataset
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3)

# Initialize and train Naive Bayes
model = GaussianNB()
model.fit(X_train, y_train)

# Evaluate performance
predictions = model.predict(X_test)
print(f'Accuracy: {accuracy_score(y_test, predictions) * 100:.2f}%')

```

How it was done
Data Manipulation: Leveraged NumPy for vector/matrix operations and Pandas for structured data handling to prepare the Iris dataset.

Modeling: Implemented Gaussian Naive Bayes and SVM to perform multi-class classification.

Visualization: Utilized Matplotlib to generate decision boundary plots, which provided visual intuition for how different algorithms partition feature space.