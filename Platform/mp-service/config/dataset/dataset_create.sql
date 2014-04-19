-- ------------------------------------------------------------
-- Create table GA_DATASET_CONNECTION
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `GA_DATASET_CONNECTION`;
-- ------------------------------------------------------------
CREATE TABLE `GA_DATASET_CONNECTION` 
( 
   `OUID`                          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
   `OVLD`                          TINYINT NOT NULL DEFAULT TRUE, 
   `UNIQUE_ID`                     INTEGER, 
   `HOST`                          VARCHAR(80), 
   `PORT`                          INTEGER, 
   `PASSPORT`                      VARCHAR(40), 
   `PASSWORD`                      VARCHAR(40), 
   `DATABASE_NAME`                 VARCHAR(40), 
   `CREATE_USER_ID`                INTEGER, 
   `CREATE_DATE`                   DATETIME, 
   `UPDATE_USER_ID`                INTEGER, 
   `UPDATE_DATE`                   DATETIME 
) ENGINE=MyISAM DEFAULT CHARSET=utf8; 


-- ------------------------------------------------------------
-- Create table GA_DATASET_SYNCHRONIZER
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `GA_DATASET_SYNCHRONIZER`;
-- ------------------------------------------------------------
CREATE TABLE `GA_DATASET_SYNCHRONIZER` 
( 
   `OUID`                          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
   `OVLD`                          TINYINT NOT NULL DEFAULT TRUE, 
   `UNIQUE_ID`                     INTEGER, 
   `SOURCE_CONNECTION_ID`          BIGINT, 
   `SOURCE_DATA_NAME`              VARCHAR(80), 
   `SOURCE_UPDATE_DATE`            DATETIME, 
   `TARGET_CONNECTION_ID`          INTEGER, 
   `TARGET_DATE_NAME`              VARCHAR(null), 
   `TARGET_UPDATE_DATE`            DATETIME, 
   `CREATE_USER_ID`                INTEGER, 
   `CREATE_DATE`                   DATETIME, 
   `UPDATE_USER_ID`                INTEGER, 
   `UPDATE_DATE`                   DATETIME 
) ENGINE=MyISAM DEFAULT CHARSET=utf8; 

