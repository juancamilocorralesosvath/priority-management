package ui;
import java.util.*;

import exceptions.*;
import model.Controller;
public class Main {
    private Scanner scanner;
    private Controller controller;

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
            System.out.println("5. Salir");
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
                        // Lógica para visualizar
                        watchMenu();
                        break;
                    case 5:
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
            System.out.println("2. Mostrar ordenado por fecha límite");
            System.out.println("3. Mostrar ordenado por prioridad");
            System.out.print("Elige una opción: ");
            int subOpcion = scanner.nextInt();

            switch (subOpcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        public  void createTask() {
            System.out.print("Título de la tarea: ");
            String title = scanner.nextLine();
            System.out.print("Descripción de la tarea: ");
            String description = scanner.nextLine();
            System.out.print("Fecha de la tarea (dd/mm/aaaa): ");
            String date = scanner.nextLine();

            System.out.print("¿Es prioritaria? (Si/No): ");
            String priorityInput = scanner.nextLine();
            boolean isPriority = priorityInput.equalsIgnoreCase("Si");

            int priorityLevel = 0;
            if (isPriority) {
                System.out.print("Nivel de prioridad (1-3): ");
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


}
