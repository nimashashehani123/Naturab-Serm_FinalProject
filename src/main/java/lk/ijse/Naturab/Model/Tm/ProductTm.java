package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductTm {
    private String PId;
    private String Description;
    private ImageView Design;
    private double UnitPrice;
    private int QtyOnHand;
    private String WId;
    private JFXButton btndelete;
    private JFXButton btnedit;

}
