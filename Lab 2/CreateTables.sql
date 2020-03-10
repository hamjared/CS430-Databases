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
