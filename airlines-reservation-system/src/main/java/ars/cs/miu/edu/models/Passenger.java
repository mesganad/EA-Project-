package ars.cs.miu.edu.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Passenger extends Person{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Reservation> reservations;

}
