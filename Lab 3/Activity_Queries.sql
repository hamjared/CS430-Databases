/*Add a new book to the Main library, ISBN # 96-42013-10510, shelf 8, floor 2, Title Growing your own Weeds, published by pubid 10000 on 6/24/2012.*/
INSERT INTO Book
VALUES ('96-42013-10510', 'Growing your own Weeds', 2012, 10000);

INSERT INTO StoredOn
VALUES ('96-42013-10510', 'Main', 8, 1);

/*Modify the number of copied of ISBN 96-42103-10907 to 8 in the Main library.*/

UPDATE StoredOn
Set TotalCopies = 8
where LibraryName = 'Main' AND ISBN = '96-42103-10907';


/* Delete Grace Slick from the Author table. FAILS */

DELETE FROM Author
WHERE FirstName = 'Grace' AND LastName = 'Slick';

/*Add Commander Adams to the author table, id 305, office phone 970-555-5555*/
insert into Author
VALUES (305, 'Commander', 'Adams');

insert into Phone
Values('970-555-5555', '(o)');

insert into AuthorToPhoneNumber
Values (305, '970-555-5555');

/* Add a new book to the South Park library, ISBN # 96-42013-10510, shelf 8, floor 3, Title Growing your own Weeds, published by pubid 10000 on 6/24/2012.*/
/* the book already exists in the book table, so just need to add it to the StoredOn table*/

INSERT INTO StoredOn
VALUES ('96-42013-10510', 'South Park', 8, 1);

/*Delete the book Missing Tomorrow from the Main Library.*/
DELETE FROM StoredOn
Where LibraryName = 'Main' and ISBN in (select ISBN from Book NATURAL JOIN StoredOn SO where Book.Title = 'Missing Tomorrow');

/*Add 2 new copies of Eating in the Fort in the South Park library.*/
UPDATE StoredOn
Set TotalCopies = TotalCopies + 2
where LibraryName = 'South Park' AND ISBN in (select ISBN FROM Book where Book.Title = 'Eating in the Fort');

/* Add a new book to the Main library, ISBN # 96-42013-10513, shelf 8, floor 2, Title Growing your own Weeds, published by pubid 90000 on 6/24/2012.
FAILS */
INSERT INTO Book
VALUES ('96-42013-10513', 'Growing your own Weeds', 2012, 90000);

INSERT INTO StoredOn
VALUES ('96-42013-10513', 'Main', 8, 1);

/* Print the final contents of the Audit table. */

SELECT *
FROM Audit;







