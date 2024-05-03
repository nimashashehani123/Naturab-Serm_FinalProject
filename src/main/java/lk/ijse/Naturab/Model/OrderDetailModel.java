package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailModel {
    private String OId;
    private String PId;
    private int qty;
}
