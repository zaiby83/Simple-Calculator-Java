package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimpleCalculator {
    
    private static boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private static int getPrecedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
    
    private static long power(long a, long b) {
        Stack<Boolean> stack = new Stack<>();
        while (b != 0) {
            stack.push((b & 1) == 1);
            b >>= 1;
        }
        long ans = 1;
        while (!stack.empty()) {
            ans *= ans;
            if (stack.pop())
                ans *= a;
        }
        return ans;
    }
    
    public static List<String> splitString(String str) {
        List<String> stringList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); ++i) {
            char chr = str.charAt(i);
            switch (chr) {
                case '+', '-', '*', '/', '(', ')', '^' -> {
                    if (!"".contentEquals(sb))
                        stringList.add(sb.toString());
                    stringList.add(String.valueOf(chr));
                    sb.setLength(0);
                }
                default -> sb.append(chr);
            }
        }
        stringList.add(sb.toString());
        return stringList;
    }
    
    private static List<String> infixToPostfix(List<String> list) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : list) {
            if (isNumber(token))
                result.add(token);
            else if (token.equals("("))
                stack.push("(");
            else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("("))
                    result.add(stack.pop());
                stack.pop();
            }
            else {
                while (!stack.empty() && getPrecedence(token.charAt(0)) <= getPrecedence(stack.peek().charAt(0)))
                    result.add(stack.pop());
                stack.push(token);
            }
        }
        while (!stack.empty()) {
            if (stack.peek().equals("("))
                throw new ArithmeticException("Illegal Expression");
            result.add(stack.pop());
        }
        return result;
    }
    
    private static long evaluatePostfix(List<String> list) {
        Stack<Long> stack = new Stack<>();

        for (String token : list) {
            if (isNumber(token))
                stack.push(Long.parseLong(token));
            else {
                if (stack.size() < 2)
                    throw new ArithmeticException("Illegal Expression");
                long v1 = stack.pop();
                long v2 = stack.pop();
                char operator = token.charAt(0);

                switch (operator) {
                    case '+' -> stack.push(v2 + v1);
                    case '-' -> stack.push(v2 - v1);
                    case '*' -> stack.push(v2 * v1);
                    case '/' -> stack.push(v2 / v1);
                    case '^' -> stack.push(power(v2, v1));
                }
            }
        }

        if (stack.size() != 1)
            throw new ArithmeticException("Illegal Expression");
        return stack.pop();
    }
    
    
    public static long evaluateExpression(String str) {
        return evaluatePostfix(infixToPostfix(splitString(str)));
    }
}
