package com.andx.micro.core.idempotent.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by andongxu on 9/20/16.
 */
@Embeddable
public class ReqPK implements Serializable {

    @Column(length = 32, name = "service_id")
    private String serviceId;

    @Column(length = 64, name = "request_id")
    private String requestId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
