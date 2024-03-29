package com.borymskyi.trail.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * JavaBean domain object that represents Trail.
 *
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@Entity
@Table(name = "trails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trail_id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd, h:mm a")
    private LocalDateTime update_time;

    @ManyToOne
    @JoinColumn(name = "users_user_id")
    @JsonIgnore
    private Users user;
}
