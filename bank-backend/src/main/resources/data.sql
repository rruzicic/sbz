INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Petar', 'Petrovic', 'pera@pera.com', '123.Auth', 'CLERK', 25);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Jovan', 'Jovanovic', 'jovan@jovan.com', '123.Auth', 'USER', 65);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Milan', 'Milanic', 'milan@milanic.com', '123.Auth', 'USER', 45);
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Knjizara', 'Knjizaric', 'knjizara@knjizara.com', '123.Auth', 'USER', 45);

INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 101, 2);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (700, '2026-1-1', 100, 3);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (700, '2026-1-1', 100, 4);


INSERT INTO credit_request (client_id, client_income, money_sum, rate_number, client_employment_status, client_contract_start, client_contract_end, credit_end_min, credit_end_max, credit_status, recommended_approval, missed_payment, payment_overdue) VALUES (3, 1000.0, 100.0, 12, 'INDEFINITE', '2023-09-09', null, '2024-03-09', '2024-09-09', 'APPROVED', true, false, false);

-- credit testing part of db
-- won't get approval because they're over 60 and credit is over 120 rateNumber
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Mator', 'Sam', 'mator@sam.com', '123.Auth', 'USER', 65);

-- won't get approval because he's unemployed (req with employment status unemployed)
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Ne', 'Radim', 'ne@radim.com', '123.Auth', 'USER', 25);

-- doesn't have enough assets in bank accounts to accommodate credit (moneySum > 2500)
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Nemam', 'Kesa', 'nemam@kesa.com', '123.Auth', 'USER', 25);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 6);

-- doesn't have enough DTI (income < 3, moneySum < 2500)
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Nemam', 'DTI', 'nemam@DTI.com', '123.Auth', 'USER', 25);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 7);
INSERT INTO credit_request (client_id, client_income, money_sum, rate_number, client_employment_status, client_contract_start, client_contract_end, credit_end_min, credit_end_max, credit_status, recommended_approval, missed_payment, payment_overdue) VALUES (7, 1000.0, 100.0, 12, 'INDEFINITE', '2023-09-09', null, '2024-03-09', '2024-09-09', 'APPROVED', true, false, false);

-- doesn't have job contract during pay period (income > 3, moneySum < 2500, employmentStatus TEMPORARY, contractStart after creditEndMin or contractEnd before creditEndMax)
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Nemam', 'Ugovor', 'nemam@ugovor.com', '123.Auth', 'USER', 25);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 8);
INSERT INTO credit_request (client_id, client_income, money_sum, rate_number, client_employment_status, client_contract_start, client_contract_end, credit_end_min, credit_end_max, credit_status, recommended_approval, missed_payment, payment_overdue) VALUES (8, 1000.0, 100.0, 12, 'INDEFINITE', '2023-09-09', null, '2024-03-09', '2024-09-09', 'APPROVED', true, false, false);

-- hasn't payed off previous loans or has missed a payment (income > 3, moneySum < 2500, other is w/e won't get it anyways)
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Nisam', 'Placao', 'nisam@placao.com', '123.Auth', 'USER', 25);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 9);
INSERT INTO credit_request (client_id, client_income, money_sum, rate_number, client_employment_status, client_contract_start, client_contract_end, credit_end_min, credit_end_max, credit_status, recommended_approval, missed_payment, payment_overdue) VALUES (9, 1000.0, 100.0, 12, 'INDEFINITE', '2023-09-09', null, '2024-03-09', '2024-09-09', 'APPROVED', true, true, false);

-- should get credit (income > 3, moenySum < 2500, status INDEFINITE)
INSERT INTO bank_user (first_name, last_name, email, password, role, age) VALUES ('Dobijam', 'Kredit', 'dobijam@kredit.com', '123.Auth', 'USER', 25);
INSERT INTO bank_account (balance, credit_card_expiry_date, cvv, user_id) VALUES (500, '2025-1-1', 100, 10);
INSERT INTO credit_request (client_id, client_income, money_sum, rate_number, client_employment_status, client_contract_start, client_contract_end, credit_end_min, credit_end_max, credit_status, recommended_approval, missed_payment, payment_overdue) VALUES (10, 1000.0, 100.0, 12, 'INDEFINITE', '2023-09-09', null, '2024-03-09', '2024-09-09', 'APPROVED', true, false, false);
-- end of credit testing part of db
