package com.andx.micro.permission;


import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.permission.dto.PermissionRequest;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-1-17.
 */
//@Component
public class PermissionValidator implements Validator<PermissionRequest, Response> {
    public void init() throws Throwable {

    }

    public void destory() throws Throwable {

    }

    public void notify(PermissionRequest permissionRequest) {

    }

    public Response validate(PermissionRequest request, ValidatorProcessor<PermissionRequest, Response> validatorProcessor, Object... args) throws ValidatorException {
        return validatorProcessor.process(request, args);
    }
}
