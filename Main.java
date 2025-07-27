// 1 - Adding a Task 
// 2 - Updating a Task
// 3 - Deleting a Task 
// 4 - Listing all Tasks
// 5 - Exit

import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isCompleted;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters
    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markPending() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return description + " - " + (isCompleted ? "completed" : "pending");
    }
}

class TaskManager {
    ArrayList<Task> tasks = new ArrayList<>();

    // Add a new task
    public void addTask(String description) {
        tasks.add(new Task(description));
    }
    
    // Updating task to completed
    public void markTaskCompleted(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                task.markCompleted();
                return;
            }
        }
        System.out.println("Task not found.");
    }

    // Updating the task to pending
    public void markTaskPending(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                task.markPending();
                return;
            }
        }
        System.out.println("Task not found.");
    }

    // Delete a task by description
    public void deleteTask(String description) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().equalsIgnoreCase(description)) {
                tasks.remove(i);
                return;
            }
        }
        System.out.println("Task not found.");
    }

    // List all tasks
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        System.out.println("Welcome to the Task Manager!");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Add Task");
            System.out.println("2 - Update Task Status");
            System.out.println("3 - Delete Task");
            System.out.println("4 - List All Tasks");
            System.out.println("5 - Exit");
            System.out.print("Enter your choice: ");

            byte choice;
            try {
                choice = scanner.nextByte();
                scanner.nextLine(); // flush
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // flush
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    manager.addTask(desc);
                    System.out.println("Task added successfully.");
                    break;

                case 2:
                    System.out.print("Enter task description to update: ");
                    String updateDesc = scanner.nextLine();
                    System.out.print("Enter new status (pending/completed): ");
                    String newStatus = scanner.nextLine().toLowerCase();

                    if (newStatus.equals("pending")) {
                        manager.markTaskPending(updateDesc);
                        System.out.println("Task marked as pending.");
                    } else if (newStatus.equals("completed")) {
                        manager.markTaskCompleted(updateDesc);
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Invalid status. Please enter either 'pending' or 'completed'.");
                    }
                    break;

                case 3:
                    System.out.print("Enter task description to delete: ");
                    String delDesc = scanner.nextLine();
                    manager.deleteTask(delDesc);
                    System.out.println("Task deleted (if it existed).");
                    break;

                case 4:
                    System.out.println("Your Tasks:");
                    manager.listTasks();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    scanner.close(); 
                    return;

                default:
                    System.out.println("Please enter a valid option (1-5).");
            }
        }
    }
}
