package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.permission.PermissionDto;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.dto.role.QueryRoleDto;
import com.andx.micro.permission.dto.role.RoleDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 17-2-20.
 */
@Component
@Service(code = "queryRole", name = "查询角色")
public class QueryRoleService implements ServiceProcessor<Request<QueryRoleDto>, Response<Set<RoleDto>>> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Response<Set<RoleDto>> process(Request<QueryRoleDto> req, Object... args) throws ServiceException {
        List<Role> roles = null;
        if (req.getData() != null) {
            Role queryRole = new Role();
            BeanUtils.copyProperties(req.getData(), queryRole);
            Example<Role> example = Example.of(queryRole);
            roles = roleRepository.findAll(example);
        } else {
            roles = roleRepository.findAll();
        }
        Set<RoleDto> roleDtos = new HashSet<RoleDto>();
        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            Set<Permission> permissions = role.getPermissions();
            Iterator<Permission> permissionIterator = permissions.iterator();
            Set<PermissionDto> permissionDtos = new HashSet<PermissionDto>();
            while (permissionIterator.hasNext()) {
                Permission permission = permissionIterator.next();
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);
                Resource resource = permission.getResource();
                ResourceDto resourceDto = new ResourceDto();
                BeanUtils.copyProperties(resource, resourceDto);
                permissionDto.setResource(resourceDto);
                permissionDtos.add(permissionDto);
            }
            roleDto.setPermissions(permissionDtos);
            roleDtos.add(roleDto);
        }
        Response<Set<RoleDto>> response = new Response<Set<RoleDto>>(Constant.MSG_SUCCESS, true);
        response.setData(roleDtos);
        return response;
    }

    @Override
    public Response<Set<RoleDto>> handlerException(Request<QueryRoleDto> queryRoleDtoRequest, Exception e, Object... args) {
        Response response = new Response(e.getMessage(), false);
        return response;
    }
}
