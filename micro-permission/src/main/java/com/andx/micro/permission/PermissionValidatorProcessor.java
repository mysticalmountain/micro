package com.andx.micro.permission;

import com.andx.micro.api.core.dto.BaseResponse;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.permission.dto.PermissionRequest;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.ChannelRepository;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by andongxu on 17-1-18.
 */
//@Component
public class PermissionValidatorProcessor implements ValidatorProcessor<PermissionRequest, Response> {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Response process(PermissionRequest permissionRequest, Object... args) throws ValidatorException {
        if (permissionRequest.getUserType().equals(PermissionRequest.UserType.CHANNEL)) {
            return processChannel(permissionRequest, args);
        } else if (permissionRequest.getUserType().equals(PermissionRequest.UserType.ROLE)) {
            return processRole(permissionRequest, args);
        } else {
            throw new ValidatorException("unknown user type");
        }
    }

    protected Response processChannel(final PermissionRequest permissionRequest, Object... args) {
        Channel channel = channelRepository.findByCode(permissionRequest.getOwnerId());
        Set<Permission> permissionSet = channel.getPermissions();
        Long count = permissionSet.stream().filter(p -> p.getResource().getId().equals(permissionRequest.getResourceId())).count();
        BaseResponse response = new BaseResponse();
        response.setRequestId(permissionRequest.getRequestId());
        response.setServiceId(permissionRequest.getServiceId());
        if (count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    protected Response processRole(PermissionRequest permissionRequest, Object... args) {
        Role role = roleRepository.findOne(Long.valueOf(permissionRequest.getOwnerId()));
        Set<Permission> permissionSet = role.getPermissions();
        Long count = permissionSet.stream().filter(p -> String.valueOf(p.getResource().getId()).equals(permissionRequest.getResourceId())).count();
        BaseResponse response = new BaseResponse();
        response.setRequestId(permissionRequest.getRequestId());
        response.setServiceId(permissionRequest.getServiceId());
        if (count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }
}

