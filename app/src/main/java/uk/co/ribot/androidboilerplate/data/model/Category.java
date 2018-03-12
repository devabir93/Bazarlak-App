package uk.co.ribot.androidboilerplate.data.model;

/**
 * Created by Dev_Abir on 03/08/2018.
 */

public class Category {

    private int img;
    private String name;

    public Category() {
    }

    public Category(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (img != category.img) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        int result = img;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
