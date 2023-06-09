-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for neapolis_hotel
CREATE DATABASE IF NOT EXISTS `neapolis_hotel` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `neapolis_hotel`;

-- Dumping structure for procedure neapolis_hotel.add_room_reservation
DELIMITER //
CREATE PROCEDURE `add_room_reservation`(
	IN `room_id` INT,
	IN `customer_id` VARCHAR(50),
	IN `date_start` DATE,
	IN `date_end` DATE,
	IN `value` DOUBLE,
	IN `status` INT,
	IN `is_paid` TINYINT
)
BEGIN
    DECLARE result TINYINT; 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;

    START TRANSACTION;


    SELECT isRoomAvailable(room_id, date_start, date_end) INTO result;

    IF result = TRUE THEN
        INSERT INTO room_reservations (room_id, customer_id, date_start, date_end, value, STATUS, is_paid)
        VALUES (room_id, customer_id, date_start, date_end, value, status, is_paid); -- Assuming end_date is the column name
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
END//
DELIMITER ;

-- Dumping structure for table neapolis_hotel.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Citizen ID',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Citizen Name',
  `phone_number` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Full phone number including country code',
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Full email containing *@*.*',
  `dob` date NOT NULL COMMENT 'Date of birth',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `email` (`email`),
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for procedure neapolis_hotel.getAvailableRooms
DELIMITER //
CREATE PROCEDURE `getAvailableRooms`(
	IN `booking_start` DATE,
	IN `booking_end` DATE
)
BEGIN
SELECT rooms.*
FROM rooms
WHERE ID NOT IN (
SELECT room_id AS ID
FROM room_reservations
WHERE (
		room_reservations.status != 3 AND((
 booking_start >= room_reservations.date_start AND booking_start <= room_reservations.date_end
 	) OR (
 booking_end >= room_reservations.date_start AND booking_end <= room_reservations.date_end
 	))
	)
); END//
DELIMITER ;

-- Dumping structure for function neapolis_hotel.isRoomAvailable
DELIMITER //
CREATE FUNCTION `isRoomAvailable`(`room_id` INT,
    `booking_start` DATE,
    `booking_end` DATE
) RETURNS tinyint(4)
    DETERMINISTIC
BEGIN
    DECLARE result TINYINT;
    
    SET result = room_id NOT IN (
        SELECT room_id AS ID
        FROM room_reservations
        WHERE (
            room_reservations.status != 3 AND (
                (booking_start >= room_reservations.date_start AND booking_start <= room_reservations.date_end)
                OR
                (booking_end >= room_reservations.date_start AND booking_end <= room_reservations.date_end)
            )
        )
    );

    RETURN result;
END//
DELIMITER ;

-- Dumping structure for table neapolis_hotel.rooms
CREATE TABLE IF NOT EXISTS `rooms` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Database ID, autogenerated',
  `room_number` varchar(5) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT 'Number on the door',
  `size` int(11) NOT NULL COMMENT 'Number of people it can accomodate',
  `type` int(11) NOT NULL COMMENT 'Enum for normal, luxury, suite, luxury suite etc',
  `description` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Sea view, 2 bathrooms, etc',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `room_number` (`room_number`),
  KEY `size` (`size`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table neapolis_hotel.room_reservations
CREATE TABLE IF NOT EXISTS `room_reservations` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `customer_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `date_start` date NOT NULL COMMENT 'Check-in time',
  `date_end` date NOT NULL COMMENT 'Check-out time',
  `value` double NOT NULL DEFAULT 0 COMMENT 'Value in euro',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0: Reserved, 1: checked-in, 2: checked-out, 3: past',
  `is_paid` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Boolean value, is reservation paid?',
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `date_start` (`date_start`) USING HASH,
  KEY `date_end` (`date_end`) USING HASH,
  KEY `FK_room_reservations_customers` (`customer_id`),
  KEY `FK_room_reservations_rooms` (`room_id`),
  CONSTRAINT `FK_room_reservations_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_room_reservations_rooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
