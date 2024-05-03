package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductModel {
    private String PId;
    private String Description;
    private String Design;
    private double UnitPrice;
    private int QtyOnHand;
    private String MaId;
    private String WId;

}
