from Authors import writeFile

def main():
    commaParseFile("./Data/Book.txt")


def commaParseFile(filename):
    f=open(filename, "r")
    line_list = f.read().splitlines()
    i = 0
    while (i < len(line_list)):
        line = line_list[i]
        if(line_list[i+1][0:2] == '  '):
            parseMemberIds(line_list[i+1].strip())
            i+=1
        comma_split = line.split(",")
        isbn = comma_split[0].strip()
        title = comma_split[4].strip()
        publisher = comma_split[5].strip()
        yearPublished = comma_split[6].strip().split("/")[2]
        # print(yearPublished)
        i += 1


def parseMemberIds(memberIds):
    print(memberIds)






if __name__ == '__main__':
    main()
