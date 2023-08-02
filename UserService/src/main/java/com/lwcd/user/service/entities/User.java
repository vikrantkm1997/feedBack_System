package com.lwcd.user.service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name ="ID")
    private String userId;
    @Column(name ="NAME",length = 20)
    private String name;
    @Column(name ="EMAIL")
    private String email;
    @Column(name ="ABOUT")
    private String about;

    @Transient                                       //Transient ensures it will not fetch this from users Db
    private List<Rating> ratings =new ArrayList<>(); // Will be fetched from different service Rating

}
