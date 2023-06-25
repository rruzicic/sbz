INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Petar', 'Petrovic', 'pera@pera.com', '123.Auth', 'CLERK', 25);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Jovan', 'Jovanovic', 'jovan@jovan.com', '123.Auth', 'USER', 65);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Milan', 'Milanic', 'milan@milanic.com', '123.Auth', 'USER', 45);

INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 101, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (700, '2026-1-1', 100, 3);
