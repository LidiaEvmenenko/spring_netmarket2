package marketproducts.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Введите логин")
    @Length(min = 3, max = 255, message = "Длина логина должна составлять 3-255 символов")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Введите nic")
    @Length(min = 3, max = 255, message = "Длина nic-а должна составлять 3-255 символов")
    @Column(name = "nicname")
    private String nicname;

    @NotNull(message = "Введите пароль")
    @Length(min = 3, max = 255, message = "Длина пароля должна составлять 3-255 символов")
    @Column(name = "password")
    private String password;

    @Length(min = 9, max = 255, message = "Длина пароля должна составлять 9-255 символов")
    @Column(name = "email")
    private String email;

    @Length(min = 9, max = 255, message = "Длина пароля должна составлять 9-255 символов")
    @Column(name = "phonenumber")
    private String phonenumber;

    @Min(value = 100, message = "Баланс должен быть не менее 100 рублей")
    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
