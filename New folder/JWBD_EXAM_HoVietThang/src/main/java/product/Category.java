package product;

public class Category {
    int id;
    String category;
    String description_category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(int id) {
        this.id = id;
    }

    public Category(int id, String category, String description_category) {
        this.id = id;
        this.category = category;
        this.description_category = description_category;
    }

    public Category(String category, String description_category) {
        this.category = category;
        this.description_category = description_category;
    }

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription_category() {
        return description_category;
    }

    public void setDescription_category(String description_category) {
        this.description_category = description_category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", description='" + description_category + '\'' +
                '}';
    }
}
