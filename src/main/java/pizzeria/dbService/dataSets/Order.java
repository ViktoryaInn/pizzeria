package pizzeria.dbService.dataSets;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Order")
public class Order {
    public Order() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String clientName;
    @Column
    private String clientPhone;
    @Column
    private int cost;
    @Column
    private Date date; //TODO: в контролерре устанавливать дату

    public Order(int id, String clientName, String clientPhone, int cost, Date date){
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.cost = cost;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public int getCost() {
        return cost;
    }

    public Date getDate() {return date;}

    public void setClientName(String clientName){
        this.clientName = clientName;
    }

    public void setClientPhone(String clientPhone){
        this.clientPhone = clientPhone;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
