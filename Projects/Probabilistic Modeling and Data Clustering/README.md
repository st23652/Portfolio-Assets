# Probabilistic Modeling and Data Clustering
Modeling causal relationships and unsupervised data partitioning.

## Features
- Construction and inference on Bayesian Belief Networks (BBNs).
- Implementation of K-means clustering on 2D and 3D synthetic data.
- Evaluation of clustering quality using the Elbow test and Silhouette score.

## Technical Implementation: Clustering Logic
Utilizing `scikit-learn` to partition high-dimensional data by iteratively assigning points to the nearest centroid.

```python
from sklearn.cluster import KMeans

# Initialize KMeans with 3 clusters
kmeans = KMeans(n_clusters=3, random_state=42)

# Fit the model to synthetic data
kmeans.fit(data)

# Predict clusters and extract centroids
labels = kmeans.predict(data)
centroids = kmeans.cluster_centers_

```

How it was done

Bayesian Networks: Defined network structure and tabular Conditional Probability Distributions (CPDs) to perform causal inference on the "Alarm" network using the pgmpy library.

Clustering: Generated Gaussian blobs in 2D and 3D space, applied the K-means algorithm to find optimal centroids, and visualized the results using Matplotlib and Axes3D.

Evaluation: Used the Elbow method and Silhouette score to determine the optimal number of clusters ($k$) for the synthetic datasets.