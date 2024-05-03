package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MachineTm {
    private String MaId;
    private String Description;
    private String Type;
    private String Status;
    private JFXButton btnedit;
    private JFXButton btndelete;
}
