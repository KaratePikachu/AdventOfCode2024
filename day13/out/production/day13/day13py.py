from sympy import *

input = open('test.txt').read().split("\n\n")
sum = 0

for num, i in enumerate(input):
    input[num] = i.split("\n")
    input[num] = [val.split(",") for val in input[num]]
    m = Matrix([
        #[int(input[num][0][0].split("+")[1]), int(input[num][1][0].split("+")[1]), int(input[num][2][0].split("=")[1])],
        #[int(input[num][0][1].split("+")[1]), int(input[num][1][1].split("+")[1]), int(input[num][2][1].split("=")[1])],
        [int(input[num][0][0].split("+")[1]), int(input[num][1][0].split("+")[1]), int(input[num][2][0].split("=")[1]) + 10000000000000],
        [int(input[num][0][1].split("+")[1]), int(input[num][1][1].split("+")[1]), int(input[num][2][1].split("=")[1]) + 10000000000000],
    ])

    

    row_reduced = m.rref()
    
    print(row_reduced[0][0,1])
    if row_reduced[0] != 0:
        print("huh")
    #if(row_reduced[1,1])
    a = row_reduced[0][2]
    b = row_reduced[0][5]

    if abs(a-round(a)) <=0.0001 and abs(b-round(b)) <=0.0001:
        #print(m[2])
        #print(row_reduced[0][2])
        a = row_reduced[0][2]
        b = row_reduced[0][5]
        sum+=3*a+b



print(sum)