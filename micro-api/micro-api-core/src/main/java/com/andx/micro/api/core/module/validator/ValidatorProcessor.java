package com.andx.micro.api.core.module.validator;

import com.andx.micro.api.core.Processor;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 17-1-6.
 */
public interface ValidatorProcessor<I extends Request, O extends Response> extends Processor<I, O> {

    O process(I i, Object ... args) throws ValidatorException;
}
