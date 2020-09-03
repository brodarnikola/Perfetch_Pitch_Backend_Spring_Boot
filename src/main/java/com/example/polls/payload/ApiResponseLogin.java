package com.example.polls.payload;

import com.example.polls.model.MasterClass;
import com.example.polls.model.SubMasterClass;

import java.util.List;

/**
 * Created by nikola brodar on 20/9/2019
 */
public class ApiResponseLogin {
    private Boolean success;
    private String message;
    private Long userId;

    private List<MasterClass> masterClasses;
    private List<SubMasterClass> subMasterClasses;

    public ApiResponseLogin(Boolean success, String message, Long userId, List<MasterClass> masterClasses, List<SubMasterClass> subMasterClasses) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.masterClasses = masterClasses;
        this.subMasterClasses = subMasterClasses;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<MasterClass> getMasterClasses() {
        return masterClasses;
    }

    public void setMasterClasses(List<MasterClass> masterClasses) {
        this.masterClasses = masterClasses;
    }

    public List<SubMasterClass> getSubMasterClasses() {
        return subMasterClasses;
    }

    public void setSubMasterClasses(List<SubMasterClass> subMasterClasses) {
        this.subMasterClasses = subMasterClasses;
    }
}
