package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderModel {
    private OrderModel order;
    private List<OrderDetailModel> odList;
}