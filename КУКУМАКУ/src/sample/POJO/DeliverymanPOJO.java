package sample.POJO;

public class DeliverymanPOJO {
    String Number;
    public int Order_number;
    String Buer;
    String Telephone;
    String Address;

    public String getNumber(){
        return Number;
    }

    public void setNumber(String Number){
        this.Number = Number;
    }

    public int getOrder_number(){
        return Order_number;
    }

    public void setOrder_number(int Order_number){
        this.Order_number = Order_number;
    }

    public String getBuer(){
        return Buer;
    }

    public void setBuer(){
        this.Buer = Buer;
    }

    public String getTelephone(){
        return Telephone;
    }

    public void setTelephone(){
        this.Telephone = Telephone;
    }

    public String getAddress(){
        return Address;
    }

    public void setAddress(){
        this.Address = Address;
    }

    public DeliverymanPOJO(String Number,int Order_number,String Buer, String Telephone,String Address){
        this.Number = Number;
        this.Order_number = Order_number;
        this.Buer = Buer;
        this.Telephone = Telephone;
        this.Address = Address;
    }
}
