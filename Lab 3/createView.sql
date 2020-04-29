CREATE SQL SECURITY INVOKER VIEW Book_Locations AS
SELECT Book.Title, GROUP_CONCAT(Author.LastName, ' ',Author.FirstName) as Authors, Shelf.LibraryName,  Shelf.Floor, Shelf.ShelfNumber
FROM (Book NATURAL JOIN WrittenBy NATURAL JOIN Author) NATURAL JOIN StoredOn NATURAL JOIN Shelf
Group by  Book.Title, Shelf.LibraryName;

select Title, Authors, ShelfNumber, LibraryName
FROM Book_Locations
ORDER BY Title;