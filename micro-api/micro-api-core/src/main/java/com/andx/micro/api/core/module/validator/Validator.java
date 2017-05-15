package com.andx.micro.api.core.module.validator;

import com.andx.micro.api.core.Module;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 17-1-6.
 */
public interface Validator<I, O> extends Module<I, O> {

    O validate(I i, ValidatorProcessor<I, O> validatorProcessor, Object... args) throws ValidatorException;
}
