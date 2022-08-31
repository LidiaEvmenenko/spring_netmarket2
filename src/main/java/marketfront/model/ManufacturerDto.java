package marketfront.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import marketfront.entity.Manufacturer;

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
