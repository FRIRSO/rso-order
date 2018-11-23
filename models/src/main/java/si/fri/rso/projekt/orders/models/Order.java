package si.fri.rso.projekt.orders.models;


public class Order {

    private int orderID;
    private int restaurantID;
    private int billID;
    private int buyerID;

    public Order(int orderID, int restaurantID, int billID, int buyerID) {
        this.orderID = orderID;
        this.restaurantID = restaurantID;
        this.billID = billID;
        this.buyerID = buyerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
}
