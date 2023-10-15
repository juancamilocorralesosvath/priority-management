package model;

import datastructures.HashTable;
import datastructures.PriorityQueue;
import datastructures.Queue;

import java.text.SimpleDateFormat;
import java.util.Random;
public class Controller {
    private HashTable<String, Manageable> hashTable;
    private Queue<Manageable> queue;
    private PriorityQueue<PriorityTask> priorityQueue;
    private int key;
    private Random random;

    public Controller(){
        hashTable = new HashTable<>(100);
        key = 1;
        random = new Random();
        queue = new Queue<>();
        priorityQueue = new PriorityQueue<>();
    }

    public boolean addTask(String title, String description, String date,  boolean isPriority, int priorityLevel){
        key += random.nextInt(0,15);
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
            msg += "\nID:" + manageable.getKey() + ", " + manageable.getTitle() + ", " + ((manageable instanceof Task) ? "Tarea" : "Recordatorio");
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
            msg += "\nID:" + manageable.getKey() + ", " + manageable.getTitle() + ", Tarea prioritaria nivel " + ((PriorityTask) manageable).getPriorityLevel() + "";
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
                    tmp.setTitle(title);
                    tmp.setDescription(description);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    tmp.setDate(dateFormat.parse(date));
                    tmp.setPriorityLevel(priorityLevel);
                    return true;
                } else {
                    Task tmp = (Task) task;
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
                    priorityQueue.delete((PriorityTask) task);
                } else {
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
                queue.delete(reminder);
                return hashTable.delete(key) != null;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }
}
