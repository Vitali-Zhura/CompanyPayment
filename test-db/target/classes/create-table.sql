-- Schema HR
DROP TABLE IF EXISTS company;

-- company
CREATE TABLE company (
  companyId  INT NOT NULL AUTO_INCREMENT,
  companyAccount VARCHAR(255) NOT NULL UNIQUE,
  companyName VARCHAR(255) NOT NULL
);

-- payment
DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
  paymentId    INT NOT NULL AUTO_INCREMENT,
  payerName VARCHAR(255) NOT NULL,
  paymentSum  INT NOT NULL,
  companyAccount   VARCHAR(255) NOT NULL,
  paymentDate TIMESTAMP(0) NOT NULL,
  PRIMARY KEY (paymentId),
  FOREIGN KEY (companyAccount)

  REFERENCES company (companyAccount)

);