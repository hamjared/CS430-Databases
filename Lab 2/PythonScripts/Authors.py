
def main():
    commaParseFile("./Data/Author.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()
    insertAuthors = 'INSERT INTO Author VALUES\n'
    insertAuthorToPhoneNumber = 'INSERT INTO AuthorToPhoneNumber VALUES\n'
    insertPhoneNumber = 'INSERT INTO Phone VALUES\n'
    for line in line_list:
        split_line = line.split(",")
        authorID = int(split_line[0])
        firstName = split_line[1].split(" ")[1]
        lastName = split_line[1].split(" ")[2]
        parsedPhoneNumbers = parsePhoneNumbers(split_line[2:], authorID)
        insertAuthors += "({}, \'{}\', \'{}\'),\n".format(authorID, firstName, lastName)
        insertAuthorToPhoneNumber += parsedPhoneNumbers[0]
        insertPhoneNumber += parsedPhoneNumbers[1]
    f.close()
    insertAuthors = insertAuthors[:-2] + ";"
    insertAuthorToPhoneNumber = insertAuthorToPhoneNumber[:-2] + ";"
    insertPhoneNumber = insertPhoneNumber[:-2] + ";"
    removeDuplicatePhoneNumbers(insertPhoneNumber)
    writeFile(insertAuthors, "SQLScripts/insertAuthors.sql")
    writeFile(insertAuthorToPhoneNumber, "SQLScripts/insertAuthorToPhoneNumber.sql")
    writeFile(insertPhoneNumber, "SQLScripts/insertPhoneNumber.sql")

def writeFile(data, filename):
    f = open(filename, "w+")
    f.write(data)
    f.close()

def parsePhoneNumbers(phoneNumbers, authorID):
    authorToPhoneNumber = ""
    insertIntoPhoneNumber = ""
    for phoneNumber in phoneNumbers:
        split_line = phoneNumber.lstrip().split(" ")
        try:
            number = split_line[0]
            type = split_line[1]
        except(IndexError):
            continue
        authorToPhoneNumber = authorToPhoneNumber + "(\'{}\', \'{}\'),\n".format(number, authorID)
        insertIntoPhoneNumber += "(\'{}\', \'{}\'),\n".format(number, type)
    return authorToPhoneNumber, insertIntoPhoneNumber

def removeDuplicatePhoneNumbers(insertPhoneNumberCommand):
    lines = insertPhoneNumberCommand.splitlines()
    newLines = []
    numbers = []
    for line in lines:

        try:
            number = line.split('\'')[1]
            if number not in numbers:
                newLines.append(line)
                numbers.append(numbers)

        except (IndexError):
            continue

    print('\n'.join(newLines))






if __name__ == '__main__':
    main()
