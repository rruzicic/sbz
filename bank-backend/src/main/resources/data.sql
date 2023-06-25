INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Petar', 'Petrovic', 'pera@pera.com', '123.Auth', 'CLERK', 25);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Jovan', 'Jovanovic', 'jovan@jovan.com', '123.Auth', 'USER', 65);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Milan', 'Milanic', 'milan@milanic.com', '123.Auth', 'USER', 45);

INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (700, '2026-1-1', 100, 3);

INSERT INTO credit_request (client_id, client_income, money_sum, rate_number, client_employment_status, client_contract_start, client_contract_end, credit_end_min, credit_end_max, credit_status, recommended_approval, missed_payment, payment_overdue) VALUES (3, 1000.0, 100.0, 12, 'INDEFINITE', '2023-09-09', null, '2024-03-09', '2024-09-09', 'APPROVED', true, false, false);