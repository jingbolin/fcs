/**
 *
 */
package com.yinhe.ec.cpps.sys.model;

import java.io.Serializable;

/**
 * @author ShenHuaJie
 * @version 2017年3月18日 下午1:40:45
 */
@SuppressWarnings("serial")
public class Login implements Serializable {

    private String account;
    private String password;
    private Boolean rememberMe = false;

    public Login() {
    }

    public Login(String account, String password, Boolean rememberMe) {
        this.account = account;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     * the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     * the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
