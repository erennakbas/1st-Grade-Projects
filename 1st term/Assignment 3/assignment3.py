import sys
def initialize():
    global thesquares, row_column_of_thesquares, theboard, blackimportantpieces, blackpawns, whiteimportantpieces, whitepawns,positionofpieces,inversedict
    thesquares=[[i for i in ["a"+str(a),"b"+str(a),"c"+str(a),"d"+str(a),"e"+str(a),"f"+str(a),"g"+str(a),"h"+str(a)]] for a in range(8,0,-1)]
    row_column_of_thesquares={point:[(thesquares.index(line)),thesquares[thesquares.index(line)].index(point)] for line in thesquares for point in thesquares[thesquares.index(line)]}
    inversedict = {str((thesquares.index(line)))+str(thesquares[thesquares.index(line)].index(point)):point for line in thesquares for point in thesquares[thesquares.index(line)]}
    theboard=[["  " for i in range(0,8)] for a in range(0,8)]
    blackimportantpieces=["R1","N1","B1","QU","KI","B2","N2","R2"]
    blackpawns=["P"+str(a) for a in range(1,9)]
    whiteimportantpieces=["r1","n1","b1","qu","ki","b2","n2","r2"]
    whitepawns=["p"+str(a) for a in range(1,9)]
    a=0
    positionofpieces={}
    while a<len(whiteimportantpieces):
        positionofpieces.update({whiteimportantpieces[a]: thesquares[7][a]})
        positionofpieces.update({whitepawns[a]: thesquares[6][a]})
        positionofpieces.update({blackimportantpieces[a]:thesquares[0][a]})
        positionofpieces.update({blackpawns[a]: thesquares[1][a]})
        a+=1
    for i in positionofpieces:
        x=positionofpieces.get(i)
        c=row_column_of_thesquares[x]
        theboard[c[0]][c[1]]=i
def displayboard(chessboard):
    print("------------------------")
    for i in chessboard:
        print(*i)
    print("------------------------")
