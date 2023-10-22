CREATE TABLE if not exists customer
   (
       customer_id VARCHAR(11),
       first_name VARCHAR(10),
       middle_name VARCHAR(10),
       last_name VARCHAR(10),
       city VARCHAR(15),
       mobile_no VARCHAR(15),
       occupation VARCHAR(15),
       date_of_birth DATE,
      CONSTRAINT customer_custid_pk PRIMARY KEY(customer_id)
   );
CREATE TABLE if not exists bank_branch
   (
    branch_id VARCHAR(10),
    branch_name VARCHAR(30),
    branch_city VARCHAR(30),
    branch_state VARCHAR(30),
    branch_country VARCHAR(30),
    CONSTRAINT branch_id_pk PRIMARY KEY(branch_id)
   );
CREATE TABLE if not exists account
   (
      account_id VARCHAR(11),
      customer_id VARCHAR(11),
      branch_id VARCHAR(10),
      opening_balance DECIMAL,
      account_open_date TIMESTAMP,
      account_type VARCHAR(10),
      account_status VARCHAR(10),
      CONSTRAINT account_id_pk PRIMARY KEY(account_id),
      CONSTRAINT account_custid_fk FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
      CONSTRAINT account_bid_fk FOREIGN KEY(branch_id) REFERENCES bank_branch(branch_id)
    );
CREATE TABLE if not exists account_transaction
    (
     trxn_number VARCHAR(10),
     account_id VARCHAR(11),
     date_of_trxn DATE,
     mode_of_transaction VARCHAR(20),
     transaction_type VARCHAR(20),
     transaction_amount DECIMAL,
     CONSTRAINT trxn_number_pk PRIMARY KEY(trxn_number),
     CONSTRAINT trxn_account_id_fk FOREIGN KEY(account_id) REFERENCES account(account_id)
    );
insert into bank_branch values
('BR0001', 'SVG Bank', 'Nagari', 'Andhra Pradesh', 'India'),
('BR0002', 'SVG Bank', 'Nagalapuram', 'Andhra Pradesh', 'India'),
('BR0003', 'SVG Bank', 'Puttur', 'Andhra Pradesh', 'India')