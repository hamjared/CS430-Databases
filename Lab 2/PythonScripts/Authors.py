
def main():
    commaParseFile("./Data/Author.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()
    print (line_list)
    insertAuthors = 'INSERT INTO Author VALUES\n'
    for line in line_list:
        split_line = line.split(",")
        authorID = int(split_line[0])
        firstName = split_line[1].split(" ")[1]
        lastName = split_line[1].split(" ")[2]
        insertAuthors += "({}, \'{}\', \'{}\'),\n".format(authorID, firstName, lastName)

    insertAuthors = insertAuthors[:-2] + ";"
    print(insertAuthors)







if __name__ == '__main__':
    main()
