package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperatorModel {
    private String OpId;
    private String Name;
    private String Address;
    private String Tel;
    private double Salary;
    private int YrOfExperience;
    private String Userid;
    private String MaId;
}
