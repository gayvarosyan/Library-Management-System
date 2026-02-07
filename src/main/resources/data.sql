INSERT INTO category (name, description) VALUES
('Programming', 'Programming books'),
('Science Fiction', 'Science fiction novels'),
('History', 'Historical books');

INSERT INTO book (title, author, publish_year, genre, category_id) VALUES
('Java Basics', 'John Smith', 2020, 'Programming', 1),
('Spring in Action', 'Craig Walls', 2022, 'Programming', 1),
('Clean Code', 'Robert Martin', 2008, 'Programming', 1);

INSERT INTO members (full_name, email, phone, registration_date) VALUES
('Armen Sargsyan', 'armen@mail.com', '091-123456', '2021-08-10'),
('Ani Davtyan', 'ani@mail.com', '091-654321', '2022-01-15');

INSERT INTO member_book (member_id, book_id, borrowed_date) VALUES
(1, 1, '2024-01-10'),
(1, 2, '2024-01-15'),
(1, 3, '2024-02-01'),
(2, 1, '2024-02-10');


