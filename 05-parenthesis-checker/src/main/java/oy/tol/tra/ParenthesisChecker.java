package oy.tol.tra;

/**
 * Uses the StackInterface implementation to check that parentheses in text files
 * match. 
 */
public class ParenthesisChecker {

    private ParenthesisChecker() {
    }

    /**
     * Checks if the given string has matching opening and closing parentheses.
     *
     * @param stack      The stack object used in checking the parentheses from the string.
     * @param fromString A string containing parentheses to check if it is valid.
     * @return Returns the number of parentheses found from the input in total (both opening and closing).
     * @throws ParenthesesException   if the parentheses did not match as intended.
     * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
     */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException, StackAllocationException {
        int totalParentheses = 0;

        // Iterate through each character in the string
        for (char c : fromString.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') { // If character is an opening parenthesis
                stack.push(c); // Push it into the stack
                totalParentheses++;
            } else if (c == ')' || c == ']' || c == '}') { // If character is a closing parenthesis
                totalParentheses++;
                if (stack.isEmpty()) { // If the stack is empty, there are too many closing parentheses
                    throw new ParenthesesException("Too many closing parentheses", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                }

                char popped = stack.pop(); // Pop the latest opening parenthesis from the stack
                if (popped == '(' && c != ')' || popped == '[' && c != ']' || popped == '{' && c != '}') {
                    // If the popped opening parenthesis does not match the closing parenthesis
                    throw new ParenthesesException("Parentheses in wrong order", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                }
            }
        }

        if (!stack.isEmpty()) { // If the stack is not empty after all characters have been handled
            throw new ParenthesesException("Too few closing parentheses", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }

        return totalParentheses;
    }
}
