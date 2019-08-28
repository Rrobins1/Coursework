import csv

def writeResults (targetFilePath, classifier, dataType, precision, recall, f1, datasetSize, dataPath, elapsedTime):
    file = open(targetFilePath, "a+", newline='')
    fileWriter = csv.writer(file, quotechar="", quoting=csv.QUOTE_NONE, delimiter=',', escapechar='\\')

    fileWriter.writerow([
        classifier,
        dataType,
        precision,
        recall,
        f1,
        datasetSize,
        dataPath,
        elapsedTime
    ])
    file.close()