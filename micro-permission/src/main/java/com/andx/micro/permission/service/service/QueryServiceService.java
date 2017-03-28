package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.dto.service.ServicePermissionDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 17-2-23.
 */
@Component
@com.andx.micro.api.core.Service(code = "queryService", name = "查询服务")
public class QueryServiceService implements ServiceProcessor<Request, Response<Set<ServicePermissionDto>>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Response<Set<ServicePermissionDto>> process(Request request, Object... args) throws ServiceException {
        List<Service> services = serviceRepository.findAll();
        Set<ServicePermissionDto> serviceDtos = new HashSet<ServicePermissionDto>();
        for (Service service : services) {
            Resource resource = service.getResource();
            Permission permission = resource.getPermissions().stream().findFirst().get();
            ServicePermissionDto servicePermissionDto = new ServicePermissionDto();
            BeanUtils.copyProperties(service, servicePermissionDto);
            servicePermissionDto.setResourceId(String.valueOf(resource.getId()));
            servicePermissionDto.setPermissionId(String.valueOf(permission.getId()));
            serviceDtos.add(servicePermissionDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(serviceDtos);
        return response;
    }

    @Override
    public Response<Set<ServicePermissionDto>> handlerException(Request request, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }
}
