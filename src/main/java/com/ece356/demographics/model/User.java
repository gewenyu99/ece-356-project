package com.ece356.demographics.model;

public class User {
    private String username;
    private String password;
    private int enabled;

    public String getUsername(){ return this.username; }

    public void setUsername(String username){ this.username = username; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password) { this.password = password; }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
