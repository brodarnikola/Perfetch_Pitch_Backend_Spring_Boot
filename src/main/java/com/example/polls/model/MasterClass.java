package com.example.polls.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "masterclass" )

public class    MasterClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_masterclass;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    private Long id_user;


    @NotBlank
    @Size(max = 20)
    private String solved;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_masterclass() {
        return id_masterclass;
    }

    public void setId_masterclass(Long id_masterclass) {
        this.id_masterclass = id_masterclass;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getSolved() {
        return solved;
    }

    public void setSolved(String solved) {
        this.solved = solved;
    }


}
