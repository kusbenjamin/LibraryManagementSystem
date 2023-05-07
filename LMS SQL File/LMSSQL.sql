CREATE SCHEMA `library`;

CREATE TABLE `library`.`users` (
`UID` INT(11) NOT NULL AUTO_INCREMENT,
`USERNAME` VARCHAR(45) NOT NULL,
`PASSWORD` VARCHAR(45) NOT NULL,
PRIMARY KEY (`UID`));

CREATE TABLE `library`.`books` (
`bid` INT(11) NOT NULL AUTO_INCREMENT,
`book_isbn` VARCHAR(45) NOT NULL,
`book_name` VARCHAR(45) NOT NULL,
`book_author` VARCHAR(45) NOT NULL,
PRIMARY KEY (`bid`));

CREATE TABLE `library`.`issued_books` (
`iid` INT(11) NOT NULL AUTO_INCREMENT,
`book_isbn` VARCHAR(45) NOT NULL,
`book_name` VARCHAR(45) NOT NULL,
`book_author` VARCHAR(45) NOT NULL,
`borrower_name` VARCHAR(45) NOT NULL,
`borrower_address` VARCHAR(45) NOT NULL,
`borrower_email` VARCHAR(45) NOT NULL,
PRIMARY KEY (`iid`));

INSERT INTO `library`.`users`(USERNAME, PASSWORD) VALUES ('Admin1', 'GummyBear973');
INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('Example ISBN Here', 'Example Title Here', 'Example Author Here');
INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('9780763630188', 'Moby Dick', 'Herman Melville');
INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('9780316769533', 'Catcher in the Rye', 'J.D. Salinger');
INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('9780143035008', 'Anna Karenina', 'Leo Tolstoy');
INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('9780545453578', 'Charlie the Ranch Dog', 'Diane DeGroate');
INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('9780743273565', 'The Great Gatsby', 'F. Scott Fitzgerald');
