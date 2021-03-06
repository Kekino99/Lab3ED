package stack;

import MutableInt.MutableInt;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;

public class SharedStackTest {
    private SharedStack<Integer> stack1;
    private SharedStack<Integer> stack2;


    //Preguntar sobre exit (No funciona com s'espera (?))
    @Before
    public void initStack() {
        stack1 = new SharedStack<>();
        stack1 = stack1.push(3);
        stack1 = stack1.push(3);
        stack2 = stack1.push(4);
        stack1 = stack1.push(4);
        //Stack1 and stack2 are equal.

    }

    @org.junit.Test
    public void push() {
        SharedStack<Integer> stack, result;
        stack = new SharedStack<>();
        result = stack;
        stack = stack.push(3);
        result = result.push(3);
        assertEquals(result, stack);
        assertEquals(stack1, stack2);
        assertEquals("3, ", stack.toString());
    }

    @org.junit.Test
    public void pop() {
        try {
            while (!stack1.isEmpty() && !stack2.isEmpty()) {
                stack1 = stack1.pop();
                stack2 = stack2.pop();
                assertEquals(stack1, stack2);
            }

            SharedStack<Integer> stack = new SharedStack<>(), altStack;
            stack = stack.push(3);
            altStack = stack.push(4);
            assertEquals(stack, altStack.pop());
        } catch (StackError err) {
            Assert.fail();
        }


    }

    @org.junit.Test
    public void top() {
        Integer[] array = new Integer[10];
        SharedStack<Integer> stack = new SharedStack<>();

        for (int i = 0; i < 10; i++) {
            array[i] = i;
            stack = stack.push(array[i]);
        }
        try {
            for (int i = 9; i >= 0; i--) {
                assertEquals(array[i], stack.top());
                stack = stack.pop();
            }
        } catch (StackError err) {
            Assert.fail();
        }
    }

    @org.junit.Test
    public void isEmpty() {
        SharedStack<Integer> stack = new SharedStack<>();
        assertTrue(stack.isEmpty());
        stack = stack.push(3);
        assertFalse(stack.isEmpty());
        try {
            stack = stack.pop();
            assertTrue(stack.isEmpty());
        } catch (StackError err) {
            Assert.fail();
        }

    }

    @org.junit.Test(expected = stack.StackError.class)
    public void stackErrorUnexpectedPopTest() throws StackError {
        SharedStack<String> stack = new SharedStack<>();
        stack.pop();
    }

    @org.junit.Test(expected = StackError.class)
    public void stackErrorTopTest() throws StackError {
        SharedStack<String> stack = new SharedStack<>();
        stack.top();
    }

    @org.junit.Test
    public void stackErrorPopTest() {
        try {
            stack1.pop();
        } catch (StackError err) {
            Assert.fail(); //Shouldnt catch anything
        }
    }



    @org.junit.Test
    public void stackErrorUnexpectedTopTest() {
        try {
            stack1.top();
        } catch (StackError err) {
            Assert.fail(); //Shouldnt catch anything
        }
    }

    @org.junit.Test
    public void referenceTest() {
        SharedStack<MutableInt> stack3 = new SharedStack<>();
        SharedStack<MutableInt> stack4;
        MutableInt number = new MutableInt(5);
        stack3 = stack3.push(new MutableInt(3));
        stack3 = stack3.push(new MutableInt(4));
        stack3 = stack3.push(new MutableInt(7));
        stack4 = stack3.push(number);
        stack3 = stack3.push(number);
        assertEquals(stack3, stack4);
        number.setI(0);
        assertEquals(stack3, stack4);
        try {
            assertEquals(number, stack3.top());
        } catch (StackError err) {
            Assert.fail();
        }
    }
}