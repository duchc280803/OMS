package e.authservice.entity;

import e.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {

    private String username;

    private String password;

    @Column(name = "full_name")
    private String fullName;

    private Boolean gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    private String email;

    private Integer status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRole role;
}