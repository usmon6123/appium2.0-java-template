package org.example.modul;

public class WithdrawalModule {

    private Integer id;
    private String transaction_id;
    private String trader;
    private String sender_name;
    private String sender_id;
    private Double amount;
    private String card_no;
    private String expiry_date;
    private String robot;
    private String robot_time;
    private String robot_status;
    private Double amount_received;
    private Integer result;
    private String comment;
    private String start_time;
    private String finish_time;
    private String api_status;
    private Integer user_id;


    public WithdrawalModule() {
    }

    public WithdrawalModule(Integer id, String transaction_id, Double amount, String card_no, String expiry_date, String robot) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.card_no = card_no;
        this.expiry_date = expiry_date;
        this.robot = robot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    @Override
    public String toString() {
        return "WithdrawalModule{" +
                "id=" + id +
                ", transaction_id='" + transaction_id + '\'' +
                ", amount=" + amount +
                ", card_no='" + card_no + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", robot='" + robot + '\'' +
                '}';
    }
}
