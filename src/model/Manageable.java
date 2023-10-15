package model;

public interface Manageable {
    boolean edit();
    boolean delete();
    String getTitle();

    int getKey();
}
