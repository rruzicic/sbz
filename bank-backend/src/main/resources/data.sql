INSERT INTO bank_user (first_name, last_name, email, password, role) VALUES ('Petar', 'Petrovic', 'pera@pera.com', '123.Auth', 'CLERK');
INSERT INTO bank_user (first_name, last_name, email, password, role) VALUES ('Jovan', 'Jovanovic', 'jovan@jovan.com', '123.Auth', 'USER');
INSERT INTO bank_user (first_name, last_name, email, password, role) VALUES ('Milan', 'Milanic', 'milan@milanic.com', '123.Auth', 'USER');

INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (700, '2026-1-1', 100, 3);