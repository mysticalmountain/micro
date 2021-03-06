package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.PermissionRequest;
import com.andx.micro.permission.dto.permission.NewPermissionDto;
import com.andx.micro.permission.model.Operate;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-5-5.
 */
@Component
@Service(path = "/permissions", code = "newPermissions", method = "POST", name = "新建权限")
public class NewPermissions extends PostSampleService<Request<Set<NewPermissionDto>>, Response>{

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ResourceRepository resourceRepository;


    @Override
    protected Response doService(Request<Set<NewPermissionDto>> setRequest) throws ServiceException {
        Iterator<NewPermissionDto> newPermissionDtos = setRequest.getData().iterator();
        while (newPermissionDtos.hasNext()) {
            NewPermissionDto newPermissionDto = newPermissionDtos.next();
            Resource resource = resourceRepository.findOne(Long.valueOf(newPermissionDto.getResourceId()));
            if (resource == null) {
                throw new ServiceException("资源不存在");
            }
            Permission permission = permissionRepository.findByResource(resource);
            if(permission != null) {
                throw new ServiceException("权限已存在:" + resource.getId());
            }
            permission = new Permission();
            permission.setResource(resource);
            permission.setOperate(Operate.READ);
            permissionRepository.save(permission);
        }
        return Constant.RESPONSE_SUCCESS.clone();
    }
}
