package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialModel {
    private String MId;
    private String Description;
    private double UnitCost;
    private int QtyOnHand;
    private String SId;

}
