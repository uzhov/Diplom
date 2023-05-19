package sample.POJO;

public class AdminclassPOJO {
    String name;
    int price;
    String category;

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

    public void  setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public AdminclassPOJO(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
