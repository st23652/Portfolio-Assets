# Non-Linear Classification and ANN Regression
Applying advanced kernels for non-linear classification and PyTorch for predictive regression modeling.

## Features
- Implementation of SVMs using Linear and RBF kernels.
- Visualization of decision boundaries for non-linear datasets.
- Construction of a simple ANN in PyTorch for regression tasks.
- Minimization of Mean Squared Error (MSE) using Stochastic Gradient Descent (SGD).

## Technical Implementation: SVM Kernels
Fitting an SVM with a Radial Basis Function (RBF) kernel to transform data into higher dimensions, allowing for non-linear separation.

```python
from sklearn.svm import SVC

# Initialize SVM with RBF kernel to handle non-linear relationships
clf = SVC(kernel='rbf', C=1.0, gamma='scale')
clf.fit(X_train, y_train)

# Predict and evaluate
y_pred = clf.predict(X_test)

```

How it was done
Non-Linear SVM: Explored soft-margin SVMs by tuning the regularization parameter C and comparing the effectiveness of Linear vs. RBF kernels on circular data patterns.

ANN Regression: Designed a simple regression model using PyTorch, defining a loss function (MSE) and an optimizer (SGD) to fit a predictive line to synthetic noisy data.

Evaluation: Used visual plots to compare real data points against model predictions and assessed correlation coefficients to quantify regression performance.