CREATE database atm_machine;
use atm_machine;
CREATE table user(
    user_id int AUTO_INCREMENT PRIMARY KEY,
    username varchar(255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    balance decimal(10,2) DEFAULT 0.00
);