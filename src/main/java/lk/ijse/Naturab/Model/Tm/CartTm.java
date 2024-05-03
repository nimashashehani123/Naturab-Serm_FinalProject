package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private JFXButton btnRemove;
}