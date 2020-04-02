from Authors import writeFile
import dateparser
import datetime

def main():
    commaParseFile("./Data/Members.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()

    insertMembers = 'INSERT INTO Member VALUES\n'
    insertIntoBorrowedBy = "INSERT INTO BorrowedBy VALUES\n"
    i = 0
    while i < len(line_list):
        line = line_list[i]

        comma_split = line.split(",")
        print(comma_split[1].strip().split(" "))
        memberId = comma_split[0]
        firstName = comma_split[1].strip().split(" ")[0]
        lastName =  comma_split[1].strip().split(" ")[1]
        Gender = comma_split[2].strip()
        DOB = comma_split[3]
        DOB = reformatDate(DOB)

        insertMembers += '({}, \'{}\', \'{}\', \'{}\', \'{}\'),\n'.format(memberId, firstName, lastName, DOB, Gender)

        while line_list[i+1][0:2] == '  ' :
            comma_split = line_list[i+1].split(",")
            isbn = comma_split[0]
            checkoutDate = comma_split[1]
            checkoutDate = reformatDate(checkoutDate)
            try:
                checkinDate = comma_split[2]
                checkinDate = reformatDate(checkinDate)
                insertIntoBorrowedBy += '(\'{}\', {}, \'{}\', \'{}\'),\n'.format(isbn.strip(), memberId, checkoutDate,
                                                                             checkinDate)
            except(IndexError):
                checkinDate = 'Null'
                insertIntoBorrowedBy += '(\'{}\', {}, \'{}\', {}),\n'.format(isbn.strip(), memberId, checkoutDate, checkinDate)
            i+=1
            try:
                line_list[i + 1][0:2]
            except (IndexError):
                break
        i += 1

    print(insertMembers)
    print("--------------")
    print(insertIntoBorrowedBy)
    insertMembers = insertMembers[:-2] + ";"
    insertIntoBorrowedBy = insertIntoBorrowedBy[:-2] + ";"
    writeFile(insertMembers, "SQLScripts/insertMembers.sql")
    writeFile(insertIntoBorrowedBy, "SQLScripts/insertIntoBorrowedBy.sql")

def reformatDate(date):
    date = dateparser.parse(date)
    return "{}-{}-{}".format(date.year, date.month, date.day)


if __name__ == '__main__':
    main()
