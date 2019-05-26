package vplagov;

import java.math.BigInteger;
import java.util.Stack;

public class Postfix
{
    private String expr;

    public Postfix(String e)
    {
        expr = e;
    }

    public String getPostfix(){
        return expr;
    }

    public double eval()
    {
        String[] tokens = expr.split(" ");
        Stack<Double> s = new Stack<Double>();

        for (String token : tokens)
        {
            /*
            if (token.equals("+"))
            {
                double x = s.pop();
                double y = s.pop();
                s.push(x+y);
            }
            else if (token.equals("*"))
            {
                double x = s.pop();
                double y = s.pop();
                s.push(x*y);
            }
            else
            {
                s.push(Double.parseDouble(token));
            }
            */
            double x, y;
            switch(token)
            {
                case "+":
                    x = s.pop();
                    y = s.pop();
                    s.push(x+y);
                    break;
                case "*":
                    x = s.pop();
                    y = s.pop();
                    s.push(x*y);
                    break;
                case "-":
                    x = s.pop();
                    y = s.pop();
                    s.push(y-x);
                    break;
                case "/":
                    x = s.pop();
                    y = s.pop();
                    s.push(y/x);
                    break;
                case "^":
                    x = s.pop();
                    y = s.pop();
                    s.push(Math.pow(y, x));
                    break;
                case "sqrt":
                    x = s.pop();
                    s.push(Math.sqrt(x));
                    break;
                case "!":
                    x = s.pop();

                    double result = 1;

                    if ( Math.round(x) == x ){

                        for (int factor = 2; factor <= x; factor++) {
                            result *= factor;
                        }}
                    else {result = Math.pow(x/Math.E, x) * Math.sqrt(Math.PI*(2*x+(1/3)));}
                    s.push(result);
                    break;
                default:
                    s.push(Double.parseDouble(token));
                    break;
            }
        }
        return s.pop();
    }
}
