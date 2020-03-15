create table Member (
  MemberID Integer,
  FirstName VarChar(30),
  LastName VarChar(30),
  DOB Date NOT NULL,
  PRIMARY KEY(MemberID)
)

create table Book (
  ISBN Integer,
  Title VarChar(50),
  YearPublished Integer,
  MemberID Integer,
  PubID Integer,
  PRIMARY KEY(ISBN),
  FOREIGN KEY(MemberID) REFERENCES Member,
  FOREIGN KEY(PubID) REFERENCES Publisher
)

create table Publisher (
  PubID Integer,
  Pub_name VarChar(50),
  PRIMARY KEY(PubID)
)

create table Author (
  AuthorID Integer,
  FirstName VarChar(50),
  LastName VarChar(50),
  PRIMARY KEY (AuthorID)
)

create table Phone (
  PNumber Integer,
  Type VarChar(10),
  PRIMARY KEY(PNumber)
)

create table WrittenBy (
  ISBN VarChar (20),
  AuthorID Integer,
  PRIMARY KEY (ISBN, AuthorID)
  FOREIGN KEY (ISBN) REFERENCES Book,
  FOREIGN KEY (AuthorID) REFERENCES Author
)

create table PublisherToPhoneNumber (
  PubID Integer,
  PNumber Integer,
  PRIMARY KEY (PubID, PNumber),
  FOREIGN KEY (PubID) REFERENCES Publisher ON DELETE CASCADE,
  FOREIGN KEY (PNumber) REFERENCES Phone
)

create table AuthorToPhoneNumber (
  AuthorID Integer,
  PNumber Integer,
  PRIMARY KEY (AuthorID, PNumber),
  FOREIGN KEY (AuthorID) REFERENCES Author ON DELETE CASCADE,
  FOREIGN KEY (PNumber) REFERENCES Phone
)