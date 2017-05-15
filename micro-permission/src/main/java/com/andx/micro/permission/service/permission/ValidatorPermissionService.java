package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.PermissionRequest;
import com.andx.micro.permission.dto.permission.ValidatorPermissionDto;
import com.andx.micro.permission.model.*;
import com.andx.micro.permission.repository.ChannelRepository;
import com.andx.micro.permission.repository.ResourceRepository;
import com.andx.micro.permission.repository.RoleRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-2-21.
 */
//@Component
//@Service(code = "validatorPermission", name = "验证权限")
public class ValidatorPermissionService implements SampleService<Request<ValidatorPermissionDto>, Response> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Response process(Request<ValidatorPermissionDto> validatorPermissionDtoRequest, Object... args) throws ServiceException {
        ValidatorPermissionDto validatorPermissionDto = validatorPermissionDtoRequest.getData();
//        Resource targetResource = resourceRepository.findOne(Long.valueOf(validatorPermissionDto.getResourceId()));
        com.andx.micro.permission.model.Service service = serviceRepository.findByCode(validatorPermissionDto.getServiceId());
        if (service == null) {
            throw  new ServiceException("service [" + validatorPermissionDto.getServiceId() + "] not exists");
        }
        Long resourceId = service.getResource().getId();
        validatorPermissionDto.setResourceId(String.valueOf(resourceId));
        if (validatorPermissionDto.getUserType() == ValidatorPermissionDto.UserType.ROLE) {
            return processRole(validatorPermissionDto);
        } else if (validatorPermissionDto.getUserType() == ValidatorPermissionDto.UserType.CHANNEL) {
            return processChannel(validatorPermissionDto);
        } else {

        }
        return new Response(Constant.MSG_FAIL, false);
    }
    protected Response processChannel(final ValidatorPermissionDto permissionRequest, Object... args) {
        Channel channel = channelRepository.findByCode(permissionRequest.getOwnerId());
        Set<Permission> permissionSet = channel.getPermissions();
        Long count = permissionSet.stream().filter(p -> p.getResource().getId().equals(permissionRequest.getResourceId())).count();
        Response response = new Response();
//        response.setRequestId(permissionRequest.getRequestId());
//        response.setServiceId(permissionRequest.getServiceId());
        if (count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    protected Response processRole(ValidatorPermissionDto permissionRequest, Object... args) {
        Role role = roleRepository.findOne(Long.valueOf(permissionRequest.getOwnerId()));
        Set<Permission> permissionSet = role.getPermissions();
        Long count = permissionSet.stream().filter(p -> String.valueOf(p.getResource().getId()).equals(permissionRequest.getResourceId())).count();
        Response response = new Response();
//        response.setRequestId(permissionRequest.getRequestId());
//        response.setServiceId(permissionRequest.getServiceId());
        if (count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public Response handlerException(Request<ValidatorPermissionDto> validatorPermissionDtoRequest, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }

}