def movementlist(piece):
    letterdigit = positionofpieces.get(piece)
    position = row_column_of_thesquares[letterdigit]
    if piece[0]=="p":
        list1=[]
        if 0<position[0]<=7:
            a=str(position[0]-1)+str(position[1])
            x=inversedict[a]
            y=row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] in blackimportantpieces or theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]]=="  ":
                list1.append(x)
        return list1
    elif piece[0]=="P":
        list1 = []
        if 0 <= position[0] < 7:
            a = str(position[0] + 1) + str(position[1])
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] in whiteimportantpieces or theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]]=="  ":
                list1.append(x)
        return list1
    elif piece[0]=="r" or piece[0]=="R":
        up=position.copy()
        down = position.copy()
        right = position.copy()
        left = position.copy()
        list1=[]
        while 0<up[0]<=7:
            up[0]-=1
            up[0],up[1]=str(up[0]),str(up[1])
            a="".join(up)
            x=inversedict[a]
            y=row_column_of_thesquares[x]
            if theboard[y[0]][y[1]]=="  ":
                list1.append(x)
            if piece[0]=="r":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            up[0], up[1] = int(up[0]), int(up[1])
        while 0<= down[0] < 7:
            down[0] += 1
            down[0], down[1] = str(down[0]), str(down[1])
            a = "".join(down)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(x)
            if piece[0] == "r":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            down[0], down[1] = int(down[0]), int(down[1])
        while 0<=right[1]<7:
            right[1] += 1
            right[0], right[1] = str(right[0]), str(right[1])
            a = "".join(right)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(x)
            if piece[0] == "r":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            right[0], right[1] = int(right[0]), int(right[1])
        while 0<left[1]<=7:
            left[1] -= 1
            left[0], left[1] = str(left[0]), str(left[1])
            a = "".join(left)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(x)
            if piece[0] == "r":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            left[0], left[1] = int(left[0]), int(left[1])
        return list1
    elif piece[0]=="b" or piece[0]=="B":
        topright=position.copy()
        topleft=position.copy()
        leftbottom=position.copy()
        rightbottom=position.copy()
        list2=[]
        if piece[0]=="b":
            while 0<topright[0]<=7 and 0<=topright[1]<7:
                topright[0]-=1
                topright[1]+=1
                topright[0], topright[1] = str(topright[0]), str(topright[1])
                a="".join(topright)
                x=inversedict[a]
                y=row_column_of_thesquares[x]
                if theboard[y[0]][y[1]]=="  ":
                    list2.append(inversedict[a])
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list2.append(inversedict[a])
                    break
                topright[0], topright[1] = int(topright[0]),int(topright[1])
            while 0<topleft[0]<=7 and 0<topleft[1]<=7:
                topleft[0]-=1
                topleft[1]-=1
                topleft[0], topleft[1] = str(topleft[0]), str(topleft[1])
                a = "".join(topleft)
                x = inversedict[a]
                y = row_column_of_thesquares[x]
                if theboard[y[0]][y[1]] == "  ":
                    list2.append(inversedict[a])
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list2.append(inversedict[a])
                    break
                topleft[0], topleft[1] = int(topleft[0]), int(topleft[1])
            return list2
        else:
            list2 = []
            while 0 <= rightbottom[0] < 7 and 0 <= rightbottom[1] < 7:
                rightbottom[0] += 1
                rightbottom[1] += 1
                rightbottom[0], rightbottom[1] = str(rightbottom[0]), str(rightbottom[1])
                a = "".join(rightbottom)
                x = inversedict[a]
                y = row_column_of_thesquares[x]
                if theboard[y[0]][y[1]] == "  ":
                    list2.append(inversedict[a])
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list2.append(inversedict[a])
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                rightbottom[0], rightbottom[1] = int(rightbottom[0]), int(rightbottom[1])
            while 0 <= leftbottom[0] < 7 and 0 < leftbottom[1] <= 7:
                leftbottom[0] += 1
                leftbottom[1] -= 1
                leftbottom[0], leftbottom[1] = str(leftbottom[0]), str(leftbottom[1])
                a = "".join(leftbottom)
                x = inversedict[a]
                y = row_column_of_thesquares[x]
                if theboard[y[0]][y[1]] == "  ":
                    list2.append(inversedict[a])
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list2.append(inversedict[a])
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                leftbottom[0], leftbottom[1] = int(leftbottom[0]), int(leftbottom[1])
            return list2
    elif piece[0]=="q" or piece[0]=="Q":
        topright = position.copy()
        topleft = position.copy()
        leftbottom = position.copy()
        rightbottom = position.copy()
        up = position.copy()
        down = position.copy()
        right = position.copy()
        left = position.copy()
        list1=[]
        while 0<up[0]<=7:
            up[0]-=1
            up[0],up[1]=str(up[0]),str(up[1])
            a="".join(up)
            x=inversedict[a]
            y=row_column_of_thesquares[x]
            if theboard[y[0]][y[1]]=="  ":
                list1.append(x)
            if piece[0]=="q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            up[0], up[1] = int(up[0]), int(up[1])
        while 0<= down[0] < 7:
            down[0] += 1
            down[0], down[1] = str(down[0]), str(down[1])
            a = "".join(down)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(x)
            if piece[0] == "q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            down[0], down[1] = int(down[0]), int(down[1])
        while 0<=right[1]<7:
            right[1] += 1
            right[0], right[1] = str(right[0]), str(right[1])
            a = "".join(right)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(x)
            if piece[0] == "q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            right[0], right[1] = int(right[0]), int(right[1])
        while 0<left[1]<=7:
            left[1] -= 1
            left[0], left[1] = str(left[0]), str(left[1])
            a = "".join(left)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(x)
            if piece[0] == "q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(x)
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(x)
                    break
            left[0], left[1] = int(left[0]), int(left[1])
        while 0<topright[0]<=7 and 0<=topright[1]<7:
            topright[0]-=1
            topright[1]+=1
            topright[0], topright[1] = str(topright[0]), str(topright[1])
            a="".join(topright)
            x=inversedict[a]
            y=row_column_of_thesquares[x]
            if theboard[y[0]][y[1]]=="  ":
                list1.append(inversedict[a])
            if piece[0]=="q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(inversedict[a])
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(inversedict[a])
                    break
            topright[0], topright[1] = int(topright[0]),int(topright[1])
        while 0<topleft[0]<=7 and 0<topleft[1]<=7:
            topleft[0]-=1
            topleft[1]-=1
            topleft[0], topleft[1] = str(topleft[0]), str(topleft[1])
            a = "".join(topleft)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(inversedict[a])
            if piece[0] == "q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(inversedict[a])
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(inversedict[a])
                    break
            topleft[0], topleft[1] = int(topleft[0]), int(topleft[1])
        while 0 <= rightbottom[0] < 7 and 0 <= rightbottom[1] < 7:
            rightbottom[0] += 1
            rightbottom[1] += 1
            rightbottom[0], rightbottom[1] = str(rightbottom[0]), str(rightbottom[1])
            a = "".join(rightbottom)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(inversedict[a])
            if piece[0] == "Q":
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(inversedict[a])
                    break
            else:
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(inversedict[a])
                    break
            rightbottom[0], rightbottom[1] = int(rightbottom[0]), int(rightbottom[1])
        while 0 <= leftbottom[0] < 7 and 0 < leftbottom[1] <= 7:
            leftbottom[0] += 1
            leftbottom[1] -= 1
            leftbottom[0], leftbottom[1] = str(leftbottom[0]), str(leftbottom[1])
            a = "".join(leftbottom)
            x = inversedict[a]
            y = row_column_of_thesquares[x]
            if theboard[y[0]][y[1]] == "  ":
                list1.append(inversedict[a])
            if piece[0] == "q":
                if theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    list1.append(inversedict[a])
                    break
            else:
                if theboard[y[0]][y[1]] in blackpawns or theboard[y[0]][y[1]] in blackimportantpieces:
                    break
                elif theboard[y[0]][y[1]] in whitepawns or theboard[y[0]][y[1]] in whiteimportantpieces:
                    list1.append(inversedict[a])
                    break
            leftbottom[0], leftbottom[1] = int(leftbottom[0]), int(leftbottom[1])
        return list1
    elif piece[0] == "K" or piece[0] == "k":
        a=str(position[0]+1)+str(position[1])
        b=str(position[0]-1)+str(position[1])
        c=str(position[0])+str(position[1]+1)
        d = str(position[0]) + str(position[1]-1)
        e = str(position[0]+1) + str(position[1]+1)
        f = str(position[0] - 1) + str(position[1]+1)
        g = str(position[0] - 1) + str(position[1]-1)
        h = str(position[0] +1) + str(position[1]-1)
        list1=[i for i in [a,b,c,d,e,f,g,h]]
        list2 = [inversedict[i] for i in list1 if i[0] != "-" and i[0] != "8" and i[1] != "-" and i[1] != "8"]
        list3=list2.copy()
        if piece[0]=="K":
            for i in list3:
                a=row_column_of_thesquares[i]
                if theboard[a[0]][a[1]] in blackpawns or theboard[a[0]][a[1]] in blackimportantpieces:
                    list2.remove(i)
        else:
            for i in list3:
                a=row_column_of_thesquares[i]
                if theboard[a[0]][a[1]] in whitepawns or theboard[a[0]][a[1]] in whiteimportantpieces:
                    list2.remove(i)
        return list2
    else:
        a = str(position[0] + 1) + str(position[1] + 1)
        b = str(position[0] - 1) + str(position[1] + 1)
        c = str(position[0] - 1) + str(position[1] - 1)
        d = str(position[0] + 1) + str(position[1] - 1)
        e= str(position[0]+2)+str(position[1]-1)
        f= str(position[0]+2)+str(position[1]+1)
        g= str(position[0]+1)+str(position[1]+2)
        h= str(position[0]+1)+str(position[1]-2)
        j= str(position[0]-1)+str(position[1]+2)
        k = str(position[0]-1) + str(position[1] - 2)
        l= str(position[0]-2)+str(position[1]-1)
        m= str(position[0]-2)+str(position[1]+1)
        list1 = [i for i in [a, b, c, d]]
        list2 = [inversedict[i] for i in list1 if i[0] != "-" and i[0] != "8" and i[1] != "-" and i[1] != "8"]
        list3=list2.copy()
        for i in list3:
            x=row_column_of_thesquares[i]
            if theboard[x[0]][x[1]] in blackpawns or theboard[x[0]][x[1]] in blackimportantpieces or theboard[x[0]][x[1]] in whitepawns or theboard[x[0]][x[1]] in whiteimportantpieces:
                list2.remove(i)
        list4=[i for i in [e,f,g,h,j,k,l,m]]
        list5=[inversedict[i] for i in list4 if i[0] != "-" and i[0] != "8" and i[1] != "-" and i[1] != "8" and i[0]!="9" and i[1]!="9"]
        list6=list5.copy()
        if piece[0]=="n":
            for i in list6:
                x = row_column_of_thesquares[i]
                if theboard[x[0]][x[1]] in whitepawns or theboard[x[0]][x[1]] in whiteimportantpieces:
                    list5.remove(i)
        else:
            for i in list6:
                x = row_column_of_thesquares[i]
                if theboard[x[0]][x[1]] in blackpawns or theboard[x[0]][x[1]] in blackimportantpieces:
                    list5.remove(i)
        return list2+list5
