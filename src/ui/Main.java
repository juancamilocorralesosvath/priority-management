package ui;
import java.util.*;

import exceptions.*;
import model.Controller;
public class Main {
    private final Scanner scanner;
    private final Controller controller;

    public Main() {
        controller = new Controller();
        scanner = new Scanner(System.in);
    }


    public static void main(String[] args) throws EntryException {
        Main executable = new Main();
        executable.menu();
    }


    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Crear tarea");
            System.out.println("2. Crear recordatorio");
            System.out.println("3. Deshacer la última acción");
            System.out.println("4. Visualizar");
            System.out.println("5. gestionar una tarea");
            System.out.println("6. gestionar un recordatorio");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    createTask();
                    break;
                case 2:
                    createReminder();
                    break;
                case 3:
                    // Lógica para deshacer la última acción
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
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void watchMenu() {
        System.out.println("Submenú de visualización:");
        System.out.println("1. Mostrar todo");
        System.out.println("2. Mostrar tareas y recordatorios");
        System.out.println("3. Mostrar tareas prioritarias");
        System.out.print("Elige una opción: ");
        int subOpcion = scanner.nextInt();

        switch (subOpcion) {
            case 1:
                System.out.println(controller.getActivities());
                System.out.println(controller.getPriorityActivities());
                break;
            case 2:
                System.out.println(controller.getActivities());
                break;
            case 3:
                System.out.println(controller.getPriorityActivities());
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public  void createTask() {
        System.out.println("Título de la tarea: ");
        String title = scanner.nextLine();
        System.out.println("Descripción de la tarea: ");
        String description = scanner.nextLine();
        System.out.println("Fecha de la tarea (dd/mm/aaaa): ");
        String date = scanner.nextLine();

        System.out.print("¿Es prioritaria? (Si/No): ");
        String priorityInput = scanner.nextLine();
        boolean isPriority = priorityInput.equalsIgnoreCase("Si");

        int priorityLevel = 0;
        if (isPriority) {
            System.out.print("Nivel de prioridad (1-10): ");
            priorityLevel = scanner.nextInt();
        }

        // Crear la tarea y agregarla a la lista
        if(controller.addTask(title, description, date, isPriority, priorityLevel)){
            System.out.println("Tarea creada con éxito.");
        } else {
            System.out.println("No se pudo crear la tarea.");
        }

    }

    public void createReminder() {
        System.out.print("Título del recordatorio: ");
        String title = scanner.nextLine();
        System.out.print("Descripción del recordatorio: ");
        String description = scanner.nextLine();
        System.out.print("Fecha del recordatorio (dd/mm/aaaa): ");
        String dateString = scanner.nextLine();
        if(controller.addReminder(title, description, dateString)){
            System.out.println("Reminder creado con éxito.");
        } else {
            System.out.println("No se pudo crear el Reminder.");
        }

    }

    public void manageTask() {
        System.out.print("Ingrese el id de la tarea que desea gestionar: ");
        int id = scanner.nextInt();
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.print("Elige una opción: ");
        int subOpcion = scanner.nextInt();

        switch (subOpcion) {
            case 1:
                editTask();
                break;
            case 2:
                deleteTask();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public void manageReminder() {
        System.out.print("Ingrese el id del recordatorio que desea gestionar: ");
        int id = scanner.nextInt();
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.print("Elige una opción: ");
        int subOpcion = scanner.nextInt();

        switch (subOpcion) {
            case 1:
                editReminder();
                break;
            case 2:
                deleteReminder();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }


    public void editTask() {
        System.out.print("Ingrese el id de la tarea que desea editar: ");
        int id = scanner.nextInt();
        System.out.print("Título de la tarea: ");
        String title = scanner.nextLine();
        System.out.print("Descripción de la tarea: ");
        String description = scanner.nextLine();
        System.out.print("Fecha de la tarea (dd/mm/aaaa): ");
        String date = scanner.nextLine();


        int priorityLevel = 0;
        if (controller.isPriority(String.valueOf(id))) {
            System.out.print("Nivel de prioridad (1-10): ");
            priorityLevel = scanner.nextInt();
        }

        // Crear la tarea y agregarla a la lista
        if(controller.editTask(String.valueOf(id), title, description, date, priorityLevel)){
            System.out.println("Tarea ha sido editada con éxito.");
        } else {
            System.out.println("No se pudo crear la tarea.");
        }
    }

    public void editReminder() {
        System.out.print("Ingrese el id del recordatorio que desea editar: ");
        int id = scanner.nextInt();
        System.out.print("Título del recordatorio: ");
        String title = scanner.nextLine();
        System.out.print("Descripción del recordatorio: ");
        String description = scanner.nextLine();
        System.out.print("Fecha del recordatorio (dd/mm/aaaa): ");
        String dateString = scanner.nextLine();
        if(controller.editReminder(String.valueOf(id), title, description, dateString)){
            System.out.println("Reminder creado con éxito.");
        } else {
            System.out.println("No se pudo crear el Reminder.");
        }
    }

    public void deleteTask() {
        System.out.print("Ingrese el id de la tarea que desea eliminar: ");
        int id = scanner.nextInt();
        if ( controller.deleteTask(String.valueOf(id)) ){
            System.out.println("Tarea ha sido eliminada con éxito.");
        } else {
            System.out.println("No se pudo eliminar la tarea.");
        }
    }

    public void deleteReminder() {
        System.out.print("Ingrese el id del recordatorio que desea eliminar: ");
        int id = scanner.nextInt();
        if ( controller.deleteReminder(String.valueOf(id)) ){
            System.out.println("Recordatorio ha sido eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el recordatorio.");
        }
    }


}
