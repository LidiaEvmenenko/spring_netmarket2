package marketproducts.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import marketproducts.entity.Manufacturer;

@Data
@NoArgsConstructor
public class ManufacturerDto {
    private Long id;
    private String title;
    private Double balance;

    public ManufacturerDto(Manufacturer manufacturer) {
        this.id = manufacturer.getId();
        this.title = manufacturer.getTitle();
        this.balance = manufacturer.getBalance();
    }
}
