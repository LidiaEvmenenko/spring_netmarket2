package marketfront.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date date;// дата время заказа

    @Column
    private Double amount;

    @Column
    private Double price;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private List<Product> products;

}
