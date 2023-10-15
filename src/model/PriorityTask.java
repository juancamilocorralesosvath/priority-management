package model;

import java.text.ParseException;

public class PriorityTask extends Task implements Comparable<PriorityTask> {

    private boolean isPriority;
    private int priorityLevel;
    public PriorityTask(int key, String title, String description, String dateString, boolean isPriority, int priorityLevel) throws ParseException {
        super(key, title, description, dateString);

        this.isPriority = isPriority;
        this.priorityLevel = priorityLevel;

    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public int compareTo(PriorityTask o) {
        return Integer.compare(priorityLevel, o.getPriorityLevel());
    }
}
