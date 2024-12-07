package com.amen.loisir.Entities;


import lombok.*;
import java.io.Serializable;

import javax.persistence.*;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }



}