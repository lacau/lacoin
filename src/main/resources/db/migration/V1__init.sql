CREATE TABLE exchange (
  id int8 NOT NULL,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(4) NOT NULL,
  CONSTRAINT pk_exchange PRIMARY KEY (id)
)
WITH (OIDS=FALSE);

CREATE SEQUENCE IF NOT EXISTS seq_exchange;
ALTER SEQUENCE seq_exchange INCREMENT BY 50;

CREATE TABLE quotation (
	id int8 NOT NULL,
	fk_exchange int8 NOT NULL,
	amount numeric(16,8) NOT NULL,
	volume numeric(16,8) NOT NULL,
	date timestamp NOT NULL,
	CONSTRAINT pk_quotation PRIMARY KEY (id),
	CONSTRAINT fk_quotation_exchange FOREIGN KEY (fk_exchange) REFERENCES exchange(id)
)
WITH (OIDS=FALSE);

CREATE SEQUENCE IF NOT EXISTS seq_quotation;
ALTER SEQUENCE seq_quotation INCREMENT BY 50;

CREATE TABLE exchange_fee (
	id int8 NOT NULL,
	fk_exchange int8 NOT NULL,
	fixed_amount numeric(16,8) NOT NULL,
	percentage_amount numeric(16,8) NOT NULL,
	currency VARCHAR(3) NOT NULL,
	type VARCHAR(45) NOT NULL,
	CONSTRAINT pk_exchange_fee PRIMARY KEY (id),
	CONSTRAINT fk_exchange_fee_exchange FOREIGN KEY (fk_exchange) REFERENCES exchange(id)
)
WITH (OIDS=FALSE);

CREATE SEQUENCE IF NOT EXISTS seq_exchange_fee;
ALTER SEQUENCE seq_exchange_fee INCREMENT BY 50;

INSERT INTO exchange(id, name, code) VALUES(nextval('seq_exchange'), 'Mercado bitcoin', 'MERC');
INSERT INTO exchange(id, name, code) VALUES(nextval('seq_exchange'), 'Blink trade', 'BKTR');
INSERT INTO exchange(id, name, code) VALUES(nextval('seq_exchange'), 'Omni trade', 'ONTR');
INSERT INTO exchange(id, name, code) VALUES(nextval('seq_exchange'), 'Bitcoin trade', 'BTTR');