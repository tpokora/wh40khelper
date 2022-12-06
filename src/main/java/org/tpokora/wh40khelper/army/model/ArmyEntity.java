package org.tpokora.wh40khelper.army.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "army")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ArmyEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String faction;
}
