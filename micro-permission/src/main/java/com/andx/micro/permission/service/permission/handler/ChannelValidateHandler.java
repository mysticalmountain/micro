package com.andx.micro.permission.service.permission.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.core.validator.PermissionValidatorDto;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.repository.ChannelRepository;
import com.andx.micro.permission.repository.RoleRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by andongxu on 17-5-9.
 */
@Component
public class ChannelValidateHandler extends GenericServiceHandler<PermissionValidatorDto, Boolean> {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Boolean doHandle(PermissionValidatorDto permissionValidatorDto, ServiceContext context) throws HandlerException {
        Channel channel = channelRepository.findByCode(permissionValidatorDto.getOwnerId());
        Set<Permission> permissionSet = channel.getPermissions();
        Long resourceId = serviceRepository.findByCode(permissionValidatorDto.getServiceCode()).getResource().getId();
        Long count = permissionSet.stream().filter(p -> p.getResource().getId().equals(resourceId)).count();
        return count > 0;
    }

    @Override
    public Boolean support(PermissionValidatorDto permissionValidatorDto, ServiceContext context) {
        if (permissionValidatorDto.getOwnerId() == null) {
            return false;
        }
        Channel channel = channelRepository.findOne(Long.valueOf(permissionValidatorDto.getOwnerId()));
        return channel != null;
    }
}
