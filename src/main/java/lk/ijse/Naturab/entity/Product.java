package lk.ijse.Naturab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Product {
    private String PId;
    private String Description;
    private String Design;
    private double UnitPrice;
    private int QtyOnHand;
    private String MaId;
    private String WId;

}
