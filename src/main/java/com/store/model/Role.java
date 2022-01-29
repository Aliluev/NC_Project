package com.store.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
@Data
public class Role {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
   // @Enumerated(EnumType.STRING)
    private String string;
    //private  RoleName roleName; как подключить ENUM к бд?!


    @ManyToMany
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="roleid"),
          inverseJoinColumns = @JoinColumn(name = "userid"))
    private List<User> users;


}
