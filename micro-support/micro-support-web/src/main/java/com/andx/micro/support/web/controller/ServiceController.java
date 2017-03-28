package com.andx.micro.support.web.controller;

import com.andx.micro.api.core.exception.UnifiedException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import com.andx.micro.support.web.dispatch.ServiceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by andongxu on 9/11/16.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/service", produces = "application/json; charset=UTF-8")
public class ServiceController {

    private Log log = Log4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    private ServiceDispatcher serviceDispatcher;

    @RequestMapping(value = "{code}")
    public @ResponseBody String service(@PathVariable String code, @RequestBody String body) throws Exception {
        return serviceDispatcher.execute(code, body);
    }
    @RequestMapping(value = "tmp")
    public @ResponseBody String tmp() throws Exception {
        return "success";
    }

    @ExceptionHandler(UnifiedException.class)
    public @ResponseBody String handleIOException(UnifiedException ue) {
        log.debug(ue.getMessage(), ue);
        String result = "{\"errorCode\":" + "" + ",\"errorMessage\":" + ue.getMessage() + ",\"success\":false}";
        log.info(result);
        return result;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String handleIOException(Exception e) {
        log.debug(e.getMessage(), e);
        String message = "";
        if (e instanceof HttpMessageNotReadableException) {
            message = "request data is not readable";
        }else {
            message = "unknown error";
        }
        String result = "{\"errorCode\":" + "999999" + ",\"errorMessage\":" + "\"" + message + "\"" + ",\"success\":false}";
        log.info(result);
        return result;
    }
}
