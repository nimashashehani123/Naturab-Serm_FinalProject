package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeTm {
    private String EId;
    private String Name;
    private String Address;
    private String Tel;
    private JFXButton btndelete;
    private JFXButton btnedit;

}
