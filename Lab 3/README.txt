Run createtables.sql first
Then run the insert sql files in the order provided by the filenames
Then run the Setup_Queries.sql (Contains all queires for Setup #5)
Then Run createTriggers.sql (Setup #6)
Then run createView.sql (Setup #7 and #8)
Then run Activity_Queries (Activity #s 1-9)

Activities That Fail:

    Delete Grace Slick from the Author Table fails due to a foreign key constraint

    Add Add a new book to the Main library, ISBN # 96-42013-10513, shelf 8, floor 2,
    Title Growing your own Weeds, published by pubid 90000 on 6/24/2012 - Fails due to Foreign Key constraint on pubid
