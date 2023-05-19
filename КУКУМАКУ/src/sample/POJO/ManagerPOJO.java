package sample.POJO;

public class ManagerPOJO {
    int id;
    int order_id;
    String product;
    int amount;
    int total_price;
    int number2;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getOrder_id(){
        return order_id;
    }

    public void setOrder_id(int order_id){
        this.order_id = order_id;
    }

    public String getProduct(){
        return product;
    }

    public void setProduct(String product){
        this.product = product;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getTotal_price(){
        return total_price;
    }

    public void setTotal_price(int total_price){
        this.total_price = total_price;
    }

    public int getNumber2(){
        return number2;
    }

    public void setNumber2(int number2){
        this.number2 = number2;
    }

    public ManagerPOJO(int id, int order_id, String product, int amount, int total_price, int number2){
        this.id = id;
        this.order_id = order_id;
        this.product = product;
        this.amount = amount;
        this.total_price = total_price;
        this.number2 = number2;
    }

}
