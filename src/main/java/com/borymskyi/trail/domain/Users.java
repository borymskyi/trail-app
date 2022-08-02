package com.borymskyi.trail.domain;

import lombok.AllArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_u;

    private String username;
    private String password;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Trails> trails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Roles",
            joinColumns = @JoinColumn(name = "user_id",
                    nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id_r",
                    foreignKey = @ForeignKey(name = "FK_ROLES_ID", foreignKeyDefinition = "FOREIGN KEY (ROLE_ID) REFERENCES ROLES ON DELETE CASCADE"),
                    nullable = false,
                    updatable = false)
    )
    private List<Roles> roles = new ArrayList<>();
}
