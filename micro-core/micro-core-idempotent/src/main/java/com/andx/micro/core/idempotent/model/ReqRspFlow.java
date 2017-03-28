package com.andx.micro.core.idempotent.model;


import com.andx.micro.support.jpa.model.Entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

/**
 * Created by andongxu on 9/20/16.
 */
@Table(name = "Req_Rsp_Flow")
@javax.persistence.Entity
public class ReqRspFlow extends Entity {

    @EmbeddedId
    private ReqPK id;

    @Column(length = 1024 * 2)
    private byte [] reqMsg;

    @Column(length = 1024 * 2)
    private byte [] rspMsg;

    public ReqPK getId() {
        return id;
    }

    public void setId(ReqPK id) {
        this.id = id;
    }

    public byte[] getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(byte[] reqMsg) {
        this.reqMsg = reqMsg;
    }

    public byte[] getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(byte[] rspMsg) {
        this.rspMsg = rspMsg;
    }
}
