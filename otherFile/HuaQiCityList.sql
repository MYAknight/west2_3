CREATE DATABASE  if not exists HuaQiweather;
use HuaQiweather;
create table if not exists city_list(
citylist_id varchar(60),
city_name varchar(60),
纬度 float(20),
经度 float(20),
set_time timestamp not null default current_timestamp,
PRIMARY KEY (citylist_id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO city_list( citylist_id , city_name ,纬度,经度 )
                       VALUES
                       ( "101010100","北京",39.90498,116.40528);
INSERT INTO city_list( citylist_id , city_name ,纬度,经度 )
                       VALUES
                       ( "101020100","上海",31.2317,121.47264);
INSERT INTO city_list( citylist_id , city_name ,纬度,经度 )
                       VALUES
                       ( "101230101","福州",26.0753,119.30623);

create table if not exists TD_Weather(
citylist_id varchar(60),
city_name varchar(60),
fx_Date DATE,
temp_Max int(10),
temp_Min int(10),
text_Day varchar(20),
PRIMARY KEY (citylist_id,fx_Date )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;