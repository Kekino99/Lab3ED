package stack;

import static java.lang.System.exit;

public class SharedStack<E> implements Stack<E> {
    private final Node<E> topOfStack;

    private static class Node<E> {
        private final E elem;
        private final Node<E> next;


        private Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    public SharedStack() {
        topOfStack = new Node<>(null, null);
    }

    private SharedStack(E elem, Node<E> next) {
        topOfStack = new Node<>(elem, next);
    }

    private SharedStack(Node<E> topOfStack) {
        this.topOfStack = topOfStack;
    }
    //Otros Constructores
    //¿?


    // methods of Stack<E>;
    @Override
    public SharedStack<E> push(E elem) {
        return new SharedStack<>(elem, topOfStack);
    }

    @Override
    public SharedStack<E> pop() throws StackError {
        if (isEmpty()) {
            throw new StackError("Empty Stack");
        } else {
            return new SharedStack<>(topOfStack.next);
        }
    }

    @Override
    public E top() throws StackError {
        if (isEmpty()) {
            throw new StackError("Empty Stack");
        } else {
            return topOfStack.elem;
        }
    }

    @Override
    public boolean isEmpty() {
        return (topOfStack.next) == null;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference to any implementation of Stack<E> where E
     *            is the type of the elements of the SharedStack passed.
     * @return {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @SuppressWarnings("unchecked") //Is required in javaDoc
    @Override
    public boolean equals(Object obj) {
        try {
            Stack<E> stack = (Stack<E>) obj;
            SharedStack<E> current = this;
            while (!(stack.isEmpty() || current.isEmpty())) {
                E elem1 = stack.top();
                E elem2 = current.top();
                if (elem1 == null || !(elem1.equals(elem2))) {
                    return false;
                }
                stack = stack.pop();
                current = current.pop();
            }
            return stack.isEmpty() && current.isEmpty();
        } catch (StackError err) {
            return false;
        }
        /* Notes al corrector: S'ha actualitzat el javaDoc de equals el qual
         * ara demana que obj sigui Stack<E> i tot el que això comporta.
         *
         * Tot i que he mirat que no es generi cap error en el bucle
         * mirant que sigui empty la IDE suda i troba un error a això que s'hauria de
         * solucionar amb un try-catch. He posat un try catch i he retornat a fals
         * (comentar-ho amb el professor).
         * */
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        try {
            SharedStack<E> current = this;
            while (!current.isEmpty()) {
                E elem2 = current.top();
                result.append(elem2).append(", ");
                current = current.pop();
            }
        } catch (StackError err) {
            exit(3);
        }
        return result.toString();
    }
}
