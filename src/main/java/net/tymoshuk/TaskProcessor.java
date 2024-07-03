package net.tymoshuk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class TaskProcessor {
    
    private final Stack<Task> taskStack = new Stack<>();

    private Set<Task> optimalTaskSet = new HashSet<>();
    
    private int currentValue = 0;
    
    private int maxValue = 0;
    
    public Set<Task> process(final List<Task> tasks, int time) {
        this.optimalTaskSet.clear();
        this.maxValue = 0;
        
        this.taskStack.clear();
        this.currentValue = 0;
        
        processTasks(tasks, time);
        
        return this.optimalTaskSet;
    }
    
    private synchronized void processTasks(final List<Task> tasks, int time) {
       for (int counter = 0; counter < tasks.size(); counter++) {
           final Task task = tasks.get(counter);
           
           if (task.time() <= time) {
               this.taskStack.push(task);
               this.currentValue += task.value();
               
               if (this.currentValue > this.maxValue) {
                   this.maxValue = this.currentValue;
                   this.optimalTaskSet = new HashSet<>(this.taskStack);
               }
               
               this.processTasks(tasks.subList(counter + 1, tasks.size()), time - task.time());
               
               this.taskStack.pop();
               this.currentValue -= task.value();
           }
       }
    }
    
}
