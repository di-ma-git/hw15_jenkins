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
	STR_TO_DATE('1909', '%YYYY'),
	1
);

INSERT INTO books(book_name, book_description, book_publish_date, author_id)
VALUES(
	'One Hundred Years of Solitude',
	'Is the story of seven generations of the Buendía Family in the town of Macondo. The founding patriarch of Macondo, José Arcadio Buendía, and Úrsula Iguarán, his wife (and first cousin), leave their hometown in Riohacha, Colombia, after José Arcadio kills Prudencio Aguilar after a cockfight for suggesting José Arcadio was impotent.',
	STR_TO_DATE('1967', '%YYYY'),
	3
);

INSERT INTO books(book_name, book_description, book_publish_date, author_id)
VALUES(
	'White Fang',
	'The story begins before the wolf-dog hybrid is born, with two men and their sled dog team on a journey to deliver the coffin of Lord Alfred to a remote town named Fort McGurry in the higher area of the Yukon Territory.',
	STR_TO_DATE('1906', '%YYYY'),
	1
);

INSERT INTO books(book_name, book_description, book_publish_date, author_id)
VALUES(
	'The Night in Lisbon',
	'The story takes place in the opening months of World War II. Josef Schwarz is a refugee who offers his visa and tickets for America to another refugee desperate to leave Lisbon.',
	STR_TO_DATE('1962', '%YYYY'),
	2
);

INSERT INTO books(book_name, book_description, book_publish_date, author_id)
VALUES(
	'Arch of Triumph',
	'The novel is set in Paris, in 1939. Despite having no permission to perform surgery, stateless refugee Ravic, a very accomplished German surgeon, has been “ghost-operating” on patients for two years on the behalf of two less-skillful French physicians.',
	STR_TO_DATE('1945', '%YYYY'),
	2
);

INSERT INTO authors
VALUES(
	1,
	'Jack London',
	STR_TO_DATE('12-JAN-1876', '%d-%M-%Y')
	);
	
INSERT INTO authors
VALUES(
	2,
	'Erich Maria Remarque',
	STR_TO_DATE('22-JUN-1898', '%d-%M-%Y')
	);
	
INSERT INTO authors
VALUES(
	3,
	'Gabriel García Márquez',
	STR_TO_DATE('6-MAR-1927', '%d-%M-%Y')
	);
	
COMMIT;
ALTER TABLE books ADD FOREIGN KEY (author_id) REFERENCES authors(author_id);