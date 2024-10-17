CREATE TABLE client_balance
(
    client_name          VARCHAR(50),
    client_balance_date  TIMESTAMP,
    client_balance_value NUMERIC(15, 5)
);

INSERT INTO client_balance (client_name, client_balance_date, client_balance_value)
VALUES ('klient_1', '2022-05-20 18:50:00', 1500.00000),
       ('klient_1', '2022-05-20 18:50:00', 1500.00000),
       ('klient_1', '2022-05-20 17:50:00', 1000.00000),
       ('klient_2', '2022-05-20 17:50:00', 500.00000),
       ('klient_2', '2022-05-20 18:50:00', 1000.00000),
       ('klient_2', '2022-05-20 18:50:00', 1000.00000);

---------- This is a solution ---------
DELETE
FROM client_balance
WHERE ctid NOT IN (
    SELECT MIN(ctid)
    FROM client_balance
    GROUP BY client_name, client_balance_date, client_balance_value
);