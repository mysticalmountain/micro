package com.andx.micro.api.core.module.service;


import com.andx.micro.api.core.Processor;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 16-12-30.
 */
public interface ServiceProcessor<I, O> extends Processor<I, O> {

    O process(I i, Object... args) throws ServiceException;

    O handlerException(I i, Exception e, Object ... args);

}
