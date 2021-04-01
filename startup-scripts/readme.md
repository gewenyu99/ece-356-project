make sure you have files.. country_names_area.csv Economy_Data_Fixed.csv (some columns removed for easy parsing)
Human-Development-Index.csv midyear_population_age_sex.csv midyear_population_5yr_age_sex.csv
mortality_life_expectancy.csv midyear_population.csv birth_death_growth_rates.csv

Now time to run the scripts:
python3 fix_population.py python3 fix_economy.py python3 fix_hdi.py

These scripts will generate new .csv files that are MUCH easier to import via sql
--------------------------------------------------------

Create a -v in docker container to reference a folder containing these .csv files, I currently have it in C:\NHL, but
you can put it anywhere, just make sure it references the startup-scripts folder (personally i just copied everything in
that folder into NHL)
that is why it's confusing.. but yeah (long blurb for nothing lol):

docker run --name demographics -v C:\NHL:/mnt -p 3306:3306 -e MYSQL_ROOT_PASSWORD=rtxon -d mysql

docker exec -it demographics /bin/bash

first, try mysql -u root -p

if doesn't work, in docker container if can't find .sock or something, do:
apt-get update apt-get install mysql-client
----------------------------------------------------------

Ok great. now you can do this if all goes well... docker exec -it demographics mysql -u root -p

or .. if you're already in /bin/bash of docker mysql -u root -p

Please run these commands in mysql to create the database..

create database demographics; use demographics;

quit out of the database (quit)
install vim (apt-get install vim)
change the file /etc/mysql/my.cnf (add this to end of file)

[mysqld]
secure_file_priv=/var/lib/mysql-files/

This will allow mysql to actually run the source files from the /mnt directory (or any directory)

now exit out of container (and restart it)

docker stop demographics docker start demographics

----------------------------------------------------------

* No guaruntees the sql works properly yet...
* sorry shouldn't be committing massive .csv files but this is easy way lol..

try to do this...

docker exec -it demographics mysql -u root -p

use demographics;

source /mnt/Create-Demographics.sql