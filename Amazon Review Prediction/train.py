import configuration
import csv
import datetime
import matplotlib.pyplot
import numpy as np
import pandas as pd
import re
import scikitplot
import statistics
import string
import time
import resultWriter

from imblearn.under_sampling import RandomUnderSampler
from scipy.sparse import coo_matrix, hstack
from sklearn.preprocessing import FunctionTransformer
from sklearn.base import TransformerMixin
from sklearn.compose import ColumnTransformer
from sklearn.decomposition import PCA, TruncatedSVD
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import recall_score
from sklearn.model_selection import cross_validate, train_test_split, cross_val_score, cross_val_predict
from sklearn.naive_bayes import GaussianNB, MultinomialNB
from sklearn.neural_network import MLPClassifier
from sklearn.pipeline import Pipeline, FeatureUnion, FeatureUnion
from sklearn.svm import SVC, NuSVC
from sklearn.tree import DecisionTreeClassifier

def testDataset(classifier, dataType, selectedDataset, scoringMetrics, isPlottingConfusionMatrix, isRecordingResults):
    #####################################
    # Setup Data
    #####################################

    #Start the runtime clock
    start_time = time.time()
    
    classifier_name = classifier[0]
    classifier = classifier[1]
    
    if ( dataType == "boolean" ):
        binary_classification = True
    else:
        binary_classification = False

    #input data from CSV file
    input_data = pd.read_csv(selectedDataset)

    #define dataset
    dataset = {"review_body": input_data["review_body"].values, 
        "star_rating": input_data["star_rating"].values,
        "review_headline": input_data["review_headline"].values,
        "vine": input_data["vine"].values,
        "helpful_votes": input_data["helpful_votes"].values,
        "total_votes": input_data["total_votes"].values,
        "verified_purchase": input_data["verified_purchase"].values
        }
    dataset = pd.DataFrame(data = dataset)
    dataset = dataset.dropna() #drop missing values

    #Convert Vine and Verified_Purchase fields from Y/N to 1/0
    dataset["vine"] = dataset["vine"].apply(lambda yesno : 1 if yesno == 'Y' else 0)
    dataset["verified_purchase"] = dataset["verified_purchase"].apply(lambda yesno : 1 if yesno == 'Y' else 0)

    if binary_classification: # Binary: 4-5 stars "good" , 1-3 stars "bad"
        dataset["label"] = dataset["star_rating"].apply(lambda rating : +1 if str(rating) > '3' else -1)
    else:
        dataset["label"] = dataset["star_rating"] # star classification

    #This line omits 3 star reviews
    #dataset = dataset[dataset.star_rating != 3]

    tableFields = ["star_rating", "review_headline", "review_body", "star_rating", 
                "helpful_votes", "total_votes", "vine", "verified_purchase"]
    X = pd.DataFrame(dataset, columns = tableFields)
    y = pd.DataFrame(dataset, columns = ["label"])

    #####################################
    # Random Undersampling
    #####################################    
    rus = RandomUnderSampler(random_state=13)
    X, y = rus.fit_resample(X, y)
    X = pd.DataFrame(X, columns = tableFields)
    datasetSize = X.shape[0]

    #####################################
    # Vectorization / Feature Extraction
    #####################################

    # union of features on headline
    headUnion = FeatureUnion([ 
        ("emojis", FunctionFeaturizer(emojis)), # no effect
        ("count_exclamation_mark", FunctionFeaturizer(exclamation)),
        ('capitalization', FunctionFeaturizer(capitalizationRatio)),
        #("dots", FunctionFeaturizer(dots)),
        configuration.getHeadlineVectorizer()
    ])

    # union of features on body
    bodyUnion = FeatureUnion([
        ("emojis", FunctionFeaturizer(emojis)), 
        ("count_exclamation_mark", FunctionFeaturizer(exclamation)),
        ("capitalization", FunctionFeaturizer(capitalizationRatio)),
        configuration.getBodyVectorizer()
    ])

    #join all transformers column-wise
    ct = ColumnTransformer([
        ("body_union", bodyUnion, "review_body"),
        ("head_union", headUnion, "review_headline"),
        ("helpvotes", FunctionTransformer(validate=False), ["helpful_votes"]), #helps on nu_svc
        ("totalvotes", FunctionTransformer(validate=False), ["total_votes"]), #helps on nu_svc
        ("vine", FunctionTransformer(validate=False), ["vine"]),
        ("verified_purchase", FunctionTransformer(validate=False), ["verified_purchase"])
    ])


    # fit & transorm the data
    X = ct.fit_transform(X)
    
    #####################################
    ## Training with cross validation
    #####################################

    scores = cross_validate(classifier, X, y.ravel(), scoring=scoringMetrics, cv=5, n_jobs = -1, return_train_score=True)

    #stop the clock, compute runtime
    finish_time = time.time()
    elapsed_time = round(finish_time-start_time, 1)

    #Performance Metrics: f1, Precision, Recall
    averageF1 = round(statistics.mean(scores['test_f1_macro']), 3)
    averagePrecision = round(statistics.mean(scores['test_precision_macro']), 3)
    averageRecall = round(statistics.mean(scores['test_recall_macro']), 3)

    print(datetime.datetime.now())
    print( "Classifier: " + classifier_name + "\tIs Binary: " + str(binary_classification))
    print( "Precision Score: " + str(averagePrecision))
    print( "Recall Score: " + str(averageRecall))
    print( "f1 Score: " + str(averageF1))
    print( "Elapsed Time: " + str(elapsed_time) + " seconds")

    if isRecordingResults:
        resultWriter.writeResults("Results/Amazon_Review_Results.csv", classifier_name, dataType, averagePrecision, averageRecall, averageF1, datasetSize, selectedDataset, elapsed_time  )
    
    if isPlottingConfusionMatrix:
        imageLabel = "Results/" + classifier_name + dataType + str(finish_time) + ".png"
        plotConfusionMatrix(classifier, X, y, imageLabel )

