package stack;

public class StackError extends Exception {

    /**
     * Used to display errors in Stack
     *
     * @param msg specify the nature of stack
     *            error
     */
    public StackError(String msg) {
        super(msg);
    }
}
