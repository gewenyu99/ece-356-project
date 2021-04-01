# importing csv module
import csv
import os

from countrydict import country_dict

filename = "Human-Development-Index.csv"

# initializing the titles and rows list
fields = []
rows = []

# reading csv file
with open(filename, 'r') as csvfile:
    # creating a csv reader object
    csvreader = csv.reader(csvfile)

    # extracting field names through first row
    fields = next(csvreader)

    # extracting each data row one by one
    for row in csvreader:
        rows.append(row)

if os.path.exists("hdi_results.csv"):
    os.remove("hdi_results.csv")

results = open("hdi_results.csv", "w")

years = fields

for row in rows:
    name = row[0]
    if name.replace(" ", "").lower() not in country_dict:
        print(name.replace(" ", "").lower())
        continue

    code = country_dict[name.replace(" ", "").lower()]

    for i in range(1, len(row)):
        results.write(str(code) + "," + str(years[i]) + "," + str(row[i]) + "\n")

results.close()
