select *
from Library
ORDER BY Name;

select *
from Shelf
ORDER BY LibraryName, Floor, ShelfNumber;

select*
from StoredOn
ORDER BY ISBN;

/* For each book that has copies in both libraries, list the book name, shelf #, and library sorted by book name. */

SELECT Book.Title, SO1.ShelfNumber, SO1.LibraryName
FROM Book, StoredOn SO1, StoredOn SO2
WHERE Book.ISBN = SO1.ISBN and
    SO1.ISBN = SO2.ISBN and
    SO1.LibraryName != SO2.LibraryName
ORDER BY Book.Title;

/*For each shelf, list the shelf, library, and number of titles sorted by library and shelf. */

select ShelfNumber, LibraryName, count(ISBN)
FROM StoredOn
GROUP BY ShelfNumber, LibraryName
ORDER BY LibraryName, ShelfNumber;