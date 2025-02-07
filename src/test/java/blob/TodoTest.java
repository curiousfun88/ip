package blob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("Buy broccoli");
        assertEquals("[T][ ] Buy broccoli", todo.toString(), "toString() should match expected format.");

        todo.markAsDone();
        assertEquals("[T][X] Buy broccoli", todo.toString(), "toString() should reflect marked status.");
    }

    @Test
    public void testSerialise() {
        Todo todo = new Todo("Buy broccoli");
        assertEquals("T | 0 | Buy broccoli", todo.serialize(), "serialise() should correctly format an unmarked task.");

        todo.markAsDone();
        assertEquals("T | 1 | Buy broccoli", todo.serialize(), "serialise() should correctly format a marked task.");
    }
}

