CREATE DATABASE AIRLINE_DATABASE;
 
USE AIRLINE_DATABASE;

CREATE TABLE AIRLINE_DETAILS (
    AIRLINE_ID INT PRIMARY KEY AUTO_INCREMENT,
    AIRLINE_NAME VARCHAR(20) NOT NULL
);

CREATE TABLE FLIGHT_DETAILS (
    FLIGHT_ID INT PRIMARY KEY AUTO_INCREMENT,
    AIRLINE_ID INT REFERENCES AIRLINE_DETAILS (AIRLINE_ID),
    FLIGHT_NAME VARCHAR(10) NOT NULL,
    DATE_ADDED TIMESTAMP DEFAULT NOW(),
    SEATS INT NOT NULL
);

CREATE TABLE LOCATIONS (
    LOCATION_ID INT PRIMARY KEY AUTO_INCREMENT,
    LOCATION_NAME VARCHAR(20) NOT NULL, 
    AIRPORT_NAME VARCHAR(20) NOT NULL,
    ADDRESS VARCHAR(50) NOT NULL,
    STATE VARCHAR(20) NOT NULL,PERSON_ID,
    COUNTRY VARCHAR(20) NOT NULL,
    ZIP_CODE INT NOT NULL
);

CREATE TABLE JOURNEY_DETAILS (
    JOURNEY_ID INT PRIMARY KEY AUTO_INCREMENT,
    FLIGHT_ID INT REFERENCES FLIGHT_DETAILS (FLIGHT_ID),
    FLIGHT_SOURCE INT REFERENCES FLIGHT_DETAILS (LOCATION_ID),
    FLIGHT_DESTINATION INT REFERENCES FLIGHT_DETAILS (LOCATION_ID),
    SEATS_AVAILABLE INT NOT NULL,
    SEATS_BOOKED INT DEFAULT 0,
    TICKET_PRICE INT NOT NULL
);

CREATE TABLE COMMON_VALUES (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_TYPE VARCHAR(20) NOT NULL,
    ID_DESCRIPTION VARCHAR(20)
);

CREATE TABLE PERSON (  
    PERSON_ID INT PRIMARY KEY AUTO_INCREMENT,
    PERSON_TYPE INT REFERENCES COMMON_VALUES (ID),
    FIRST_NAME VARCHAR(20) NOT NULL,
    LAST_NAME VARCHAR(20) NOT NULL,
	EMAIL_ADDRESSS VARCHAR(20) NOT NULL UNIQUE,
    PASSPORT_NUMBER VARCHAR(20),
    ADDRESS_LINE1 VARCHAR(20) NOT NULL,
    ADDRESS_LINE2 VARCHAR(20),
    CITY VARCHAR(10) NOT NULL,
    STATE VARCHAR(10) NOT NULL,
    COUNTRY VARCHAR(10) NOT NULL,
    ZIP_CODE VARCHAR(10) NOT NULL
);

CREATE TABLE AIRLINE_EMPLOYEE (
    SSN INT PRIMARY KEY UNIQUE,
    PERSON_ID INT REFERENCES PERSON (PERSON_ID),
    DESIGNATION INT REFERENCES COMMON_VALUES (ID),
    JOINING_DATE TIMESTAMP DEFAULT NOW()
);


CREATE TABLE PAYMENT_DETAILS(  
	PAYMENT_ID INT PRIMARY KEY AUTO_INCREMENT,
	PAYMENT_METHOD INT REFERENCES COMMON_VALUES (ID),
	CARD_NUMBER INT,
	ACCOUNT_NUMBER INT,
	AMOUNT_PAID INT NOT NULL,
	PAYMENT_STATUS INT REFERENCES COMMON_VALUES (ID),
	PAYMENT_CANCELLED BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE BOOKING_DETAILS(
	BOOKING_ID INT PRIMARY KEY AUTO_INCREMENT,
	BOOKING_STATUS INT REFERENCES COMMON_VALUES (ID),
	PAYMENT_ID INT REFERENCES PAYMENT_DETAILS(PAYMENT_ID),
	BOOKING_CANCELLED BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE TICKET_DETAILS (
	TICKET_ID INT PRIMARY KEY AUTO_INCREMENT,
	SSN INT NOT NULL UNIQUE,
	PERSON_ID INT REFERENCES PERSON (PERSON_ID),
	BOOKING_ID INT REFERENCES BOOKING_DETAILS (BOOKING_ID)
);

CREATE TABLE USER_ACCOUNT (
    USER_ID INT REFERENCES PERSON (PERSON_ID),
    USER_PASSWORD VARCHAR(20) NOT NULL,
    LAST_LOGIN TIMESTAMP DEFAULT NOW()
);

CREATE TABLE CREW_DETAILS (
    USER_ID INT REFERENCES PERSON (PERSON_ID),
    JOURNEY_ID INT REFERENCES JOURNEY_DETAILS (JOURNEY_ID)
);

ALTER TABLE JOURNEY_DETAILS ADD COLUMN DEPARTURE_TIME TIMESTAMP;
ALTER TABLE JOURNEY_DETAILS ADD COLUMN ARRIVAL_TIME TIMESTAMP;
ALTER TABLE JOURNEY_DETAILS MODIFY COLUMN TICKET_PRICE FLOAT NOT NULL;


/************************* NEW ALTERATIONS  :: AMOL MANE :: 1 DEC 2013  ***************************************/
/************************* NEW ALTERATIONS  :: START  ***************************************/
ALTER TABLE PERSON ADD COLUMN DATE_OF_BIRTH DATE;
ALTER TABLE PERSON DROP   EMAIL_ADDRESSS ;
ALTER TABLE PERSON ADD COLUMN EMAIL_ADDRESSS varchar(20);
ALTER TABLE PERSON MODIFY COLUMN PASSPORT_NUMBER VARCHAR(20) unique;

ALTER TABLE BOOKING_DETAILS ADD COLUMN JOURNEY_ID INT NOT NULL REFERENCES JOURNEY_DETAILS(JOURNEY_ID) ;

ALTER TABLE TICKET_DETAILS DROP INDEX SSN;
ALTER TABLE BOOKING_DETAILS ADD COLUMN JOURNEY_ID INT NOT NULL REFERENCES JOURNEY_DETAILS(JOURNEY_ID) ;
/************************* NEW ALTERATIONS :: END ***************************************/



/********************************************** COMMON_VALUES ******************************************************/

INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PERSON','CUSTOMER');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PERSON','EMPLOYEE');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PERSON','ADMIN');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PAYMENT_METHOD','CARD');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PAYMENT_METHOD','ACCOUNT');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('BOOKING_STATUS','CONFIRMED');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('BOOKING_STATUS','WAITING');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('BOOKING_STATUS','CANCELLED');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PAYMENT_STATUS','SUCCESS');
INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PAYMENT_STATUS','FAILED');


/********************************************** COMMON_VALUES ******************************************************/












 
