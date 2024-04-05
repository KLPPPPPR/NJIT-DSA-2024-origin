package oy.tol.tra;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class demonstrates using a queue to manage daily tasks.
 * Tasks are read from a file and printed on the screen periodically using a Timer.
 * The Timer is controlled by a TimerTask.
 */
public class DailyTasks {

   private final QueueInterface<String> tasksQueue;
   private final Timer timer;

   /**
    * Constructor for DailyTasks.
    * Initializes the tasks queue and timer.
    */
   public DailyTasks() {
      tasksQueue = QueueFactory.createStringQueue();
      timer = new Timer();
   }

   /**
    * Adds a task to the tasks queue.
    *
    * @param task The task to be added.
    */
   public void addTask(String task) {
      tasksQueue.enqueue(task);
   }

   /**
    * Starts the timer to print tasks periodically until the queue is empty.
    */
   public void startTimer() {
      TimerTask task = new TimerTask() {
         @Override
         public void run() {
            if (!tasksQueue.isEmpty()) {
               String task = tasksQueue.dequeue();
               System.out.println("Next Task: " + task);
            } else {
               System.out.println("No more tasks for today. Timer stopped.");
               timer.cancel();
            }
         }
      };
      // Schedule the task to run every 5 seconds (for demonstration purposes)
      timer.schedule(task, 0, 5000);
   }

   public static void main(String[] args) {
      // Instantiate DailyTasks
      DailyTasks dailyTasks = new DailyTasks();

      // Add some tasks
      dailyTasks.addTask("Task 1");
      dailyTasks.addTask("Task 2");
      dailyTasks.addTask("Task 3");

      // Start the timer
      dailyTasks.startTimer();
   }
}
