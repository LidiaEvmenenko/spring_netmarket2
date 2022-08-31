package marketfront.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stocks")
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date delivery_date;

    @Column
    private Double amount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
