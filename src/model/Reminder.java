package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reminder implements Manageable{
    private String title;
    private String description;
    private Date date;
    private int key;


    public Reminder(int key, String title, String description, String dateString) throws ParseException {
        this.key = key;
        this.title = title;
        this.description = description;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.parse(dateString);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(date);
        return "Título: " + title + ", Descripción: " + description + ", Fecha: " + formattedDate;
    }


    @Override
    public boolean edit() { // todo
        return false;
    }

    @Override
    public boolean delete() { // todo
        return false;
    }
}
