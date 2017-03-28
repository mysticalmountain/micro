package com.andx.micro.permission.dto.permission;

import java.io.Serializable;

/**
 * Created by andongxu on 16-11-21.
 */
public class PermissionDto implements Serializable {

    private Long id;

    private ResourceDto resource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceDto getResource() {
        return resource;
    }

    public void setResource(ResourceDto resource) {
        this.resource = resource;
    }
}
