package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MachineModel {
    private String MaId;
    private String Description;
    private String Type;
    private String Capacity;
    private String Status;
}