def move(piece,location):
    if location in movementlist(piece):
        x=positionofpieces.get(piece)
        y=row_column_of_thesquares[x]
        theboard[y[0]][y[1]]="  "
        a=row_column_of_thesquares[location]
        if theboard[a[0]][a[1]]=="  ":
            theboard[a[0]][a[1]]=piece
        else:
            positionofpieces.pop(theboard[a[0]][a[1]])
            theboard[a[0]][a[1]]=piece
        positionofpieces[piece]=location
        print("OK")
    else:
        print("FAILED")
def displaymoves(piece):
    a=movementlist(piece)
    if positionofpieces["ki"] in movementlist(piece):
        a.remove(positionofpieces["ki"])
    elif positionofpieces["KI"] in movementlist(piece):
        a.remove(positionofpieces["KI"])
    if len(a)>0:
        a.sort()
        print(*a)
    else:
        print("FAILED")
initialize()
file=open(sys.argv[1],"r")
correctlist=[line.split() for line in file.readlines()]
for line in correctlist:
    if line[0]=="move":
        a=" ".join(line)
        print("> "+a)
        if positionofpieces["ki"] in movementlist(line[1]) or positionofpieces["KI"] in movementlist(line[1]):
            print("FAILED")
        else:
            move(line[1],line[2])
    elif line[0]=="showmoves":
        a = " ".join(line)
        print("> " + a)
        displaymoves(line[1])
    elif line[0]=="print":
        print("> print")
        displayboard(theboard)
    elif line[0]=="exit":
        print("> exit")
        exit()
    elif line[0]=="initialize":
        print("> initialize")
        print("OK")
        initialize()
        displayboard(theboard)







