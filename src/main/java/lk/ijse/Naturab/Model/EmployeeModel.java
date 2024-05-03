package lk.ijse.Naturab.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeModel {
    private String EId;
    private String Name;
    private String Address;
    private String Tel;
    private double Salary;
    private int YrOfExperience;
    private String Userid;


}