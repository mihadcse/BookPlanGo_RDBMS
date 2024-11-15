SELECT * FROM sys.userinfo;

SELECT * FROM sys.serviceprovider_info;

SELECT * FROM sys.hotel_info;

SELECT * FROM sys.tourdetails;

Insert into userinfo (NID,Username,Password,Contact) values ('1234567','bool','1234','010101')

Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type) values ('3','bool','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918','010101','Car')

Select count(1) from userinfo where Username = 'bool' and Password = '1234'

Select count(1) from serviceprovider_info where service_id = '1' and service_password = '1234'

Insert into serviceprovider_info (service_phone_no) values ('3','bool','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918','010101')

Insert into tourdetails (traveler_username,destination,StartDate,EndDate,Total_Expenses) values ('mihad','Sylhet','16 january','20 january','10000')

Select id,destination,StartDate,EndDate,Total_Expenses from tourdetails where traveler_username = "mihad";

SELECT COUNT(*) FROM userinfo;
SELECT COUNT(*) FROM serviceprovider_info

Select NID,Username,Contact from userinfo

Select service_id,service_name,service_phone_no,service_type from serviceprovider_info


CREATE TABLE h_name (
  Hotel_ID INT PRIMARY KEY NOT NULL,
  Hotel_available_rooms INT NOT NULL,  
  Hotel_booked_rooms INT NOT NULL,    
  Hotel_address VARCHAR(65) NOT NULL,
  Hotel_city VARCHAR(30) NOT NULL
)



CREATE TABLE h_roomdetails (
  Hotel_ID INT NOT NULL,
  room_type VARCHAR(65) NOT NULL,
  room_status VARCHAR(30) NOT NULL,
  room_ac VARCHAR(10) NOT NULL,
  room_price INT NOT NULL
)

Select * from h_name;

Select * from h_roomdetails;

insert into h_roomdetails (Hotel_ID,room_type,room_status,room_ac,room_price) values ('1001','single','Acailable','Non AC','7500')
insert into h_roomdetails (Hotel_ID,room_type,room_status,room_ac,room_price) values ('1001','Double','Available','AC','12000')


Select room_type,room_ac,room_status,room_price from h_roomdetails where Hotel_ID = '1001';

Select * from jamuna_30302;
select * from lakeview_1777;


Select count(*) from h_roomdetails where Hotel_ID = '1001'

Select count(*) from h_roomdetails where Hotel_ID = '1001' And room_status = 'Available'

Select count(*) from h_roomdetails where Hotel_ID = '1001' And room_status = 'Booked'


ALTER TABLE userinfo MODIFY COLUMN Password VARCHAR(64);

ALTER TABLE serviceprovider_info MODIFY COLUMN service_password VARCHAR(64);

select * from userinfo where Username = 'mihad'



Select * from serviceprovider_info where service_id='1001';









































--TRUNCATE TABLE userinfo;

--TRUNCATE TABLE serviceprovider_info;

--TRUNCATE TABLE h_name;

