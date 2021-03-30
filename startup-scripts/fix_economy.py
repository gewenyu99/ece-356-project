# importing csv module
import csv
import os
from countrydict import country_dict

filename = "Economy_Data_Fixed.csv"

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
  

if os.path.exists("economy_results.csv"):
  os.remove("economy_results.csv")

results = open("economy_results.csv", "w")

for row in rows:
    name = row[0]
    if name.replace(" ", "").lower() not in country_dict:
        print(name.replace(" ", "").lower())
        continue
    code = country_dict[name.replace(" ", "").lower()]
    if row[2] == "NA" and row[3] != "NA" and row[4] != "NA":
        results.write(str(code) + "," + str(row[1]) + "," + "," + str(float(row[3]) + float(row[4])) + "\n")
    elif row[2] == "NA" and row[3] == "NA" and row[4] == "NA":
        results.write(str(code) + "," + str(row[1]) + "," + "," + "\n")
    elif row[3] == "NA" and row[4] == "NA":
        results.write(str(code) + "," + str(row[1]) + "," + str(row[2]) + "," + "\n")
    else:
        results.write(str(code) + "," + str(row[1]) + "," + str(row[2]) + "," + str(float(row[3]) + float(row[4])) + "\n")

results.close()