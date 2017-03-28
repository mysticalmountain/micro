package com.andx.micro.permission.extend.validator;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.permission.dto.permission.ValidatorPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
public class PermissionValidatorProcessor implements ValidatorProcessor<Request, Response> {

    @Autowired
    @Qualifier("validatorPermissionService")
    private ServiceProcessor<Request<ValidatorPermissionDto>, Response> validatorPermissionService;

    @Override
    public Response process(Request request, Object... args) throws ValidatorException {
        ValidatorPermissionDto validatorPermissionDto = new ValidatorPermissionDto();
        validatorPermissionDto.setResourceId(request.getServiceId());
        validatorPermissionDto.setOwnerId("10000000");
        validatorPermissionDto.setServiceId(request.getServiceId());
        validatorPermissionDto.setUserType(ValidatorPermissionDto.UserType.ROLE);
        Object sourceData = request.getData();
        request.setData(validatorPermissionDto);
        try {
            return validatorPermissionService.process(request);
        } catch (ServiceException e) {
            throw new ValidatorException(e.getMessage());
        } finally {
            request.setData(sourceData);
        }
    }
}
