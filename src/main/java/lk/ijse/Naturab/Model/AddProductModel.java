package lk.ijse.Naturab.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddProductModel {
    private ProductModel product;
    private List<MaterialDetailModel> maList;
}
