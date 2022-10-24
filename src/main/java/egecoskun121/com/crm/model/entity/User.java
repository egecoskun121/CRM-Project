package egecoskun121.com.crm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Size(min = 5, max = 25, message = "Username length should be between 5 and 25 characters")
    @Column(unique = true, nullable = false)
    private String userName;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @Column(unique = true, nullable = false)
    @NotNull
    private String email;

    @Size(min = 5, message = "Minimum password length: 5 characters")
    @NotNull
    private String password;

    @Transient
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;

    @Transient
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductInquiry> productInquiries;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

}