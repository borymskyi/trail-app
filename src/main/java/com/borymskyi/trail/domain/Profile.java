package com.borymskyi.trail.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * JavaBean domain object that represents Profile.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String profilepic;
    private String email;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
//    @JsonIgnore
    private List<Trail> trails;

    public void addTrail(Trail trail) {
        this.trails.add(trail);
        trail.setProfile(this);
    }

    public void removeTrail(Trail trail) {
        this.trails.remove(trail);
        trail.setProfile(null);
    }
}
