import sys
def inversematrix(thematrix): #Code for inversing the matrix.
    global counter_x
    identity=augmented(thematrix)
    counter_x=0
    for i in range(len(thematrix)):
        divideline(thematrix,identity)
        theline=thematrix[counter_x]
        theline2=identity[counter_x]
        thenumber = thematrix[counter_x][counter_x]
        for j in range(len(thematrix)):
            coefficient=thematrix[j][i]/thenumber
            if thematrix[j]!=theline:
                for a in range(len(theline)):
                    thematrix[j][a]-=theline[a]*coefficient
                    identity[j][a]-=theline2[a]*coefficient
        counter_x += 1
    for i in range(len(identity)):
        for j in range(len(identity)):
            identity[j][i] = round(identity[j][i])
    return identity
def divideline(thematrix,identity):
    global counter_x
    global error
    def divide(thematrix,identity):
        global error
        pivotnumber = thematrix[counter_x][counter_x]
        linecoefficient = 1 / pivotnumber
        for column in range(len(thematrix)):
            a = linecoefficient * thematrix[counter_x][column]
            a1 = linecoefficient * identity[counter_x][column]
            thematrix[counter_x][column] = a
            identity[counter_x][column] = a1
            error=False
    for i in range(1,len(thematrix)+1):
        try:
            divide(thematrix,identity)
            if error==False:
                error=True
                break
        except ZeroDivisionError:
            a,b,c,d=thematrix[counter_x+i].copy(),thematrix[counter_x].copy(),identity[counter_x+i].copy(),identity[counter_x].copy()
            thematrix[counter_x],thematrix[counter_x+i],identity[counter_x],identity[counter_x+i]=a,b,c,d
def augmented(thematrix):
    a=len(thematrix)
    counter=0
    augmented=[[0 for i in range(a)] for x in range(a)]
    for i in range(a):
        augmented[counter][counter]=1
        counter+=1
    return augmented
try:
    assert len(sys.argv) == 5, "Parameter number error" #Check whether unnecessary parameter entered.
    assert sys.argv[1] == "enc" or sys.argv[1] == "dec", "Undefined parameter error" #Check whether different operation type entered.
    file1 = open(sys.argv[3], "r")
    assert sys.argv[3][-4:] == ".txt", "The input file could not be read error" #Check whether the file is txt or not.
    assert len(file1.readline()) > 0, "Input file is empty error"#Check if input file is empty.
    file1.close()
    file2 = open(sys.argv[2], "r")
    lines = file2.readlines()
    assert sys.argv[2][-4:] == ".txt", "Key file could not be read error"  # Check whether the key file is txt or not.
    assert len(lines) != 0, "Key file is empty error"  # Check whether key file is empty.
    matrixkey = []
    for line in lines:
        matrixkey.append(line.split()[0].split(","))
    for i in range(len(matrixkey)):
        for j in range(len(matrixkey)):
            matrixkey[i][j] = int(matrixkey[i][j])
    alphabet = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "]
    letters = {i: alphabet.index(i) + 1 for i in alphabet}#Values of letters.
    inverseletters = {alphabet.index(i) + 1: i for i in alphabet}
    if sys.argv[1]=="enc": #"If it's encode"
        file1 = open(sys.argv[3], "r")
        themessage="".join([i.capitalize()  for i in file1.readline()])
        if len(themessage)%len(matrixkey)!=0: #If the groups are unbalanced, add whitespace.
            b=len(matrixkey)-(len(themessage)%len(matrixkey))
            for i in range(b):
                themessage+=" "
        groups=[] #Set the groups.
        for i in range(0,len(themessage),len(matrixkey)):
            groups.append(themessage[i:i+len(matrixkey)])
        values_of_groups=[]#Find values of groups.
        for i in groups:
            group=[letters[i[j]] for j in range(len(i))]
            values_of_groups.append(group)
        encodedmessage=[]#Encode.
        for i in values_of_groups:
            encodedgroup=[]
            for j in range(len(i)):
                x=0
                for k in range(len(matrixkey)):
                    x+=matrixkey[j][k]*i[k]
                encodedgroup.append(x)
            encodedmessage.append(encodedgroup)
        encodedtext=[a for group in encodedmessage for a in group]
        encodedfile=open(sys.argv[4],"w") #Write decoded text.
        for i in range(len(encodedtext)):
            if i!=len(encodedtext)-1:
                encodedfile.write(f"{encodedtext[i]},")
            else:
                encodedfile.write(f"{encodedtext[i]}")
    elif sys.argv[1]=="dec": #If it's decode
        file1=open(sys.argv[3],"r")
        inversekey=inversematrix(matrixkey) #Find inverse of matrix key.
        mylist=[int(i) for i in file1.readline().split(",")]
        ciphermessage=[mylist[i:i+len(inversekey)] for i in range(0,len(mylist),len(inversekey))]
        decodedmessage=[]
        for i in  ciphermessage:#Find the text by multiplying with inverse matrix key.
            decodedgroup=[]
            for j in range(len(i)):
                x=0
                for k in range(len(inversekey)):
                    x+=inversekey[j][k]*i[k]
                    x=round(x)
                decodedgroup.append(x)
            decodedmessage.append(decodedgroup)
        decodedtext="".join([inverseletters[a] for i in decodedmessage for a in i])
        outputfile=open(sys.argv[4],"w") #Write decoded text.
        outputfile.write(decodedtext)
except AssertionError as msg: #Write message of assertion.
    print(msg)
except FileNotFoundError as msg:
    if msg.filename==sys.argv[2]: #If it's key file.
        print("Key file not found error")
    elif msg.filename==sys.argv[3]:#If it's input file.
        print("Input file not found error")
except KeyError:
    print("Invalid character in input file error") #Key error if the arguments in input file can't match with dictionary.
except ValueError:
    print("Invalid character in key file error")#It can't be converted to integer if there is invalid character in key file.








