select *
FROM BorrowedBy;

SELECT FirstName, LastName, MemberID, Title
FROM BorrowedBy natural join Book B natural join Member M
WHERE CheckinDate is null
order by MemberID;