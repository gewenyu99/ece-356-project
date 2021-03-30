--
-- 
-- :) 
-- 
--

create table Users (
	username char(25),
	password char(25),
	isAdmin boolean,
	primary key (username)
);

-- 
-- 
-- :) 
-- 
--

create table Country (
	countryID char(2),
	countryName char(50),
	primary key (countryID)
);

load data infile 'country_names_area.csv' ignore into table Country
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 1 lines
	(countryID, countryName, @dummy);
	
	
-- 
-- 
-- :) 
-- 
--
	
create table PopulationData (
	countryID char(2),
	year int,
	birthRate decimal(5, 2),
	deathRate decimal(5, 2),
	infantMortalityRate decimal(5, 2),
	totalPopulation int,
	foreign key (countryID) references Country(countryID)
);

load data infile 'birth_death_growth_rates.csv' ignore into table Country
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 1 lines
	(countryID, @dummy, year, birthRate, deathRate, @dummy, @dummy, @dummy);
	
-- We still need infantMortalityRate and totalPopulation
create table TempPopulationDataOne (
	countryID char(2),
	year int,
	population int,
	foreign key (countryID) references Country(countryID)
);

load data infile 'midyear_population.csv' ignore into table TempPopulationDataOne
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 1 lines
	(countryID, @dummy, year, population);
	
update t1
	set t1.totalPopulation = t2.population
	from PopulationData t1 inner join TempPopulationDataOne t2 
	on t1.countryID = t2.countryID
	and t1.year = t2.year;

create table TempPopulationDataTwo (
	countryID char(2),
	year int,
	infantMortalityRate int,
	foreign key (countryID) references Country(countryID)
);

load data infile 'mortality_lie_expectancy.csv' ignore into table TempPopulationDataTwo
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 1 lines
	(countryID, @dummy, year, infantMortalityRate, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy);
	
update t1
	set t1.infantMortalityRate = t2.infantMortalityRate
	from PopulationData t1 inner join TempPopulationDataTwo t2 
	on t1.countryID = t2.countryID
	and t1.year = t2.year;
	
drop table TempPopulationDataOne;
drop table TempPopulationDataTwo;

-- 
-- 
-- :)
-- 
--

-- Please run python3 fix_population.py

create table populationDist (
	countryID char(2),
	year int,
	age int,
	population int,
	foreign key (countryID) references Country(countryID)
);

load data infile 'population_results.csv' ignore into table populationDist
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 0 lines
	(countryID, year, age, population);
	
-- 
-- 
-- :)
-- 
--

create table populationDist_5YearRange (
	countryID char(2),
	year int,
	startAge int,
	population int,
	foreign key (countryID) references Country(countryID)
);

load data infile 'midyear_population_5yr_age_sex.csv' ignore into table populationDist
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 0 lines
	(countryID, @dummy, year, @dummy, startAge, @dummy, @dummy, population, @dummy, @dummy);

-- 
-- 
-- :)
-- 
--

-- Qol
-- Life expectancy line FB in Economy Data.csv
-- HDI is in Human-Development-Index.csv
-- SurvivalToAge65 is in Economy Data.csv but it is split into female male

create table qolData (
	countryID char(2),
	year int,
	HDI decimal(4, 3),
	lifeExpectancy decimal(11, 8),
	survivalToAge65 decimal(10, 8),
	foreign key (countryID) references Country(countryID)
);

load data infile 'economy_results.csv' ignore into table qolData
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 0 lines
	(countryID, year, lifeExpectancy, survivalToAge65);

create table tempHDI (
	countryID char(2),
	year int,
	HDI decimal(4, 3),
	foreign key (countryID) references Country(countryID)
);

load data infile 'hdi_results.csv' ignore into table tempHDI
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 0 lines
	(countryID, year, HDI);
	
update t1
	set t1.HDI = t2.HDI
	from qolData t1 inner join tempHDI t2 
	on t1.countryID = t2.countryID
	and t1.year = t2.year;

drop table tempHDI;













































