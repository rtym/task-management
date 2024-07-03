package net.tymoshuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class TaskProcessorTest {
    
    
    @Test
    void scenario1() {
        final Task task1 = new Task("T1", 1, 1);
        final Task task2 = new Task("T2", 3, 2);
        final Task task3 = new Task("T3", 4, 3);
        
        final List<Task> tasks = List.of(task1, task2, task3);
        final TaskProcessor processor = new TaskProcessor();
        
        final Set<Task> expectation = Set.of(task2, task3);

        Assertions.assertEquals(expectation, processor.process(tasks, 7));
    }
}
