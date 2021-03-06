package com.company.app;

public class App
{
    /*
                                                       (9)r34
                                                (8)r21  +  (7)r13
                                         (7)r13  +  (6)r8
                                  (6)r8  +  (5)r5
                            (5)r5  +  (4)r3
                     (4)r3  +  (3)r2
               (3)r2  +   (2)r1
         (2)r1  +  (1)r1
      output:
      34
      because
      fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
      index:     0  1  2  3  4  5  6  7   8   9
     */
    static int recursion(int numberOfTerms){
        if(numberOfTerms == 1 || numberOfTerms == 2){
            return 1;
        }
        return recursion(numberOfTerms - 1) + recursion(numberOfTerms - 2);
    }

    /*
      output:
      fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
      index:     0  1  2  3  4  5  6  7   8   9
     */
    static void iteration(int numberOfTerms){
        int first = 0;
        int second = 1;
        int next;
        System.out.print("fibonacci: " + first + ", " + second);
        int thirdIndex = 2;
        for(int i = thirdIndex; i <= numberOfTerms; i++){
            next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
    }

    static int number = 9;
    public static void main( String[] args )
    {
        System.out.println("#Recursion " + number + ": ");
        System.out.println(recursion(number));

        System.out.println("#Iteration " + number +  ": ");
        iteration(number);
    }
}
/*
output:
#Recursion 9:
34
#Iteration 9:
fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
 */