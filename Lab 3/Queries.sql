insert into Author
VALUES (305, 'Commander', 'Adams');


insert into Phone
Values('970-555-5555', '(o)');

insert into AuthorToPhoneNumber
Values (305, '970-555-5555');


INSERT INTO Book
VALUES ('96-42013-10510', 'Growing your own Weeds', 2012, 10000);

INSERT INTO StoredOn
VALUES ('96-42013-10510', 'Main', 8, 1);


select Title, Authors, ShelfNumber, LibraryName
FROM Book_Locations
ORDER BY Title;


