package vplagov;

import java.util.Stack;
import java.util.ArrayList;

public class SY
{
    private String infix;

    public SY(String expr)
    {
        infix = expr;
    }

    private int precedence(String op)
    {
        switch(op)
        {
            case "^":
            case "sqrt":
            case "!":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    public String toPostfix()
    {
        // A stack of strings for the operators
        Stack<String> stack = new Stack<String>();

        // A list of strings for output
        ArrayList<String> postfix = new ArrayList<String>();

        // An array of Strings for tokens
        String[] tokens;

        // Split infix expression into tokens
        tokens = infix.split(" ");

        // Loop through tokens
        for (String token : tokens)
        {
            if (precedence(token) > 0)  // It's an op!
            {

                if (stack.empty()) stack.push(token);
                else {


                    while (! stack.empty() && precedence(stack.peek()) >= precedence(token)) {postfix.add(stack.pop());}

                    stack.push(token);}

            }
            else if (token.equals("("))
            {
                stack.push(token);


            }
            else if (token.equals(")"))
            {
                while ( !stack.empty() && !(stack.peek().equals("("))) postfix.add(stack.pop());

                stack.pop();

            }
            else        // It's (probably) a number!
            {
                postfix.add(token);
            }
        }

        // empty out the stack
        while (! stack.empty())
        {
            postfix.add(stack.pop());
        }

        // Return a string

        return String.join(" ", postfix);
    }
}