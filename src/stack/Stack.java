package stack;

public interface Stack<E> {

    /**
     * Puts an element to a new stack
     *
     * @param elem new element of the new stack
     * @return new stack
     */
    Stack<E> push(E elem);

    /**
     * Deletes the last element pushed in the stack
     * @return a stack without the last element
     * @throws StackError if the stack was empty. Please, use
     *      isEmpty() method to manage the error
     */
    Stack<E> pop() throws StackError;

    /**
     *
     * @return the last element pushed in the stack
     * @throws StackError if the stack was empty. Please, use
     *      *      isEmpty() method to manage the error
     */
    E top() throws StackError;

    /**
     *
     * @return checks if the stack has no elements in there
     */
    boolean isEmpty();
}