def plotConfusionMatrix(classifier, test_vector, test_y, label):
    predictions = cross_val_predict(classifier, test_vector, test_y.ravel(), cv = 5, n_jobs = -1)
    scikitplot.metrics.plot_confusion_matrix( test_y.ravel(), predictions, normalize=True)
    matplotlib.pyplot.savefig(label) ##add timestamp to title to preserve multiples
    matplotlib.pyplot.show()


#########################################################
# CUSTOM FEATURES
#########################################################

# Feature Functions. Write functions which take the document text
# as a parameter and return a single integer.
# For example: emojis(text) returns the count of ":(" emojis in the text.

def caps(text):
    runs = sorted(re.findall(r"[A-Z]+", text), key=len)
    if runs:
        return len(runs[-1])
    else:
        return 0

def emojis(text):
    sadface = len(re.findall(":\(", text)) + len(re.findall("\):", text)) + len(re.findall(":'\(", text)) + len(re.findall("\)':", text))
    happyface = len(re.findall(":\)", text)) + len(re.findall("\(:", text)) + len(re.findall(":D", text)) + len(re.findall("XD", text)) + len(re.findall(":3", text))
    if sadface > happyface: return 0
    return 1

def exclamation(text):
    return len(re.findall("!", text))

def dots(text):
    return len(re.findall("...", text))
    #This feature meant to detect long pauses.
    # such as: "This thing is ... ok"
    # seems to improve performance on headlines ONLY
    # (tested with NB and nu_svc)

def length(text):
    return len(text)

def capitalizationRatio(text):
    return len(re.findall(r"[A-Z]", text)) / len(text)

def question(text):
    return len(re.findall("\?", text))

# The FunctionFeaturizer implements a transformer which can be used in a Feature Union pipeline.
# It allows you to specify the function with which to transform the data, and applies
# the function to each resulting vector in the dataset

class FunctionFeaturizer(TransformerMixin):
    def __init__(self, *featurizers):
        self.featurizers = featurizers

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        fvs = []
        for datum in X:
            fv = [f(datum) for f in self.featurizers]
            fvs.append(fv)
        return np.array(fvs)