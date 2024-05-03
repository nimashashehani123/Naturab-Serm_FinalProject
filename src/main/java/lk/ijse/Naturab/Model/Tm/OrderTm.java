package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderTm {
    private String OId;
    private Date  PlacedDate;
    private double PaymentAmount;
    private String Status;
    private String CId;
    private JFXButton btnedit;
    private JFXButton btndelete;

}
