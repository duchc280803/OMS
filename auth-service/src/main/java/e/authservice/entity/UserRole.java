package e.authservice.entity;

import e.infra.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"userRole\"")
public class UserRole extends BaseEntity {

    private String id;

    private String role;

    @OneToOne(mappedBy = "role")
    private User user;
}
