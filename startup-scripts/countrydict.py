# importing csv module
import csv
import os

# csv file name
filename = "country_names_area.csv"
  
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

country_dict = {}
for row in rows:
    name = row[1].replace(" ", "").lower()
    code = row[0]
    country_dict[name] = code

# Something wrong with south korea 1993 economy_results.csv

country_dict["myanmar"] = "BM"
country_dict["czechrepublic"] = "EZ"
country_dict["czechrepublicthe"] = "EZ"
country_dict["cdivoire"] = "IV"
country_dict["côted'ivoire"] = "IV"
country_dict["cotedivoire"] = "IV"
country_dict["côtedivoire"] = "IV"
country_dict["thegambia"] = "GA"
country_dict["gambia"] = "GA"
country_dict["thenetherlands"] = "NL"
country_dict["netherlandsthe"] = "NL"
country_dict["democraticrepublicofthecongothe"] = "CG"
country_dict["democraticrepublicofthecongo"] = "CG"
country_dict["congo(democraticrepublicofthe)"] = "CG"
country_dict["congodemrep"] = "CG"
country_dict["congorep"] = "CG"
country_dict["republicofthecongo"] = "CF"
country_dict["republicofcongo"] = "CF"
country_dict["congothe"] = "CF"
country_dict["congo"] = "CF"
country_dict["unitedstatesofamerica"] = "US"
country_dict["fyrmacedonia"] = "MK"
country_dict["theformeryugoslavrepublicofmacedonia"] = "MK"
country_dict["macedoniafyr"] = "MK"
country_dict["thebahamas"] = "BF"
country_dict["bahamas"] = "BF"
country_dict["stvincentandthegrenadines"] = "VC"
country_dict["slovakrepublic"] = "LO"
country_dict["stkittsandnevis"] = "SC"
country_dict["islamicrepublicofiran"] = "IR"
country_dict["iran(islamicrepublicof)"] = "IR"
country_dict["iranislamicrepublicof"] = "IR"
country_dict["iranislamicrep"] = "IR"
country_dict["taiwanprovinceofchina"] = "TW"
country_dict["taiwanchina"] = "TW"
country_dict["timorleste"] = "TT"
country_dict["stlucia"] = "ST"
country_dict["kyrgyzrepublic"] = "KG"
country_dict["laopdr"] = "LA"
country_dict["laopeople'sdemocraticrepublic"] = "LA"
country_dict["laopeoplesdemocraticrepublicthe"] = "LA"
country_dict["micronesia"] = "FM"
country_dict["micronesiafedsts"] = "FM"
country_dict["micronesia(federatedstatesof)"] = "FM"
country_dict["hongkongsar"] = "HK"
country_dict["hongkong,china(sar)"] = "HK"
country_dict["hongkongsarchina"] = "HK"
country_dict["guineabissau"] = "PU"
country_dict["bruneidarussalam"] = "BX"
country_dict["yemenrep"] = "YM"
country_dict["westbankandgaza"] = "GZ"
country_dict["virginislandsus"] = "VQ"
country_dict["venezuelarb"] = "VE"
country_dict["venezuela(bolivarianrepublicof)"] = "VE"
country_dict["venezuelabolivarianrepublicof"] = "VE"
country_dict["vanatu"] = "NH"
country_dict["republicofkorea"] = "KS"
country_dict["democraticpeoplesrepublicofkoreathe"] = "KS"
country_dict["democraticpeoplesrepublicofkorea"] = "KS"
country_dict["korearep"] = "KS"
country_dict["korea(republicof)"] = "KS"
country_dict["korea"] = "KN"
country_dict["koreadempeople’srep"] = "KS"
country_dict["republicofkoreathe"] = "KS"
country_dict["sintmaartendutchpart"] = "NN"
country_dict["republicofmoldova"] = "MD"
country_dict["moldova(republicof)"] = "MD"
country_dict["republicofmoldovathe"] = "MD"
country_dict["papanewguinea"] = "PP"
country_dict["capeverde"] = "CV"
country_dict["bukinafaso"] = "UV"
country_dict["britishvirginislands"] = "VI"
country_dict["russianfederation"] = "RS"
country_dict["russianfederationthe"] = "RS"
country_dict["egyptarabrep"] = "EG"
country_dict["syrianarabrepublic"] = "SY"
country_dict["syrianarabrepublicthe"] = "SY"
country_dict["macaosarchina"] = "MC"
country_dict["unitedrepublicoftanzania"] = "TZ"
country_dict["tanzania(unitedrepublicof)"] = "TZ"
country_dict["unitedstatesofamericathe"] = "US"
country_dict["unitedkingdomofgreatbritainandnorthernirelandthe"] = "UK"
country_dict["unitedarabemiratesthe"] = "AE"
country_dict["sudanthe"] = "OD"
country_dict["stmartinfrenchpart"] = "RN"
country_dict["philippinesthe"] = "RP"
country_dict["nigerthe"] = "NG"
country_dict["comorosthe"] = "CN"
country_dict["boliviaplurinationalstateof"] = "BL"
country_dict["bolivia(plurinationalstateof)"] = "BL"
country_dict["marshallislandsthe"] = "RM"
country_dict["dominicanrepublicthe"] = "DR"
country_dict["stomndpripe"] = "TP"