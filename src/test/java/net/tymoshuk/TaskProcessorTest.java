package net.tymoshuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
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

    @Test
    void scenario2() {
        final Task task1 = new Task("T1", 1, 1);
        final Task task2 = new Task("T2", 2, 3);
        final Task task3 = new Task("T3", 3, 4);

        final List<Task> tasks = List.of(task1, task2, task3);
        final TaskProcessor processor = new TaskProcessor();

        final Set<Task> expectation = Set.of(task2, task3);

        Assertions.assertEquals(expectation, processor.process(tasks, 5));
    }

    @Test
    void scenario3() {
        final Task task1 = new Task("T1", 6, 14);
        final Task task2 = new Task("T2", 1, 3);
        final Task task3 = new Task("T3", 2, 5);
        final Task task4 = new Task("T4", 3, 8);

        final List<Task> tasks = List.of(task1, task2, task3, task4);
        final TaskProcessor processor = new TaskProcessor();

        final Set<Task> expectation = Set.of(task2, task3, task4);

        Assertions.assertEquals(expectation, processor.process(tasks, 6));
    }

    @Test
    void scenario4() {
        final Task task1 = new Task("T1", 3, 18);
        final Task task2 = new Task("T2", 1, 3);
        final Task task3 = new Task("T3", 1, 5);
        final Task task4 = new Task("T4", 1, 8);

        final List<Task> tasks = List.of(task1, task2, task3, task4);
        final TaskProcessor processor = new TaskProcessor();

        final Set<Task> expectation = Set.of(task1);

        Assertions.assertEquals(expectation, processor.process(tasks, 3));
    }
    
    //This test validates the call with empty data
    @Test
    void testEmptyTaskCollection() {
        final TaskProcessor processor = new TaskProcessor();
        
        Assertions.assertEquals(Collections.emptySet(), processor.process(Collections.emptyList(), 3));
    }

    //This test validates the call with tasks which takes longer then in provided time range
    @Test
    void callWithTasksOutOfRequiredRange() {
        final Task task1 = new Task("T1", 10, 18);
        final Task task2 = new Task("T2", 5, 5);

        final List<Task> tasks = List.of(task1, task2);
        final TaskProcessor processor = new TaskProcessor();

        Assertions.assertEquals(Collections.emptySet(), processor.process(tasks, 3));
    }

    //This test validates the call with only one task within required time range
    @Test
    void callWithOnlyTaskInRequiredTimeRange() {
        final Task task1 = new Task("T1", 10, 18);
        final Task task2 = new Task("T2", 2, 5);

        final List<Task> tasks = List.of(task1, task2);
        final TaskProcessor processor = new TaskProcessor();

        final Set<Task> expectation = Set.of(task2);

        Assertions.assertEquals(expectation, processor.process(tasks, 3));
    }

    @Test
    public void testSingleTaskExceedsTime() {
        final List<Task> tasks = List.of(new Task("T1", 15, 10));
        final TaskProcessor processor = new TaskProcessor();
        final Set<Task> result = processor.process(tasks, 10);
        
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testAllTasksFitWithinTime() {
        final Task task1 = new Task("T1", 1, 1);
        final Task task2 = new Task("T2", 2, 2);
        final Task task3 = new Task("T3", 3, 3);
        
        final List<Task> tasks = List.of(task1, task2, task3);
        final TaskProcessor processor = new TaskProcessor();
        final Set<Task> expectations = Set.of(task1, task2, task3); 

        Assertions.assertEquals(expectations, processor.process(tasks, 6));
    }

    @Test
    public void testTasksWithSameTimeDifferentValue() {
        final Task task1 = new Task("T1", 2, 1);
        final Task task2 = new Task("T2", 2, 2);
        final Task task3 = new Task("T3", 2, 3);

        final List<Task> tasks = List.of(task1, task2, task3);
        final TaskProcessor processor = new TaskProcessor();
        final Set<Task> expectations = Set.of(task2, task3);

        Assertions.assertEquals(expectations, processor.process(tasks, 4));
    }

    // This is a tricky one as multiple combinations are possible
    @Test
    public void testNonObviousOptimalChoice() {
        final Task task1 = new Task("T1", 2, 3);
        final Task task2 = new Task("T2", 3, 4);
        final Task task3 = new Task("T3", 4, 5);
        final Task task4 = new Task("T4", 5, 8);
        final Task task5 = new Task("T5", 1, 1);

        final List<Task> tasks = List.of(task1, task2, task3, task4, task5);
        final TaskProcessor processor = new TaskProcessor();
        final Set<Task> expectations = Set.of(task1, task4, task5);

        Assertions.assertEquals(expectations, processor.process(tasks, 8));
    }
}
