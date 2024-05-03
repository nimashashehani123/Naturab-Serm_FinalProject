package lk.ijse.Naturab.Model;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderModel {
    private String OId;
    private Date  PlacedDate;
    private double PaymentAmount;
    private String Status;
    private String CId;

}
