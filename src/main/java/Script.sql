CREATE DATABASE library;
USE library;
CREATE TABLE library (
                         library_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         phone VARCHAR(20)
);
CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) UNIQUE NOT NULL,
                      birthday DATE NOT NULL
);
CREATE TABLE book (
                      book_id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      publisher VARCHAR(255) NOT NULL,
                      library_id INT,
                      FOREIGN KEY (library_id) REFERENCES library(library_id)
);
CREATE TABLE reservation (
                             reservation_id INT AUTO_INCREMENT PRIMARY KEY,
                             reservation_date DATE NOT NULL,
                             return_date DATE,
                             user_id INT,
                             book_id INT,
                             FOREIGN KEY (user_id) REFERENCES user(user_id),
                             FOREIGN KEY (book_id) REFERENCES book(book_id)
);