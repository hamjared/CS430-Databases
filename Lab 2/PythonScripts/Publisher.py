
def main():
    commaParseFile("./Data/Publisher.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()
    print (line_list)
    insertPublishers = 'INSERT INTO Publisher VALUES\n'
    for line in line_list:
        split_line = line.split(",")
        pubID = int(split_line[0])
        name = split_line[1][1:]
        insertPublishers += "({}, \'{}\'),\n".format(pubID, name)

    insertPublishers = insertPublishers[:-2] + ";"
    print(insertPublishers)







if __name__ == '__main__':
    main()
