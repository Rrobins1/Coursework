# AmazonReviews
Amazon customer reviews sentiment classification / machine learning

Dependancies: if already present no need to reinstall

NumPy:
  python -m pip install --user numpy scipy matplotlib ipython jupyter pandas sympy nose
  conda install -c anaconda numpy

scikit-learn:
  pip install -U scikit-learn
  conda install scikit-learn

imbalanced learn: 
  pip install -U imbalanced-learn
  conda install -c conda-forge imbalanced-learn

scikitplot:  
  pip install scikit-plot
  c conda-forge scikit-plot


Set desired configuration settings in configuration.py
Run with python run.py

Files are by default read from folder in Amazon Review Datasets, a 10k review set is shown as a sample
Results are written to Results/ Amazon_Review_Results.csv if chosen to write to a csv.
Confusion matrices are stored in results as well.
