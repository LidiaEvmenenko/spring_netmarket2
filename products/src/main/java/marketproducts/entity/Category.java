package marketproducts.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Введите название категории")
    @Column
    private String title;


    @OneToMany(mappedBy = "category")
    @OrderBy("title")
    private List<Product> products;

}




















