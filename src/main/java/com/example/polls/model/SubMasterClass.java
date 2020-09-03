package com.example.polls.model;

import com.example.polls.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "submasterclass" )

public class SubMasterClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_submasterclass;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String master_class_name;

    @NotBlank
    private Long id_user;

    @NotBlank
    private Long id_masterclass;

    @NotBlank
    @Size(max = 20)
    private String solved;

    private String file_name;

    private String sub_master_class_unique_id;

    public SubMasterClass() {

    }

    public SubMasterClass(String name, String master_class_name, Long id_user, Long id_masterclass,  String solved, String file_name, String sub_master_class_unique_id) {
        this.name = name;
        this.master_class_name = master_class_name;
        this.id_user = id_user;
        this.id_masterclass = id_masterclass;
        this.solved = solved;
        this.file_name = file_name;
        this.sub_master_class_unique_id = sub_master_class_unique_id;
    }

    public Long getId() {
        return id_submasterclass;
    }

    public void setId(Long id) {
        this.id_submasterclass = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterClassName() {
        return master_class_name;
    }

    public void setMasterClassName(String masterClassName) {
        this.master_class_name = masterClassName;
    }

    public Long getId_submasterclass() {
        return id_submasterclass;
    }

    public void setId_submasterclass(Long id_submasterclass) {
        this.id_submasterclass = id_submasterclass;
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

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getSub_master_class_unique_id() {
        return sub_master_class_unique_id;
    }

    public void setSub_master_class_unique_id(String sub_master_class_unique_id) {
        this.sub_master_class_unique_id = sub_master_class_unique_id;
    }
}
