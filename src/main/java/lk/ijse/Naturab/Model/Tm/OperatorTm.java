package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OperatorTm {
    private String OpId;
    private String Name;
    private String Address;
    private String Tel;
    private String MaId;
    private JFXButton btndelete;
    private JFXButton btnedit;

}
