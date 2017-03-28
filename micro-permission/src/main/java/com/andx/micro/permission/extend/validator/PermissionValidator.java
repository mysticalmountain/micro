package com.andx.micro.permission.extend.validator;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
public class PermissionValidator implements Validator<Request,Response> {
    @Override
    public void init() throws Throwable {

    }

    @Override
    public void destory() throws Throwable {

    }

    @Override
    public void notify(Request request) {

    }

    @Override
    public Response validate(Request request, ValidatorProcessor<Request, Response> validatorProcessor, Object... args) throws ValidatorException {
        Response response = validatorProcessor.process(request, args);
        if (response.getSuccess() == false) {
            throw new ValidatorException("无操作权限");
        } else {
            return response;
        }
    }
}
