package lk.ijse.Naturab.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String EId;
    private String Name;
    private String Address;
    private String Tel;
    private double Salary;
    private int YrOfExperience;
    private String Userid;


}