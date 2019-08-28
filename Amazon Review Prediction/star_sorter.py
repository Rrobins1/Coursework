import csv
import numpy
import pandas

max_size =2147483647
csv.field_size_limit(max_size)

base_path = 'Amazon Review Datasets/' 
videoPath = 'amazon_reviews_Video_Games.csv'
kitchenPath = 'amazon_reviews_kitchen.csv'
video_label = 'video_games'
kitchen_label = 'kitchen'


def rowToString (row):
    output = ""
    for item in row:
        output += item + ','
    output = output[:-1]
    output += '\n'
    return output

def setTargets(target_path):
    global one_star_path, two_star_path, three_star_path, four_star_path, five_star_path
    global file_one, file_two, file_three, file_four, file_five
    global writer_one, writer_two, writer_three, writer_four, writer_five

    one_star_path = target_path + '_one_star.csv'
    two_star_path = target_path+ '_two_star.csv'
    three_star_path = target_path + '_three_star.csv'
    four_star_path = target_path + '_four_star.csv'
    five_star_path = target_path + '_five_star.csv'


    file_one = open(one_star_path, 'w', encoding="utf8")
    file_two = open(two_star_path, 'w', encoding="utf8 ")
    file_three = open(three_star_path, 'w', encoding="utf8 ")
    file_four = open(four_star_path, 'w', encoding="utf8")
    file_five = open(five_star_path, 'w', encoding="utf8")

    writer_one = csv.writer(file_one, quotechar="", quoting=csv.QUOTE_NONE)
    writer_two = csv.writer(file_two, quotechar="", quoting=csv.QUOTE_NONE)
    writer_three = csv.writer(file_three, quotechar="", quoting=csv.QUOTE_NONE)
    writer_four = csv.writer(file_four, quotechar="", quoting=csv.QUOTE_NONE)
    writer_five = csv.writer(file_five, quotechar="", quoting=csv.QUOTE_NONE)


def sortCSV (csv_path, target_path):
    setTargets(target_path)
    with open(csv_path, encoding='utf8') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                row = rowToString(row)
                file_one.write(row)
                file_two.write(row)
                file_three.write(row)
                file_four.write(row)
                file_five.write(row)
                line_count = 1

            else:
                score = row[7]
                score = score[0]

                row = rowToString(row)
                if score == str(1):
                    file_one.write(row)
                    
                elif score == str(2):
                    file_two.write(row)

                elif score == str(3):
                    file_three.write(row)
                    
                elif score == str(4):
                    file_four.write(row)

                elif score == str(5):
                    file_five.write(row)

    print("Finished Sorting " + csv_path)


video_csv = base_path + videoPath
video_target = base_path + video_label

kitchen_csv = base_path + kitchenPath
kitchen_target = base_path + kitchen_label

sortCSV(video_csv, video_target)
sortCSV(kitchen_csv, kitchen_target)