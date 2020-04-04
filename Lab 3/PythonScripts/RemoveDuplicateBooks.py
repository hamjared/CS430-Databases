from Authors import writeFile

def main():
    removeDuplicates("SQLScripts/Main_insertIntoWrittenBy.sql", "SQLScripts/South Park_insertIntoWrittenBy.sql")


def removeDuplicates(file1, file2):
    f = open(file1, "r")
    line_list = f.read().splitlines()[1:]
    lineSet = set()
    for line in line_list:
        lineSet.add(line)
    f.close()

    f = open(file2, "r")
    line_list = f.read().splitlines()[1:]
    for line in line_list:
        lineSet.add(line)
    print(lineSet)
    f.close()

    insertIntoBooks = "INSERT INTO WrittenBy VALUES\n"
    for line in lineSet:
        insertIntoBooks += line + "\n"
    print(insertIntoBooks)

    writeFile(insertIntoBooks, "SQLScripts/9_All_inserttoWrittenBy.sql")


if __name__ == '__main__':
    main()