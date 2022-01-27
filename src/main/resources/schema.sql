CREATE TABLE IF NOT EXISTS transaction_data(id BIGINT NOT NULL PRIMARY KEY, client_id BIGINT NOT NULL, date DATE NOT NULL, amount NUMERIC(20,4) NOT NULL, currency VARCHAR NOT NULL, commission_amount NUMERIC(20,4) NOT NULL,commission_currency VARCHAR NOT NULL);
CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;
