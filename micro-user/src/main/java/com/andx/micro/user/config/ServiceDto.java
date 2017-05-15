package com.andx.micro.user.config;

import java.io.Serializable;

/**
 * Created by andongxu on 17-2-23.
 */
public class ServiceDto implements Serializable {

    private Long id;

    private String code;

    private String path;

    private String content;

    private String system;

    private String module;

    private String method;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getModule() {
        return module;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
