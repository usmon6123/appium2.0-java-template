package org.example.m10;

public class OrderModul {

    private Integer id;

    private float price;
    private String cardNumber;
    private String cardYear;
    private String cvv2;
    private String status;
    private String message;

    public OrderModul(Integer id, float price, String cardNumber, String cardYear, String cvv2, String status, String message) {
        this.id = id;
        this.price = price;
        this.cardNumber = cardNumber;
        this.cardYear = cardYear;
        this.cvv2 = cvv2;
        this.status = status;
        this.message = message;
    }

    public OrderModul() {
    }

    @Override
    public String toString() {
        return "OrderModul{" +
                "id=" + id +
                ", price=" + price +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardYear='" + cardYear + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardYear() {
        return cardYear;
    }

    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
