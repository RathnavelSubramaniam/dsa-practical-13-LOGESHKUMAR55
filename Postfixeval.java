import java.io.*;
import java.util.*;

class stack {
    int size;
    int item[];
    int top;

    public stack() {
        size = 100;
        item = new int[size];
        top = -1;
    }

    public void push(int ele) {
        if (top == (size - 1)) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            item[top] = ele;
            // System.out.println("Inserted Element:"+ele);
        }
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Invalid Postfix string; Operators are given more than operands");
            return (-1);

        } else {
            int x = item[top];
            top--;
            // System.out.println("Popped Element:"+x);
            return (x);
        }
    }

    public int peek() {
        if (top == -1) {
            System.out.println("No Elements");
            return (-1);
        } else
            return (item[top]);
    }

    public void display() {
        System.out.println();
        if (top == -1) {
            System.out.println("No Elements");
        } else {
            System.out.println("Stack is");
            for (int i = 0; i <= top; i++)
                System.out.println(item[i]);

        }
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

class evalpostfix {
    stack st = new stack();
    String postfix;

    public evalpostfix(String str) {
        postfix = str;
    }

    public boolean isOperand(char ch) {
        return Character.isDigit(ch);
    }

    public int eval() {
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (isOperand(ch)) {
                st.push(Character.getNumericValue(ch));
            } else {
                try { // Added try-catch for potential exceptions
                    int op2 = st.pop();
                    int op1 = st.pop();
                    int res = 0;
                    switch (ch) {
                        case '+':
                            res = op1 + op2;
                            break;
                        case '-':
                            res = op1 - op2;
                            break;
                        case '*':
                            res = op1 * op2;
                            break;
                        case '/':
                            res = op1 / op2;
                            break;
                        default:
                            System.out.println("Invalid operator");
                            return -1; // Indicate an error
                    }
                    st.push(res);
                } catch (Exception e) {
                   System.out.println("Invalid Postfix string; Operands are given more than operators or Divide by zero");
                    return -1; // Indicate an error
                }
            }
        }
        if(!st.isEmpty() && st.peek()!=-1) //Check if the stack has a valid result and not an error code
        {
            return st.pop();
        }
        else
        {
            return -1;
        }
    }
}

public class Postfixeval {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter postfix string");
        String str = sc.nextLine();
        evalpostfix epf = new evalpostfix(str);
        int res = epf.eval();
        if (res != -1)
            System.out.println("Result:" + res);
        else
            System.out.println("Invalid Postfix String");
    }
}
