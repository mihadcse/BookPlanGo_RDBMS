
CREATE TABLE `car_details` (
  `CarID` int NOT NULL,
  `LiscenceNum` varchar(45) NOT NULL,
  `VehicleType` varchar(45) NOT NULL,
  `Seat` varchar(45) NOT NULL,
  `Location` varchar(45) NOT NULL,
  `carStatus` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `Dhaka` varchar(45) NOT NULL,
  `Mymensingh` varchar(45) NOT NULL,
  `Barishal` varchar(45) NOT NULL,
  `Khulna` varchar(45) NOT NULL,
  `Chattogram` varchar(45) NOT NULL,
  `Rajshahi` varchar(45) NOT NULL,
  `Sylhet` varchar(45) NOT NULL,
  `Rangpur` varchar(45) NOT NULL,
  `AC` varchar(45) NOT NULL,
  `ImagePath` varchar(400) NOT NULL,
  `Rating` float DEFAULT NULL,
  `RatingNum` int DEFAULT NULL,
  PRIMARY KEY (`LiscenceNum`)
) 

CREATE TABLE `carbookdetails` (
  `CarLicsence` varchar(45) DEFAULT NULL,
  `BookingDate` date DEFAULT NULL,
  `CarID` int DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Start` varchar(45) DEFAULT NULL,
  `End` varchar(45) DEFAULT NULL,
  `Rated` varchar(45) DEFAULT 'N',
  `travelID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`travelID`)
) 

CREATE TABLE `h_roomdetails` (
  `Hotel_ID` int NOT NULL,
  `Hotel_name` varchar(45) DEFAULT NULL,
  `room_num` varchar(45) NOT NULL,
  `bedding` varchar(45) DEFAULT NULL,
  `room_status` varchar(45) DEFAULT NULL,
  `room_ac` varchar(45) DEFAULT NULL,
  `room_floor` int DEFAULT NULL,
  `room_price` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `book_start_date` date DEFAULT NULL,
  `book_end_date` date DEFAULT NULL,
  `Rating` float DEFAULT NULL,
  `RatingNum` int DEFAULT NULL
) 

CREATE TABLE `hotel_info` (
  `Hotel_Id` int NOT NULL,
  `Hotel_name` varchar(45) NOT NULL,
  `Hotel_Address` varchar(45) NOT NULL,
  `Hotel_available_room` varchar(45) DEFAULT NULL,
  `Hotel_booked_room` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Hotel_Id`),
  UNIQUE KEY `Hotel_Id_UNIQUE` (`Hotel_Id`)
) 

CREATE TABLE `message` (
  `from_id` varchar(100) DEFAULT NULL,
  `to_name` varchar(100) DEFAULT NULL,
  `message` varchar(400) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) 

CREATE TABLE `new_table` (
  `Hotel_ID` int NOT NULL,
  `Hotel_available_rooms` int NOT NULL,
  `Hotel_booked_rooms` int NOT NULL,
  `Hotel_address` varchar(65) NOT NULL,
  `Hotel_city` varchar(45) NOT NULL,
  PRIMARY KEY (`Hotel_ID`)
) 

CREATE TABLE `serviceprovider_info` (
  `service_id` int NOT NULL,
  `service_name` varchar(100) NOT NULL,
  `service_password` varchar(64) DEFAULT NULL,
  `service_phone_no` int NOT NULL,
  `service_type` varchar(45) NOT NULL,
  `service_location` varchar(45) NOT NULL,
  `floor` int DEFAULT NULL,
  `service_approval` varchar(50) DEFAULT NULL,
  `service_image` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_id_UNIQUE` (`service_id`)
) 

CREATE TABLE `tourdetails` (
  `traveler_nid` int NOT NULL,
  `hotel_name` varchar(45) NOT NULL,
  `room_name` varchar(100) DEFAULT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `Destination` varchar(45) NOT NULL,
  `Total_Expenses` int DEFAULT NULL,
  `Rated` varchar(45) DEFAULT 'N',
  `travelID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`travelID`)
) 

CREATE TABLE `travel_places` (
  `place_name` varchar(100) NOT NULL,
  `place_district` varchar(100) DEFAULT NULL,
  `place_division` varchar(100) DEFAULT NULL,
  `place_image_path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`place_name`)
)

CREATE TABLE `traveler_fav_places` (
  `traveler_nid` int NOT NULL,
  `fav_place` varchar(100) NOT NULL,
  `fav_place_district` varchar(100) NOT NULL,
  `fav_place_division` varchar(100) NOT NULL,
  `fav_place_image_path` varchar(500) NOT NULL
) 

CREATE TABLE `userinfo` (
  `NID` int NOT NULL,
  `Username` varchar(64) NOT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`NID`,`Username`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `NID_UNIQUE` (`NID`)
) 


CREATE TABLE bookplango.ratings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rating_value INT NOT NULL CHECK (rating_value BETWEEN 1 AND 5),
    rating_time DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE bookplango.rating_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    rating_id INT,
    rating_value INT,
    log_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

USE bookplango;
DELIMITER $$

CREATE TRIGGER after_rating_insert
AFTER INSERT ON bookplango.ratings
FOR EACH ROW
BEGIN
    INSERT INTO bookplango.rating_logs (rating_id, rating_value)
    VALUES (NEW.id, NEW.rating_value);
END$$

DELIMITER ;