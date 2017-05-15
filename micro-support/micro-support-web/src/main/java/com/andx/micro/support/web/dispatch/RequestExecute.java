package com.andx.micro.support.web.dispatch;

import com.andx.micro.api.core.dto.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andongxu on 17-3-29.
 */
public interface RequestExecute {

    public String execute(Object ... args) throws Exception;

}
