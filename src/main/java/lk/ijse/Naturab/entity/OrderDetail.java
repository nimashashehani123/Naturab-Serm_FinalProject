package lk.ijse.Naturab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {
    private String OId;
    private String PId;
    private int qty;
}
