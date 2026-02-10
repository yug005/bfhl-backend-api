package com.yug.bfhl.model;

public class ApiResponse {

    private boolean is_success;
    private String official_email = "yug2328.be23@chitkara.edu.in";
    private Object data;
    private String error;

    public ApiResponse() {
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getOfficial_email() {
        return official_email;
    }

    public void setOfficial_email(String official_email) {
        this.official_email = official_email;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
