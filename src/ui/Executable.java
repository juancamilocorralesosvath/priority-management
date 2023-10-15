package ui;

import java.util.*;

import exceptions.*;
import model.Controller;

public class Executable {

    private final Scanner scanner;
    private final Controller controller;

    public Executable() {
        controller = new Controller();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws EntryException {
        Executable executable = new Executable();
        executable.menu();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create task");
            System.out.println("2. Create reminder");
            System.out.println("3. Undo the last action");
            System.out.println("4. View");
            System.out.println("5. Manage a task");
            System.out.println("6. Manage a reminder");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createTask();
                    break;
                case 2:
                    createReminder();
                    break;
                case 3:
                    System.out.println("Undoing the last action...");
                    controller.undo();
                    break;
                case 4:
                    watchMenu();
                    break;
                case 5:
                    manageTask();
                    break;
                case 6:
                    manageReminder();
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void watchMenu() {
        System.out.println("View submenu:");
        System.out.println("1. Show everything");
        System.out.println("2. Show tasks and reminders");
        System.out.println("3. Show priority tasks");
        System.out.print("Choose an option: ");
        int subOption = scanner.nextInt();
        scanner.nextLine();

        switch (subOption) {
            case 1:
                System.out.println(controller.getActivities() + controller.getPriorityActivities());
                break;
            case 2:
                System.out.println(controller.getActivities());
                break;
            case 3:
                System.out.println(controller.getPriorityActivities());
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void createTask() {
        System.out.print("Task title: ");
        String title = scanner.nextLine();
        System.out.print("Task description: ");
        String description = scanner.nextLine();
        System.out.print("Task date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        System.out.print("Is it a priority task? (Yes/No): ");
        String priorityInput = scanner.nextLine();
        boolean isPriority = priorityInput.equalsIgnoreCase("Yes");

        int priorityLevel = 0;
        if (isPriority) {
            System.out.print("Priority level (1-10): ");
            priorityLevel = scanner.nextInt();
            scanner.nextLine();
        }

        // Create the task and add it to the list
        if (controller.addTask(title, description, date, isPriority, priorityLevel)) {
            System.out.println("Task created successfully.");
        } else {
            System.out.println("Task creation failed.");
        }
    }

    public void createReminder() {
        System.out.print("Reminder title: ");
        String title = scanner.nextLine();
        System.out.print("Reminder description: ");
        String description = scanner.nextLine();
        System.out.print("Reminder date (dd/mm/yyyy): ");
        String dateString = scanner.nextLine();
        if (controller.addReminder(title, description, dateString)) {
            System.out.println("Reminder created successfully.");
        } else {
            System.out.println("Reminder creation failed.");
        }
    }

    public void manageTask() {
        System.out.print("Enter the task ID you want to manage: ");
        int id = scanner.nextInt();
        System.out.println("1. Edit");
        System.out.println("2. Delete");
        System.out.print("Choose an option: ");
        int subOption = scanner.nextInt();
        scanner.nextLine();

        switch (subOption) {
            case 1:
                editTask(id);
                break;
            case 2:
                deleteTask(id);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void manageReminder() {
        System.out.print("Enter the reminder ID you want to manage: ");
        int id = scanner.nextInt();
        System.out.println("1. Edit");
        System.out.println("2. Delete");
        System.out.print("Choose an option: ");
        int subOption = scanner.nextInt();
        scanner.nextLine();

        switch (subOption) {
            case 1:
                editReminder(id);
                break;
            case 2:
                deleteReminder(id);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void editTask(int id) {
        System.out.print("Task title: ");
        String title = scanner.nextLine();
        System.out.print("Task description: ");
        String description = scanner.nextLine();
        System.out.print("Task date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        int priorityLevel = 0;
        if (controller.isPriority(String.valueOf(id))) {
            System.out.print("Priority level (1-10): ");
            priorityLevel = scanner.nextInt();
            scanner.nextLine();
        }

        // Edit the task and add it to the list
        if (controller.editTask(String.valueOf(id), title, description, date, priorityLevel)) {
            System.out.println("Task has been edited successfully.");
        } else {
            System.out.println("Task edit failed.");
        }
    }

    public void editReminder(int id) {
        System.out.print("Reminder title: ");
        String title = scanner.nextLine();
        System.out.print("Reminder description: ");
        String description = scanner.nextLine();
        System.out.print("Reminder date (dd/mm/yyyy): ");
        String dateString = scanner.nextLine();
        if (controller.editReminder(String.valueOf(id), title, description, dateString)) {
            System.out.println("Reminder has been edited successfully.");
        } else {
            System.out.println("Reminder edit failed.");
        }
    }

    public void deleteTask(int id) {
        if (controller.deleteTask(String.valueOf(id))) {
            System.out.println("Task has been deleted successfully.");
        } else {
            System.out.println("Task deletion failed.");
        }
    }

    public void deleteReminder(int id) {
        if (controller.deleteReminder(String.valueOf(id))) {
            System.out.println("Reminder has been deleted successfully.");
        } else {
            System.out.println("Reminder deletion failed.");
        }
    }
}
