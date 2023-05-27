INSERT INTO author (name) VALUES ('F. Scott Fitzgerald');
INSERT INTO author (name) VALUES ('Fyodor Dostoyevsky');
INSERT INTO author (name) VALUES ('Aldous Huxley');
INSERT INTO author (name) VALUES ('George Orwell');
INSERT INTO author (name) VALUES ('John Steinback');

INSERT INTO author (name) VALUES ('Herman Hesse');
INSERT INTO author (name) VALUES ('Robert Cecil Martin');


INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('The Great Gatsby', 1, 1000, '2023-4-25', '1925-4-10', 'NOVEL', 500);
INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('The Brothers Karamazov', 2, 1000, '2020-1-1', '1880-11-01', 'NOVEL', 600);
INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('Brave New World', 3, 1000, '2020-1-1', '1932-01-01', 'NOVEL', 700);
INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('Nineteen Eighty-Four', 4, 1000, '2020-1-1', '1949-06-08', 'NOVEL', 800);
INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('Of Mice and Men', 5, 1000, '2020-1-1', '2023-03-01', 'EDUCATION', 900);
INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('Siddhartha', 6, 100, '2022-1-1', '2023-01-01', 'NOVEL', 900);
INSERT INTO book (name, author_id, number_of_copies, added_to_bookstore_date, publish_date, genre, price)
	VALUES ('Clean Code', 7, 100, '2022-1-1', '2023-01-01', 'EDUCATION', 900);


INSERT INTO bookstore_user (name, email, password, role) VALUES ('Petar Petrovic', 'pera@pera.com', '123.Auth', 'ADMIN');
INSERT INTO bookstore_user (name, email, password, role) VALUES ('Ana Anic', 'ana@ana.com', '123.Auth', 'USER');
INSERT INTO bookstore_user (name, email, password, role) VALUES ('Milos Milic', 'milos@milos.com', '123.Auth', 'USER');

INSERT INTO book_order (discount, user_id) VALUES (0, 1);
INSERT INTO book_order (discount, user_id) VALUES (0, 2);


INSERT INTO item (discount, quantity, book_id, order_id) VALUES (0, 1, 1, 1);
INSERT INTO item (discount, quantity, book_id, order_id) VALUES (0, 1, 2, 1);
INSERT INTO item (discount, quantity, book_id, order_id) VALUES (5, 2, 3, 2);
INSERT INTO item (discount, quantity, book_id, order_id) VALUES (0, 1, 4, 2);

INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (2, 4, 2);

INSERT INTO review (rating, book_id, user_id) VALUES (5, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 2);

INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 3);

INSERT INTO review (rating, book_id, user_id) VALUES (1, 5, 3);
INSERT INTO review (rating, book_id, user_id) VALUES (2, 5, 3);

INSERT INTO review (rating, book_id, user_id) VALUES (4, 2, 1);
INSERT INTO review (rating, book_id, user_id) VALUES (3, 1, 1);
INSERT INTO user_favourite_genres (user_id, genre) VALUES (1, 'NOVEL');
INSERT INTO user_favourite_genres (user_id, genre) VALUES (1, 'EDUCATION');
INSERT INTO user_favourite_genres (user_id, genre) VALUES (2, 'NOVEL');
INSERT INTO user_favourite_genres (user_id, genre) VALUES (2, 'ROMANCE');
INSERT INTO user_favourite_genres (user_id, genre) VALUES (3, 'NOVEL');
