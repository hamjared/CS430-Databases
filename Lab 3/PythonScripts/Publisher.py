from Authors import writeFile, parsePhoneNumbers

def main():
    commaParseFile("./Data/Publisher.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()
    insertPublishers = 'INSERT INTO Publisher VALUES\n'
    insertPublishersToPhoneNumber = 'INSERT INTO PublisherToPhoneNumber VALUES\n'
    insertPhoneNumbersPublishers = "INSERT INTO Phone VALUES\n"
    for line in line_list:
        split_line = line.split(",")
        pubID = int(split_line[0])
        name = split_line[1][1:]
        pubPhoneNumber = split_line[2][1:].split(" ")[0]
        insertPublishers += "({}, \'{}\'),\n".format(pubID, name)

        parsedPhoneNumbers = parsePhoneNumbers(split_line[2:], pubID)
        insertPublishersToPhoneNumber += parsedPhoneNumbers[0]
        insertPhoneNumbersPublishers += parsedPhoneNumbers[1]
    f.close()
    insertPublishers = insertPublishers[:-2] + ";"
    insertPublishersToPhoneNumber = insertPublishersToPhoneNumber[:-2] + ";"
    writeFile(insertPublishers, "SQLScripts/insertPublishers.sql")
    writeFile(insertPublishersToPhoneNumber, "SQLScripts/insertPhoneNumbersToPublishers.sql")
    writeFile(insertPhoneNumbersPublishers, "SQLScripts/insertPhoneNumbersPublishers.sql")







if __name__ == '__main__':
    main()
