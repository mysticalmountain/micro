package com.andx.micro.support.web.dispatch;


import com.andx.micro.api.core.exception.UnifiedException;

/**
 * Created by andongxu on 9/11/16.
 */
public interface ServiceData<D> {

    public D get() throws DispatchException;


}
