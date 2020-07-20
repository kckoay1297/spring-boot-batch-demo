DROP TABLE IF EXISTS TRANSACTION;

CREATE TABLE TRANSACTION (
   transaction_id bigint auto_increment,
   account_number VARCHAR(50),
   trx_amount DECIMAL(20, 2),
   description VARCHAR(500),
   trx_date TIMESTAMP,
   customer_id VARCHAR(50)
); 