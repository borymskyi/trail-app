package com.borymskyi.trail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JavaBean domain object that represents Role.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(unique = true, nullable = false)
    private String name;
}
