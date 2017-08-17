CREATE TABLE BOARD (
	UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	TITLE VARCHAR(100) NOT NULL,
	CONTENTS VARCHAR(4000) NOT NULL,
	READ_COUNT INT NOT NULL DEFAULT 0,
	CREATED_BY VARCHAR(20) NOT NULL,
	CREATED_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATED_BY VARCHAR(20) NOT NULL,
	UPDATED_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP
);