package com.andx.micro.support.web.dispatch;


import com.andx.micro.api.core.module.service.ServiceProcessor;

/**
 * Created by andongxu on 9/11/16.
 */
public interface ServiceVisitor<D, O> {

    public O visit(ServiceProcessor<D, O> service, ServiceData<D> serviceData) throws DispatchException;
}
