package stack;

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

    public SharedStack () {
        topOfStack=new Node<>(null, null);
    }

    private SharedStack(E elem, Node<E> next) {
        topOfStack = new Node<>(elem, next);
    }

    private SharedStack(Node<E> topOfStack){
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
        if(isEmpty()) {
            throw new StackError("Empty Stack");
        } else {
            return new SharedStack<>(topOfStack.next);
        }
    }

    @Override
    public E top() throws StackError {
        if(isEmpty()) {
            throw new StackError("Empty Stack");
        } else {
            return topOfStack.elem;
        }
    }

    @Override
    public boolean isEmpty() {
        return (topOfStack.next) == null;
    }
}
