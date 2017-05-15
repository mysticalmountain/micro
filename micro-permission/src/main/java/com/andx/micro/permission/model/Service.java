package com.andx.micro.permission.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

import com.andx.micro.support.jpa.model.Entity;

/**
 * Created by andongxu on 16-8-29.
 */
@javax.persistence.Entity
public class Service extends Entity {
    @Id
    @GenericGenerator(name = "serviceId", strategy = "enhanced-table",
            parameters = {
                    @Parameter(name = "table_name", value = "seq_id"),
                    @Parameter(name = "value_column_name", value = "next"),
                    @Parameter(name = "segment_column_name", value = "segment_name"),
                    @Parameter(name = "segment_value", value = "seq_service"),
                    @Parameter(name = "initial_value", value = "10000000"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "serviceId")
    private Long id;

    @Column(length = 32)
    private String code;

    @Column(length = 255)
    private String content;

    @Column(length = 8)
    private String system;

    @Column(length = 8)
    private String module;

    @Column(length = 255)
    private String path;

    @Column(length = 6)
    private String method;


    @OneToOne
    @JoinColumn(name = "resource_id", unique = true, nullable = false, updatable = false)
    private Resource resource;

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

    public void setModule(String module) {
        this.module = module;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Resource getResource() {
        return resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
