package model;

import datastructures.HashTable;
import datastructures.PriorityQueue;
import datastructures.Queue;
import datastructures.Stack;

import java.text.SimpleDateFormat;
import java.util.Random;

public class Controller {
    private HashTable<String, Manageable> hashTable;
    private Queue<Manageable> queue;
    private PriorityQueue<PriorityTask> priorityQueue;
    private int key;
    private Random random;

    private Stack<UserAction> userActionStack;




    public Controller(){
        hashTable = new HashTable<>(100);
        key = 1;
        random = new Random();
        queue = new Queue<>();
        priorityQueue = new PriorityQueue<>();
        userActionStack = new Stack<>();
    }

    public boolean addTask(String title, String description, String date,  boolean isPriority, int priorityLevel){
        key += random.nextInt(0,15);
        userActionStack.push(new UserAction("AddTask", String.valueOf(key)));
        try {
            if ( isPriority ){
                PriorityTask priorityTask = new PriorityTask(key, title, description, date, isPriority, priorityLevel);
                priorityQueue.insert(priorityTask);
                return hashTable.add(String.valueOf(key), priorityTask);
            } else {
                Task task = new Task(key, title, description, date);
                queue.enqueue(task);
                return hashTable.add(String.valueOf(key), task);
            }

        }catch (Exception e){
            return false;
        }
    }

    public boolean addReminder(String title, String description, String date){
        key += random.nextInt(0,15);
        userActionStack.push(new UserAction("AddReminder", String.valueOf(key)));
        try {
            Reminder reminder = new Reminder(key, title, description, date);
            queue.enqueue(reminder);
            return hashTable.add(String.valueOf(key), reminder);
        }catch (Exception e){
            return false;
        }
    }

    public String getActivities(){
        Queue<Manageable> tmp = new Queue<>();
        String msg = "";
        while (!queue.isEmpty()){
            Manageable manageable = queue.dequeue();
            msg += "\nID:" + manageable.getKey() + ", " + manageable.getTitle() + ", " + ((manageable instanceof Task) ? "Task" : "Reminder");
            tmp.enqueue(manageable);
        }
        queue = tmp;
        return msg;
    }

    public String getPriorityActivities(){
        PriorityQueue<PriorityTask> tmp = new PriorityQueue<>();
        String msg = "";
        while (!priorityQueue.isEmpty()){
            PriorityTask manageable = priorityQueue.poll();
            msg += "\nID:" + manageable.getKey() + ", " + manageable.getTitle() + " , priority task level " + ((PriorityTask) manageable).getPriorityLevel() + "";
            tmp.insert(manageable);
        }
        priorityQueue = tmp;
        return msg;
    }

