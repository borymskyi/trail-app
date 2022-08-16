package com.borymskyi.trail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean domain object that represents Profile.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Trails> trails;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "user_id",
                    foreignKey = @ForeignKey(name = "FK_USERS_ID", foreignKeyDefinition = "FOREIGN KEY (USER_ID) REFERENCES USERS ON DELETE CASCADE ON UPDATE CASCADE"),
                    nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "role_id",
                    foreignKey = @ForeignKey(name = "FK_ROLES_ID", foreignKeyDefinition = "FOREIGN KEY (ROLE_ID) REFERENCES ROLES ON DELETE CASCADE ON UPDATE CASCADE"),
                    nullable = false)
    )
    private List<Roles> roles = new ArrayList<>();
}
