# importing csv module
import csv
import os

# csv file name
filename = "midyear_population_age_sex.csv"

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

    # get total number of rows
    print("Total no. of rows: %d" % (csvreader.line_num))

# printing the field names
print('Field names are:' + ', '.join(field for field in fields))

#  printing first 5 rows
print('\nFirst 5 rows are:\n')
for row in rows[:5]:
    # parsing each column of a row
    for col in row:
        print("%10s" % col),
    print('\n')

pop_dict = {}

for row in rows:
    breaker = False
    if (row[0], row[2]) not in pop_dict:
        pop_dict[(row[0], row[2])] = row[5:]

if os.path.exists("population_results.csv"):
    os.remove("population_results.csv")

results = open("population_results.csv", "w")

for key in pop_dict:
    for i in range(len(pop_dict[key])):
        results.write(str(key[0]) + "," + str(key[1]) + "," + str(i) + "," + str(int(pop_dict[key][i]) * 2) + "\n")

results.close()
