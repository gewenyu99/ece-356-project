use demographics;
drop table if exists ix_auth_username;
drop table if exists authorities;
drop table if exists users;


drop view if exists  country_data_aggregation;
drop table if exists population_data;
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

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (username, authority);

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
    (country_id, country_name, @dummy);


--
--
-- :)
--
--

create table population_data
(
    country_id            char(2),
    year                  int,
    birth_rate            decimal(5, 2),
    death_rate            decimal(5, 2),
    infant_mortality_rate decimal(5, 2),
    total_population      int,
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/birth_death_growth_rates.csv' ignore into table population_data
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (country_id, @dummy, year, birth_rate, death_rate, @dummy, @dummy, @dummy);

-- We still need infantMortalityRate and totalPopulation
create table TempPopulationDataOne
(
    country_id char(2),
    year       int,
    population int,
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/midyear_population.csv' ignore into table TempPopulationDataOne
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (country_id, @dummy, year, population);

update population_data
    inner join TempPopulationDataOne on
            population_data.country_id = TempPopulationDataOne.country_id
            and population_data.year = TempPopulationDataOne.year
set population_data.total_population = TempPopulationDataOne.population;

create table TempPopulationDataTwo
(
    country_id            char(2),
    year                  int,
    infant_mortality_rate int,
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

load data infile '/mnt/mortality_life_expectancy.csv' ignore into table TempPopulationDataTwo
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (country_id, @dummy, year, infant_mortality_rate, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
     @dummy, @dummy, @dummy);

update population_data
    inner join TempPopulationDataTwo on
            population_data.country_id = TempPopulationDataTwo.country_id
            and population_data.year = TempPopulationDataTwo.year
set population_data.infant_mortality_rate = TempPopulationDataTwo.infant_mortality_rate;

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
    country_id char(2),
    year       int,
    start_0    int default 0,
    start_5    int default 0,
    start_10   int default 0,
    start_15   int default 0,
    start_20   int default 0,
    start_25   int default 0,
    start_30   int default 0,
    start_35   int default 0,
    start_40   int default 0,
    start_45   int default 0,
    start_50   int default 0,
    start_55   int default 0,
    start_60   int default 0,
    start_65   int default 0,
    start_70   int default 0,
    start_75   int default 0,
    start_80   int default 0,
    start_85   int default 0,
    start_90   int default 0,
    start_95   int default 0,
    start_100  int default 0,
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);

create table population_dist5_year_temp
(
    country_id char(2),
    year       int,
    start_age  int,
    end_age    int,
    population int,
    foreign key (country_id) references country (country_id),
    check (start_age != 0 or end_age != 0)
);

load data infile '/mnt/midyear_population_5yr_age_sex.csv' ignore into table population_dist5_year_temp
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 0 lines
    (country_id, @dummy, year, @dummy, start_age, @dummy, @dummy, population, @dummy, @dummy);

INSERT INTO population_dist (country_id, year)
SELECT distinct country_id, year
FROM population_dist5_year_temp;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_0 = pd5yr.population
WHERE pd5yr.start_age = 0;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_5 = pd5yr.population
WHERE pd5yr.start_age = 5;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_10 = pd5yr.population
WHERE pd5yr.start_age = 10;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_15 = pd5yr.population
WHERE pd5yr.start_age = 15;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_20 = pd5yr.population
WHERE pd5yr.start_age = 20;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_25 = pd5yr.population
WHERE pd5yr.start_age = 25;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_30 = pd5yr.population
WHERE pd5yr.start_age = 30;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_35 = pd5yr.population
WHERE pd5yr.start_age = 35;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_40 = pd5yr.population
WHERE pd5yr.start_age = 40;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_45 = pd5yr.population
WHERE pd5yr.start_age = 45;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_50 = pd5yr.population
WHERE pd5yr.start_age = 50;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_55 = pd5yr.population
WHERE pd5yr.start_age = 55;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_60 = pd5yr.population
WHERE pd5yr.start_age = 60;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_65 = pd5yr.population
WHERE pd5yr.start_age = 65;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_70 = pd5yr.population
WHERE pd5yr.start_age = 70;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_75 = pd5yr.population
WHERE pd5yr.start_age = 75;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_80 = pd5yr.population
WHERE pd5yr.start_age = 80;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_85 = pd5yr.population
WHERE pd5yr.start_age = 85;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_90 = pd5yr.population
WHERE pd5yr.start_age = 90;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_95 = pd5yr.population
WHERE pd5yr.start_age = 95;

UPDATE population_dist p
    INNER JOIN population_dist5_year_temp pd5yr on p.country_id = pd5yr.country_id and p.year = pd5yr.year
SET p.start_100 = pd5yr.population
WHERE pd5yr.start_age = 100;

drop table population_dist5_year_temp;
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
    country_id        char(2),
    year              int,
    hdi               decimal(4, 3),
    life_expectancy   decimal(11, 8),
    CONSTRAINT lifeExpectancy_Ck CHECK (life_expectancy > 0),
    survival_to_age65 decimal(10, 8),
    CONSTRAINT survivalToAge65_Ck CHECK (survival_to_age65 > 0),
    primary key (country_id, year),
    foreign key (country_id) references country (country_id)
);
alter table qol_data
    modify hdi decimal(4, 3) not null;
alter table qol_data
    modify life_expectancy decimal(11, 8) not null;
alter table qol_data
    modify survival_to_age65 decimal(10, 8) not null;

load data infile '/mnt/economy_results.csv' ignore into table qol_data
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 0 lines
    (country_id, year, life_expectancy, survival_to_age65);

create table tempHDI
(
    country_id char(2),
    year       int,
    hdi        decimal(4, 3),
    CONSTRAINT temp_hdi_Ck CHECK (hdi > 0),
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


create table tempLocation
(
    country_name char(50),
    latitude     float,
    longitude    float
);

load data infile '/mnt/Country-Location.csv' ignore into table tempLocation
    fields terminated by ','
    enclosed by '"'
    lines terminated by '\n'
    ignore 1 lines
    (country_name, latitude, longitude);


create table country_location
(
    country_id char(50),
    latitude   float,
    longitude  float
);

insert into country_location (country_id, latitude, longitude)
SELECT c.country_id, t.latitude, t.longitude
from tempLocation t
         inner join country c on t.country_name = c.country_name;


drop table tempLocation;

CREATE VIEW country_info
AS
SELECT c.country_id, c.country_name, cl.latitude, cl.longitude
from country_location cl
         inner join country c on cl.country_id = c.country_id;

CREATE VIEW country_data_aggregation
            (country_id, country_name, year, birth_rate, death_rate, total_population, hdi,
             life_expectancy, survival_to_age65)
AS
SELECT c.country_id,
       c.country_name,
       pd.year,
       pd.birth_rate,
       pd.death_rate,
       pd.total_population,
       qd.hdi,
       qd.life_expectancy,
       qd.survival_to_age65
from country c
         inner join qol_data qd on c.country_id = qd.country_id and qd.hdi > 0
         inner join population_data pd on c.country_id = pd.country_id and qd.year = pd.year;