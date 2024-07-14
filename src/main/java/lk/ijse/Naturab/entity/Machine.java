package lk.ijse.Naturab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Machine {
    private String MaId;
    private String Description;
    private String Type;
    private String Capacity;
    private String Status;
}
