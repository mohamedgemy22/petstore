package com.reload.petsstore.fav.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddFav {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("result")
    @Expose
    private List<Object> result = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;

    }
}
