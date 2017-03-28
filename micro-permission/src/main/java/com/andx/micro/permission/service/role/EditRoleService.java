package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.BaseRequest;
import com.andx.micro.api.core.dto.BaseResponse;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.role.EditRoleDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-2-3.
 */
@Component
@Service(code = "editRole", name = "编辑角色")
public class EditRoleService implements ServiceProcessor<Request<EditRoleDto>, Response> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Response process(Request<EditRoleDto> request, Object... args) throws ServiceException {
        EditRoleDto editRoleDto = request.getData();
        Role role = null;
        //judge operate edit or new
        if (editRoleDto.getId() == null) { // new
            role = new Role();
            BeanUtils.copyProperties(editRoleDto, role);
            Iterator<String> permissionIds = editRoleDto.getPermissionIds().iterator();
            Set<Permission> permissions = new HashSet<>();
            while (permissionIds.hasNext()) {
                String permissionId = permissionIds.next();
                Permission permission = permissionRepository.findOne(Long.valueOf(permissionId));
                permissions.add(permission);
            }
            role.setPermissions(permissions);
            role = roleRepository.save(role);
        } else {                          // edit
            role = roleRepository.findOne(editRoleDto.getId());
            BeanUtils.copyProperties(editRoleDto, role);
            Iterator<String> permissionIds = editRoleDto.getPermissionIds().iterator();
            Set<Permission> permissions = new HashSet<>();
            while (permissionIds.hasNext()) {
                String permissionId = permissionIds.next();
                Permission permission = permissionRepository.findOne(Long.valueOf(permissionId));
                permissions.add(permission);
            }
            role.setPermissions(permissions);
            role = roleRepository.save(role);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }

    @Override
    public Response handlerException(Request<EditRoleDto> request, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }

}
