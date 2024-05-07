package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialTm {
    private String MId;
    private String Description;
    private double UnitCost;
    private int Qty;
    private String SId;
    private JFXButton btnedit;
    private JFXButton btndelete;


}
