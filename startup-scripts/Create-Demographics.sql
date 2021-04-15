use demographics;
drop table if exists ix_auth_username;
drop table if exists authorities;
drop table if exists users;

drop table if exists PopulationData;
drop table if exists population_dist;
drop table if exists population_dist5_year_range;
drop table if exists tempHDI;
drop table if exists qol_data;
drop table if exists country;

--
-- 
-- :) 
-- 
--

CREATE TABLE users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       enabled TINYINT NOT NULL DEFAULT 1,
                       PRIMARY KEY (username)
);

CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (username,authority);

-- 
-- 
-- :) 
-- 
--

create table country
(
    country_id   char(2),
    country_name char(50),
    primary key (country_id)
);

load data infile '/mnt/country_names_area.csv' ignore into table country
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (country_id, countryName, @dummy);


-- 
-- 
-- :) 
-- 
--

create table PopulationData
(
    countryID           char(2),
    year                int,
    birthRate           decimal(5, 2),
    deathRate           decimal(5, 2),
    infantMortalityRate decimal(5, 2),
    totalPopulation     int,
    foreign key (countryID) references country (country_id)
);

load data infile '/mnt/birth_death_growth_rates.csv' ignore into table PopulationData
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (countryID, @dummy, year, birthRate, deathRate, @dummy, @dummy, @dummy);

-- We still need infantMortalityRate and totalPopulation
create table TempPopulationDataOne
(
    countryID  char(2),
    year       int,
    population int,
    foreign key (countryID) references country (country_id)
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
set PopulationData.totalPopulation = TempPopulationDataOne.population;

create table TempPopulationDataTwo
(
    countryID           char(2),
    year                int,
    infantMortalityRate int,
    foreign key (countryID) references country (country_id)
);

load data infile '/mnt/mortality_life_expectancy.csv' ignore into table TempPopulationDataTwo
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (countryID, @dummy, year, infantMortalityRate, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
     @dummy, @dummy, @dummy);

update PopulationData
    inner join TempPopulationDataTwo on
            PopulationData.countryID = TempPopulationDataTwo.countryID
            and PopulationData.year = TempPopulationDataTwo.year
set PopulationData.infantMortalityRate = TempPopulationDataTwo.infantMortalityRate;

drop table TempPopulationDataOne;
drop table TempPopulationDataTwo;

--
--
-- :)
--
--

-- Please run python3 fix_population.py

create table population_dist
(
    country_id  char(2),
    year       int,
    age        int,
    population int,
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/population_results.csv' ignore into table population_dist
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 0 lines
    (country_id, year, age, population);

--
--
-- :)
--
--

create table population_dist5_year_range
(
    country_id  char(2),
    year       int,
    start_age   int,
    population int,
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/midyear_population_5yr_age_sex.csv' ignore into table population_dist5_year_range
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 0 lines
    (country_id, @dummy, year, @dummy, start_age, @dummy, @dummy, population, @dummy, @dummy);

--
--
-- :)
--
--

-- Qol
-- Life expectancy line FB in Economy Data.csv
-- HDI is in Human-Development-Index.csv
-- SurvivalToAge65 is in Economy Data.csv but it is split into female male

create table qol_data
(
    country_id       char(2),
    year            int,
    hdi             decimal(4, 3),
    life_expectancy  decimal(11, 8),
    survival_to_age65 decimal(10, 8),
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/economy_results.csv' ignore into table qol_data
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 0 lines
    (country_id, year, life_expectancy, survival_to_age65);

create table tempHDI
(
    country_id char(2),
    year      int,
    hdi       decimal(4, 3),
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/hdi_results.csv' ignore into table tempHDI
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 0 lines
    (country_id, year, hdi);

update qol_data
    inner join tempHDI on qol_data.country_id = tempHDI.country_id and qol_data.year = tempHDI.year
set qol_data.hdi = tempHDI.hdi;

drop table tempHDI;













































