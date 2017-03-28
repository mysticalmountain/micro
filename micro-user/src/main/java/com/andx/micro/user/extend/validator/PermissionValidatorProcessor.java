package com.andx.micro.user.extend.validator;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
public class PermissionValidatorProcessor implements ValidatorProcessor<Request, Response> {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
        httpHeaders.set(HttpHeaders.ACCEPT_ENCODING, "utf-8");
        httpHeaders.set(HttpHeaders.CONTENT_ENCODING, "utf-8");
        httpHeaders.add(HttpHeaders.CONNECTION, "Keep-Alive");
        httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return restTemplate;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Response process(Request request, Object... args) throws ValidatorException {
        ValidatorPermissionDto validatorPermissionDto = new ValidatorPermissionDto();
        validatorPermissionDto.setResourceId(request.getServiceId());
        validatorPermissionDto.setOwnerId("10000000");
        validatorPermissionDto.setServiceId(request.getServiceId());
        validatorPermissionDto.setUserType(ValidatorPermissionDto.UserType.ROLE);
        Object sourceData = request.getData();
        request.setData(validatorPermissionDto);
        try{
            Response response = restTemplate.postForObject("http://localhost:8082/service/validatorPermission", request, Response.class);
            return response;
        } finally {
            request.setData(sourceData);
        }
    }
}
