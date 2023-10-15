package model;

public class UserAction {
    private String action;
    private String key;
    private String[] oldData;

    public UserAction(String action, String key) {
        this.action = action;
        this.key = key;
    }

    public UserAction(String action, String key, String[] oldData) {
        this.action = action;
        this.key = key;
        this.oldData = oldData;
    }

    public String getAction() {
        return action;
    }

    public String getKey() {
        return key;
    }

    public String[] getOldData() {
        return oldData;
    }
}
