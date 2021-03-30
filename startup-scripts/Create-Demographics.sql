drop table if exists Users;
drop table if exists PopulationData;
drop table if exists PopulationDist;
drop table if exists PopulationDist_5YearRange;
drop table if exists QolData;
drop table if exists Country;

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

load data infile '/mnt/country_names_area.csv' ignore into table Country
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

load data infile '/mnt/birth_death_growth_rates.csv' ignore into table PopulationData
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

load data infile '/mnt/midyear_population.csv' ignore into table TempPopulationDataOne
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 1 lines
	(countryID, @dummy, year, population);
		
update PopulationData
	inner join TempPopulationDataOne on 
	PopulationData.countryID = TempPopulationDataOne.countryID 
	and PopulationData.year = TempPopulationDataOne.year
set
	PopulationData.totalPopulation = TempPopulationDataOne.population;

create table TempPopulationDataTwo (
	countryID char(2),
	year int,
	infantMortalityRate int,
	foreign key (countryID) references Country(countryID)
);

load data infile '/mnt/mortality_life_expectancy.csv' ignore into table TempPopulationDataTwo
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 1 lines
	(countryID, @dummy, year, infantMortalityRate, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy);
		
update PopulationData
	inner join TempPopulationDataTwo on 
	PopulationData.countryID = TempPopulationDataTwo.countryID 
	and PopulationData.year = TempPopulationDataTwo.year
set
	PopulationData.infantMortalityRate = TempPopulationDataTwo.infantMortalityRate;
	
drop table TempPopulationDataOne;
drop table TempPopulationDataTwo;

-- 
-- 
-- :)
-- 
--

-- Please run python3 fix_population.py

create table PopulationDist (
	countryID char(2),
	year int,
	age int,
	population int,
	foreign key (countryID) references Country(countryID)
);

load data infile '/mnt/population_results.csv' ignore into table PopulationDist
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

create table PopulationDist_5YearRange (
	countryID char(2),
	year int,
	startAge int,
	population int,
	foreign key (countryID) references Country(countryID)
);

load data infile '/mnt/midyear_population_5yr_age_sex.csv' ignore into table PopulationDist_5YearRange
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

create table QolData (
	countryID char(2),
	year int,
	HDI decimal(4, 3),
	lifeExpectancy decimal(11, 8),
	survivalToAge65 decimal(10, 8),
	foreign key (countryID) references Country(countryID)
);

load data infile '/mnt/economy_results.csv' ignore into table QolData
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

load data infile '/mnt/hdi_results.csv' ignore into table tempHDI
	fields terminated by ','
	enclosed by '"'
	lines terminated by '\n'
	ignore 0 lines
	(countryID, year, HDI);
	
update QolData
	inner join tempHDI on QolData.countryID = tempHDI.countryID and QolData.year = tempHDI.year
set
	QolData.HDI = tempHDI.HDI;

drop table tempHDI;













































