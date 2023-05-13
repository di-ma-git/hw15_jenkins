DROP SCHEMA

    IF EXISTS books;
CREATE SCHEMA books COLLATE = utf8_general_ci;

USE books;

CREATE TABLE books (
                       book_id INT AUTO_INCREMENT,
                       book_name VARCHAR(255) NOT NULL,
                       book_description TEXT,
                       book_publish_date DATE,
                       author_id INT NOT NULL,
                       PRIMARY KEY(book_id)
);


CREATE TABLE authors (
                         author_id INT NOT NULL,
                         author_name VARCHAR(255) NOT NULL,
                         author_birth_date DATE,
                         PRIMARY KEY(author_id)
);

INSERT INTO books(book_name, book_description, book_publish_date, author_id)
VALUES(
          'Martin Eden',
          'Living in Oakland at the beginning of the 20th century, Martin Eden struggles to rise above his destitute, proletarian circumstances through an intense and passionate pursuit of self-education, hoping to achieve a place among the literary elite.',
          STR_TO_DATE('01,01,1799', '%d,%m,%Y'),
          777
      );

INSERT INTO books(book_name, book_description, book_publish_date, author_id)
VALUES(
          'One Hundred Years of Solitude',
          'Is the story of seven generations of the Buendía Family in the town of Macondo. The founding patriarch of Macondo, José Arcadio Buendía, and Úrsula Iguarán, his wife (and first cousin), leave their hometown in Riohacha, Colombia, after José Arcadio kills Prudencio Aguilar after a cockfight for suggesting José Arcadio was impotent.',
          STR_TO_DATE('01,01,1799', '%d,%m,%Y'),
          777
      );

INSERT INTO authors
VALUES(
          555,
          'PushkinFromMysqlEmbedded',
          STR_TO_DATE('06,June,1799', '%d,%M,%Y')
      );

INSERT INTO authors
VALUES(
          666,
          'PushkinFromMysqlEmbedded',
          STR_TO_DATE('06,June,1799', '%d,%M,%Y')
      );

INSERT INTO authors
VALUES(
          777,
          'PushkinFromMysqlEmbedded',
          STR_TO_DATE('06,June,1799', '%d,%M,%Y')
      );


COMMIT;
ALTER TABLE books ADD FOREIGN KEY (author_id) REFERENCES authors(author_id);