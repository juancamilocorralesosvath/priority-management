package model;

import datastructures.HashTable;

public class Controller {
    private HashTable<String, Manageable> hashTable;

    public Controller(){
        hashTable = new HashTable<>(100);
    }

    public boolean addTask(String title, String description, String date,  boolean isPriority, int priorityLevel){
        try {
            if ( isPriority ){
                PriorityTask priorityTask = new PriorityTask(title, description, date, isPriority, priorityLevel);
                return hashTable.add(description, priorityTask);
            } else {
                Task task = new Task(title, description, date);
                return hashTable.add(description, task);
            }

        }catch (Exception e){
            return false;
        }
    }

    public boolean addReminder(String title, String description, String date){
        try {
            Reminder reminder = new Reminder(title, description, date);
            return hashTable.add(description, reminder);
        }catch (Exception e){
            return false;
        }
    }

}
