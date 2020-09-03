package com.example.polls.payload;

import javax.validation.constraints.*;

/**
 * Created by nikola brodar on 20/9/2019
 */

public class SolvedSubMasterClassRequest {


    @NotNull
    private Long id_masterclass;

    @NotBlank
    @Size(min = 2, max = 40)
    private String masterClassName;

    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @NotBlank
    @Size(min = 1, max = 15)
    private String solved;

    @NotBlank
    @Size(min = 2, max = 300)
    private String subMasterClassUniqueId;

    @NotBlank
    @Size(min = 2, max = 150)
    private String fileName;

    @NotNull
    private Long id_user;



    public Long getId_masterclass() {
        return id_masterclass;
    }

    public void setId_masterclass(Long id_masterclass) {
        this.id_masterclass = id_masterclass;
    }

    public String getMasterClassName() {
        return masterClassName;
    }

    public void setMasterClassName(String masterClassName) {
        this.masterClassName = masterClassName;
    }

    public String getSubMasterClassUniqueId() {
        return subMasterClassUniqueId;
    }

    public void setSubMasterClassUniqueId(String subMasterClassUniqueId) {
        this.subMasterClassUniqueId = subMasterClassUniqueId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
