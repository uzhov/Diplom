package sample.POJO;

public class AdddeliverymanPOJO {
    String Number2;
    String Product;
    String Guarantee;
    String Quantity;
    String Totalprice;

    public String getNumber2(){
        return Number2;
    }

    public void setNumber2(String Number2){
        this.Number2 = Number2;
    }

    public String getProduct(){
        return Product;
    }

    public  void setProduct(String Product){
        this.Product = Product;
    }

    public String getGuarantee(){
        return Guarantee;
    }

    public void setGuarantee(String Guarantee){
        this.Guarantee = Guarantee;
    }

    public String getQuantity(){
        return Quantity;
    }

    public void setQuantity(String Quantity){
        this.Quantity = Quantity;
    }

    public String getTotalprice(){
        return Totalprice;
    }

    public void setTotalprice(String Totalprice){
        this.Totalprice = Totalprice;
    }

    public AdddeliverymanPOJO(String Number2, String Product, String Guarantee, String Quantity, String Totalprice){
        this.Number2 = Number2;
        this.Product = Product;
        this.Guarantee = Guarantee;
        this.Quantity = Quantity;
        this.Totalprice = Totalprice;
    }
}
