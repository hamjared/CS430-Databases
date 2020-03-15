
def main():
    commaParseFile("./Data/Publisher.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()
    print (line_list)
    insertPublishers = 'INSERT INTO Publisher VALUES\n'
    insertPublishersToPhoneNumber = 'INSERT INTO PublisherToPhoneNumber VALUES\n'
    for line in line_list:
        split_line = line.split(",")
        pubID = int(split_line[0])
        name = split_line[1][1:]
        pubPhoneNumber = split_line[2][1:].split(" ")[0]
        insertPublishers += "({}, \'{}\'),\n".format(pubID, name)
        insertPublishersToPhoneNumber += "({}, \'{}\'),\n".format(pubID, pubPhoneNumber)

    insertPublishers = insertPublishers[:-2] + ";"
    insertPublishersToPhoneNumber = insertPublishersToPhoneNumber[:-2] + ";"
    print(insertPublishers)
    print("---------------------------------------")
    print(insertPublishersToPhoneNumber)







if __name__ == '__main__':
    main()
