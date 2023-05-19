package sample.POJO;

public class ProductsPOJO {
    String name;
    int price;
    int id;
    String ctegory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCtegory() {
        return ctegory;
    }

    public void setCtegory(String ctegory) {
        this.ctegory = ctegory;
    }

    public ProductsPOJO(int id,String name, int price, String ctegory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ctegory = ctegory;
    }
}
