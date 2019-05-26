package vplagov;

import java.util.Stack;

public class Balance {

    public boolean isBalanced(String s){
        Stack<Character> stack = new Stack<Character>();
        Character c;
        for(int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c=='(' || c=='{' || c=='[') stack.push(c); else
            if (stack.isEmpty()) return false; else
            if (c == ')' && stack.peek() == '(') stack.pop(); else
            if (c == '}' && stack.peek() == '{') stack.pop(); else
            if (c == ']' && stack.peek() == '[') stack.pop(); else return false;

        }

        return (stack.isEmpty());
    }


}

