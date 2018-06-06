package uk.co.ribot.androidboilerplate.data.model;

public class CustomCategory {

    int icon;
    String title;
    public CustomCategory() {
    }

    public CustomCategory(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
