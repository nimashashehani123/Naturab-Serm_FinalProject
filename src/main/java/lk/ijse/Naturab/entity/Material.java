package lk.ijse.Naturab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Material {
    private String MId;
    private String Description;
    private double UnitCost;
    private int QtyOnHand;
    private String SId;

}