    public boolean editTask(String key, String title, String description, String date, int priorityLevel){
        Manageable task = hashTable.search(key);
        try {
            if ( task != null ){
                if ( task instanceof PriorityTask ){
                    PriorityTask tmp = (PriorityTask) task;
                    userActionStack.push(new UserAction("EditTask", key, new String[]{tmp.getTitle(), tmp.getDescription(), String.valueOf(tmp.getDate()), String.valueOf(tmp.getPriorityLevel())}));
                    tmp.setTitle(title);
                    tmp.setDescription(description);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    tmp.setDate(dateFormat.parse(date));
                    tmp.setPriorityLevel(priorityLevel);

                    return true;
                } else {
                    Task tmp = (Task) task;
                    userActionStack.push(new UserAction("EditTask", key, new String[]{tmp.getTitle(), tmp.getDescription(), String.valueOf(tmp.getDate())}));
                    tmp.setTitle(title);
                    tmp.setDescription(description);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    tmp.setDate(dateFormat.parse(date));
                }
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public boolean editReminder(String key, String title, String description, String date){
        Manageable reminder = hashTable.search(key);
        try {
            if ( reminder != null ){
                Reminder tmp = (Reminder) reminder;
                userActionStack.push(new UserAction("EditReminder", key, new String[]{tmp.getTitle(), tmp.getDescription(), String.valueOf(tmp.getDate())}));
                tmp.setTitle(title);
                tmp.setDescription(description);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tmp.setDate(dateFormat.parse(date));
                return true;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public boolean isPriority(String key){
        return hashTable.search(key) instanceof PriorityTask;
    }


    public boolean deleteTask(String key){
        Manageable task = hashTable.search(key);
        try {
            if ( task != null ){
                if ( task instanceof PriorityTask ){
                    PriorityTask tmp = (PriorityTask) task;
                    userActionStack.push(new UserAction("DeleteTask", key, new String[]{tmp.getTitle(), tmp.getDescription(), String.valueOf(tmp.getDate()), String.valueOf(tmp.getPriorityLevel())}));
                    priorityQueue.delete(tmp);
                } else {
                    Task tmp = (Task) task;
                    userActionStack.push(new UserAction("DeleteTask", key, new String[]{tmp.getTitle(), tmp.getDescription(), String.valueOf(tmp.getDate())}));
                    queue.delete(task);
                }
                return hashTable.delete(key) != null;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }


    public boolean deleteReminder(String key){
        Manageable reminder = hashTable.search(key);
        try {
            if ( reminder != null ){
                Reminder tmp = (Reminder) reminder;
                userActionStack.push(new UserAction("DeleteReminder", key, new String[]{tmp.getTitle(), tmp.getDescription(), String.valueOf(tmp.getDate())}));
                queue.delete(tmp);
                return hashTable.delete(key) != null;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public void undo() {
        if (!userActionStack.isEmpty()) {
            UserAction lastAction = userActionStack.pop();
            String action = lastAction.getAction();
            String key = lastAction.getKey();

            if (action.equals("AddTask")) {
                // Si la última acción fue agregar una tarea, deshazla
                if (deleteTask(key)) {
                    // Agrega la acción al stack de deshacer
                    userActionStack.push(new UserAction("DeleteTask", key));
                }
            } else if (action.equals("AddReminder")) {
                // Si la última acción fue agregar un recordatorio, deshazla
                if (deleteReminder(key)) {
                    // Agrega la acción al stack de deshacer
                    userActionStack.push(new UserAction("DeleteReminder", key));
                }
            } else if (action.equals("EditTask")) {
                // Si la última acción fue editar una tarea, deshazla
                if (lastAction.getOldData() != null) {
                    String[] data = lastAction.getOldData();
                    if (editTask(key, data[0], data[1], data[2], Integer.parseInt(data[3]))) {
                        // Agrega la acción al stack de deshacer
                        userActionStack.push(new UserAction("EditTask", key, data));
                    }
                }
            } else if (action.equals("EditReminder")) {
                // Si la última acción fue editar un recordatorio, deshazla
                if (lastAction.getOldData() != null) {
                    String[] data = lastAction.getOldData();
                    if (editReminder(key, data[0], data[1], data[2])) {
                        // Agrega la acción al stack de deshacer
                        userActionStack.push(new UserAction("EditReminder", key, data));
                    }
                }
            } else if (action.equals("DeleteTask")) {
                // Si la última acción fue eliminar una tarea, regrésala
                if (lastAction.getOldData() != null) {
                    String[] data = lastAction.getOldData();
                    if (addTask(data[0], data[1], data[2], false, 0)) {
                        // Agrega la acción al stack de deshacer
                        userActionStack.push(new UserAction("AddTask", key));
                    }
                }
            } else if (action.equals("DeleteReminder")) {
                // Si la última acción fue eliminar un recordatorio, regrésalo
                if (lastAction.getOldData() != null) {
                    String[] data = lastAction.getOldData();
                    if (addReminder(data[0], data[1], data[2])) {
                        // Agrega la acción al stack de deshacer
                        userActionStack.push(new UserAction("AddReminder", key));
                    }
                }
            }
        }
    }


}
