#try1 = Execution time: 0.47590155 seconds
import timeit
input = open('inp.txt').read().split('\n')

def code():
    out = 0
    for i in input:
        sol = int(i.split(":")[0])
        nums = []
        max = 1
        min = 0

        for j in i.split(":")[1].split():
            nums.append(int(j))
            max *= int(j)
            min += int(j)

        if max < sol or min > sol:
            continue

        poss = [nums[0]]
        nums.remove(nums[0])
        for j in nums:
            temp = []
            for k in poss:
                temp.append(k + j)
                temp.append(k * j)
                temp.append(int(str(k) + str(j)))
            poss = temp.copy()

        if sol in poss:
            out += sol

    print(out)

execution_time = 0
for i in range(10):
    execution_time += timeit.timeit(code, number=1)
print(f"Execution time: {execution_time/10} seconds")