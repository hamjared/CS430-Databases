create table Member (
  MemberID Integer,
  FirstName VarChar(30),
  LastName VarChar(30),
  DOB Date NOT NULL,
  Gender VarChar(1),
  PRIMARY KEY(MemberID)
);

create table Publisher (
  PubID Integer,
  Pub_name VarChar(50),
  PRIMARY KEY(PubID)
);

create table Book (
  ISBN VarChar(20),
  Title VarChar(50),
  YearPublished Integer,
  PubID Integer,
  PRIMARY KEY(ISBN),
  FOREIGN KEY(PubID) REFERENCES Publisher(PubID)
);


create table Author (
  AuthorID Integer,
  FirstName VarChar(50),
  LastName VarChar(50),
  PRIMARY KEY (AuthorID)
);

create table Phone (
  PNumber VarChar(20),
  Type VarChar(10),
  PRIMARY KEY(PNumber)
);

create table WrittenBy (
  ISBN VarChar(20),
  AuthorID Integer,
  PRIMARY KEY (ISBN, AuthorID),
  FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
  FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID)
);

create table PublisherToPhoneNumber (
  PubID Integer,
  PNumber varchar(20),
  PRIMARY KEY (PubID, PNumber),
  FOREIGN KEY (PubID) REFERENCES Publisher(PubID) ON DELETE CASCADE,
  FOREIGN KEY (PNumber) REFERENCES Phone(PNumber)
);

create table AuthorToPhoneNumber (
  AuthorID Integer(11),
  PNumber varchar(20),
  PRIMARY KEY (AuthorID, PNumber),
  FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID) ON DELETE CASCADE,
  FOREIGN KEY (PNumber) REFERENCES Phone(PNumber)
);

create table BorrowedBy (
  ISBN VarChar(20),
  MemberID Integer,
  CheckoutDate DATE,
  CheckinDate Date,
  PRIMARY KEY (ISBN, MemberID, CheckoutDate),
  FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
  FOREIGN KEY (MemberID) REFERENCES Member(MemberID)
);

create table Library (
  Name VarChar(20),
  Street VARCHAR (30),
  City VARCHAR (20),
  State VARCHAR (2),
  PRIMARY KEY (Name)
);

create table Shelf(
    LibraryName VarChar(20),
    ShelfNumber INTEGER,
    Floor INTEGER,
    PRIMARY KEY (LibraryName, ShelfNumber),
    FOREIGN KEY (LibraryName) REFERENCES Library(Name) ON DELETE CASCADE
);

create table StoredOn(
    ISBN VARCHAR(20),
    LibraryName VARCHAR(20),
    ShelfNumber INTEGER,
    TotalCopies INTEGER,
    PRIMARY KEY (ISBN, LibraryName, ShelfNumber),
    FOREIGN KEY (LibraryName, ShelfNumber) REFERENCES Shelf(LibraryName, ShelfNumber),
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON DELETE CASCADE

);

create table Audit(
    id integer AUTO_INCREMENT,
    TableName VARCHAR(20),
    Action VARCHAR(20),
    DateTime TIMESTAMP,
    PRIMARY KEY (id)
);



