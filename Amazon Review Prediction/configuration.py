from sklearn.linear_model import LogisticRegression
from imblearn.under_sampling import RandomUnderSampler
from sklearn.neural_network import MLPClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier, VotingClassifier
from sklearn.naive_bayes import GaussianNB, MultinomialNB
from sklearn.svm import SVC, NuSVC
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer


#possible csv

video_game_data = [
  "Amazon Review Datasets/Video_games_trunc_10k.csv",
   #"Amazon Review Datasets/Video_games_trunc_20k.csv",
   #"Amazon Review Datasets/Video_games_trunc_30k.csv",
   #"Amazon Review Datasets/Video_games_trunc_40k.csv",
   #"Amazon Review Datasets/Video_games_trunc_50k.csv",
   #"Amazon Review Datasets/Video_games_trunc_60k.csv",
   # "Amazon Review Datasets/Video_games_trunc_70k.csv",
   # "Amazon Review Datasets/Video_games_trunc_80k.csv",
   # "Amazon Review Datasets/Video_games_trunc_90k.csv",
   # "Amazon Review Datasets/Video_games_trunc_100k.csv",
   # "Amazon Review Datasets/Video_games_trunc_250k.csv",
   # "Amazon Review Datasets/Video_games_trunc_500k.csv",
   # "Amazon Review Datasets/amazon_reviews_Video_Games.csv"
]

###############################################################
# BEGIN CONFIGURATION SETUP 
###############################################################

#features being selected
selected_dataset = video_game_data #dataset 
selected_classifiers = ["nb"] #selected classifier, optionsbelow
testing_types = ["categories"] #catagories or boolean

plot_confusion_matrix = False  #option to plot confusionmatrix
record_results = True #option to record results to csv

#use all options
use_all_classifiers = False #use all classifiers in the classifiers section below
use_all_types = False #use both boolean and 5 star categories

#headline vectorizer options
#choose one!
headlineVectorizer = ("vectorizer", CountVectorizer( token_pattern=r'\b\w+\b', ngram_range=(1,2)))
#headlineVectorizer = ("vectorizer", TfidfVectorizer( token_pattern=r'\b\w+\b', ngram_range=(1,2)))

#body vectorizer options
bodyVectorizer = ("vectorizer", TfidfVectorizer( token_pattern=r'\b\w+\b', stop_words="english"))
#bodyVectorizer = ("vectorizer", CountVectorizer( token_pattern=r'\b\w+\b', stop_words="english", ngram_range=(1,2)))

#############################################################
#Possible Options
#############################################################

def findClassifier (classifier_name):
    for element in classifiers:
        if element[0] == classifier_name:
            return element
    print("NO VALID CLASSIFIER CHOSEN!")
    quit()

#possible classifiers
votingClassifier = VotingClassifier(estimators= [ 
    ("nu_svc" , NuSVC(gamma = 'auto')),
    ("decision_tree" , DecisionTreeClassifier(max_depth=5)),
    ("random_forest" , RandomForestClassifier(max_depth=5, n_estimators=10, max_features=1)),
    ("mlp" , MLPClassifier(alpha=1)),
    ("nb" , MultinomialNB())
    ], voting='hard')

classifiers = [
    ("nu_svc" , NuSVC(gamma = 'auto')),
    #("svc" , SVC(gamma = 'auto') ),
    ("svc" , SVC(kernel="linear", C=0.025)),
    ("decision_tree" , DecisionTreeClassifier(max_depth=5)),
    #("random_forest" , RandomForestClassifier(max_depth=5, n_estimators=10, max_features=1)),
    #("mlp" , MLPClassifier(alpha=1)),
    ("nb" , MultinomialNB()),
    #("votingClassifier", votingClassifier)
]

#scoring metrics
scoringMetrics = ['precision_macro', 'recall_macro', 'f1_macro']

#possible options
options = [
    "review_headline",
    "review_body",
    "combined"
]

#possible types of classification
catagory_types = [
    "boolean",
    "catagories"
]

#possible labels
star_label = "star_rating"

############################################################
# END CONFIGURATION SET UP
############################################################
testing_classifiers = []

for classifier in selected_classifiers:
    testing_classifiers.append(findClassifier(classifier))

if use_all_classifiers:
    testing_classifiers = classifiers

if use_all_types:
    testing_types = catagory_types

def getTestingClassifiers():
    return testing_classifiers

def getTestingTypes():
    return testing_types

def getSelectedDataset():
    return selected_dataset

def getPlotSetting():
    return plot_confusion_matrix

def getScoringMetrics():
    return scoringMetrics

def getRecordingSetting():
    return record_results

def getHeadlineVectorizer():
    return headlineVectorizer

def getBodyVectorizer():
    return bodyVectorizer