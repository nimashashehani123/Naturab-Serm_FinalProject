package lk.ijse.Naturab.entity;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String OId;
    private Date  PlacedDate;
    private double PaymentAmount;
    private String Status;
    private String CId;

}
