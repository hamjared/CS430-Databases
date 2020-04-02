from Authors import writeFile

def main():
    commaParseFile("./Data/NewBook.txt", "South Park")


def commaParseFile(filename, library):
    f=open(filename, "r")
    line_list = f.read().splitlines()

    insertBooks = 'INSERT INTO Book VALUES\n'
    insertIntoWrittenBy = "INSERT INTO WrittenBy VALUES\n"
    insertIntoStoredOn = "INSERT INTO StoredOn VALUES\n"
    insertIntoShelf = "INSERT INTO Shelf VALUES\n"
    i = 0
    seenFloors = []
    while i < len(line_list):
        line = line_list[i]

        comma_split = line.split(",")
        isbn = comma_split[0].strip()
        numCopies = comma_split[1].strip()
        shelf = comma_split[2].strip()
        floor = comma_split[3].strip()
        title = comma_split[4].strip()
        publisher = comma_split[5].strip()
        yearPublished = comma_split[6].strip().split("/")[2]
        # print(yearPublished)
        insertBooks += "(\'{}\', \'{}\', {}, {}),\n".format(isbn, title, yearPublished, publisher)
        insertIntoStoredOn += "(\'{}\', \'{}\', {}, {}),\n".format(isbn, library, shelf, numCopies)

        shelfFloor = (shelf,floor)
        if(shelfFloor not in seenFloors):
            seenFloors.append(shelfFloor)
            insertIntoShelf += "(\'{}\', {}, {}),\n".format(library, shelf, floor)
        if line_list[i+1][0:2] == '  ':
            insertIntoWrittenBy += parseAuthorIds(line_list[i+1].strip(), isbn)
            i+=1

        i += 1
    print(insertIntoWrittenBy)
    print("------------------")
    print(insertBooks[-2])
    insertIntoWrittenBy = insertIntoWrittenBy[:-2] + ';'
    insertMembers = insertBooks[:-2] + ';'
    insertIntoStoredOn = insertIntoStoredOn[:-2] +';'
    insertIntoShelf = insertIntoShelf[:-2] + ";"
    writeFile(insertMembers, "SQLScripts/{}_insertBooks.sql".format(library) )
    writeFile(insertIntoWrittenBy, "SQLScripts/{}_insertIntoWrittenBy.sql".format(library))
    writeFile(insertIntoStoredOn, "SQLScripts/{}_insertIntoStoredOn.sql".format(library))
    writeFile(insertIntoShelf, "SQLScripts/{}_insertIntoShelf.sql".format(library))

def parseAuthorIds(authorIds, isbn):
    print(authorIds)
    authorIds = authorIds.split(",")
    insertIntoWrittenBy = ""
    for id in authorIds:
        insertIntoWrittenBy += "(\'{}\', {}),\n".format(isbn, id.strip())
    return insertIntoWrittenBy






if __name__ == '__main__':
    main()
