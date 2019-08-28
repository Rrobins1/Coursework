import configuration
import train

##############################################################
#Configuration settings
#############################################################
testingClassifiers = configuration.getTestingClassifiers()
testingTypes = configuration.getTestingTypes()
selectedDataset = configuration.getSelectedDataset()
isPlottingConfusionMatrix = configuration.getPlotSetting()
scoringMetrics = configuration.getScoringMetrics()
isRecordingResults = configuration.getRecordingSetting()

#########################################################
# RUN
#########################################################
for dataFile in selectedDataset:
    for dataType in testingTypes:
        for classifier in testingClassifiers:
                train.testDataset( classifier, dataType, dataFile, scoringMetrics, isPlottingConfusionMatrix, isRecordingResults)