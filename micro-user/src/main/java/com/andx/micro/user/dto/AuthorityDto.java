package com.andx.micro.user.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by andongxu on 16-11-22.
 */
public class AuthorityDto implements Serializable {

    @NotNull(message = "不允许为空")
    private String accountNo;

    @NotNull(message = "不允许为空")
    private String password;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
