package cz.itnetwork.dialogs;

public class ListViewAdapterItem {
    private String name;
    private String description;
    private boolean enabled;

    public ListViewAdapterItem(String name, String description, boolean enabled) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
