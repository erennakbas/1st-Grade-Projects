def takefeedingmap(feedingmap): #Convert the board string to a usable list.
    lengthofrows, countofrows = int(feedingmap.index("]")/5), (feedingmap.count("[") - 1)
    fixedlist=[i for i in feedingmap if i=="W" or i=="C" or i=="M" or i=="A" or i=="P" or i== "X" or i=="*"]
    correctlist=[fixedlist[(a*lengthofrows):((a+1)*lengthofrows)] for a in range(countofrows)]
    return correctlist
def takedirections(directions): #Convert the direction string to a usable list.
    correctlist=[i for i in directions if i=="U" or i=="D" or i=="L" or i=="R"]
    return correctlist
def displayboard(b): #Show the board.
    for i in range(len(b)):
        print(*b[i])
def findrabbit(boardlist): #Extract the rabbit's location as a list [Line, Position on that line]
    for line in boardlist:
        for point in line:
            if point == "*":
                return [boardlist.index(line), line.index(point)]
def move(board_list,directionlist): #Move the rabbit by the given board, given direction instructions and using rabbit's location.
    global score
    rabbitslocation=findrabbit(board_list)
    values_of_foods = {"M": -5, "A": 5, "X": 0, "C": 10, "P": 0}
    for i in directionlist:
        if i=="U":
            if rabbitslocation[0] != 0: #endpoint
                nextstep=board_list[rabbitslocation[0]-1][rabbitslocation[1]] #The next destination that rabbit tries to go.
                if nextstep!="W": #if it's not at far up and there is no wall in the next destination.
                    if nextstep=="A" or nextstep=="C" or nextstep=="X" or nextstep=="P" or nextstep=="M":
                        board_list[rabbitslocation[0]][rabbitslocation[1]]="X"
                        rabbitslocation[0]-=1
                        score+=values_of_foods[nextstep]
                        if nextstep=="P":
                            break
        elif i=="D":
            if rabbitslocation[0] != len(board_list) - 1: #endpoint
                nextstep= board_list[rabbitslocation[0] + 1][rabbitslocation[1]] #The next destination that rabbit tries to go.
                if nextstep!= "W": #if it's not at far down and there is no wall in the destination.
                    if nextstep=="A" or nextstep=="C" or nextstep=="X" or nextstep=="P" or nextstep=="M":
                        board_list[rabbitslocation[0]][rabbitslocation[1]] = "X"
                        rabbitslocation[0] += 1
                        score += values_of_foods[nextstep]
                        if nextstep=="P":
                            break
        elif i=="L":
            if rabbitslocation[1] != 0: #endpoint
                nextstep=board_list[rabbitslocation[0]][rabbitslocation[1]-1] #The next destination that rabbit tries to go.
                if nextstep!="W": #if it's not at far left and there is no wall in the next destination.
                    if nextstep=="A" or nextstep=="C" or nextstep=="X" or nextstep=="P" or nextstep=="M":
                        board_list[rabbitslocation[0]][rabbitslocation[1]]="X"
                        rabbitslocation[1]-=1
                        score+=values_of_foods[nextstep]
                        if nextstep=="P":
                            break
        elif i == "R":
            if rabbitslocation[1] != len(board_list[0]) - 1: #endpoint
                nextstep = board_list[rabbitslocation[0]][rabbitslocation[1] +1] #The next destination that rabbit tries to go.
                if nextstep != "W": #if it's not at far right and there is no wall in the next destination.
                    if nextstep=="A" or nextstep=="C" or nextstep=="X" or nextstep=="P" or nextstep=="M":
                        board_list[rabbitslocation[0]][rabbitslocation[1]] = "X"
                        rabbitslocation[1] += 1
                        score += values_of_foods[nextstep]
                        if nextstep=="P":
                            break
    board_list[rabbitslocation[0]][rabbitslocation[1]]="*"
score=0
input_1=input("Please enter feeding map as a list:\n")
input_2=input("Please enter direction of movements as a list:\n")
feedingboard=takefeedingmap(input_1) #Convert
the_directions=takedirections(input_2) #Convert
print("Your board is:")
displayboard(feedingboard)
move(feedingboard,the_directions) #Move the rabbit.
print("Your output should be like this:")
displayboard(feedingboard) #Show the last situation.
print(f"Your score: {score}")