from Authors import writeFile

def main():
    commaParseFile("./Data/Book.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()

    insertBooks = 'INSERT INTO Book VALUES\n'
    insertIntoWrittenBy = "INSERT INTO WrittenBy VALUES\n"
    i = 0
    while i < len(line_list):
        line = line_list[i]

        comma_split = line.split(",")
        isbn = comma_split[0].strip()
        title = comma_split[4].strip()
        publisher = comma_split[5].strip()
        yearPublished = comma_split[6].strip().split("/")[2]
        # print(yearPublished)
        insertBooks += "(\'{}\', \'{}\', {}, {}),\n".format(isbn, title, yearPublished, publisher)
        if line_list[i+1][0:2] == '  ':
            insertIntoWrittenBy += parseAuthorIds(line_list[i+1].strip(), isbn)
            i+=1

        i += 1
    print(insertIntoWrittenBy)
    print("------------------")
    print(insertBooks[-2])
    insertIntoWrittenBy = insertIntoWrittenBy[:-2] + ';'
    insertMembers = insertBooks[:-2] + ';'
    writeFile(insertMembers, "SQLScripts/insertBooks.sql" )
    writeFile(insertIntoWrittenBy, "SQLScripts/insertIntoWrittenBy.sql")

def parseAuthorIds(authorIds, isbn):
    print(authorIds)
    authorIds = authorIds.split(",")
    insertIntoWrittenBy = ""
    for id in authorIds:
        insertIntoWrittenBy += "(\'{}\', {}),\n".format(isbn, id.strip())
    return insertIntoWrittenBy






if __name__ == '__main__':
    main()
