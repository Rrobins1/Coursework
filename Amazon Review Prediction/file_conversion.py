import csv
import numpy
import pandas



base_path = 'Amazon Review Datasets/'
videoPath = "amazon_reviews_us_Video_Games_v1_00.tsv"
kitchenPath = "amazon_reviews_us_Kitchen_v1_00.tsv"
videoWritePath = 'amazon_reviews_Video_Games.csv'
kitchenWritePath = 'amazon_reviews_kitchen.csv'

tsv_file= base_path + kitchenPath

csv_table= pandas.read_table(tsv_file,sep='\t', error_bad_lines=False)
csv_table.to_csv(base_path + kitchenWritePath,index=False)