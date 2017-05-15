package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.api.core.module.service.ServiceException;
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
import java.util.Map;
import java.util.Set;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@com.andx.micro.api.core.Service(path = "/services", code = "queryServices", name = "查询服务")
public class QueryServices extends GetSampleService<Response<Set<ServicePermissionDto>>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Response<Set<ServicePermissionDto>> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
        List<Service> services = serviceRepository.findAll();
        Set<ServicePermissionDto> serviceDtos = new HashSet<ServicePermissionDto>();
//        Set<ServiceDto> serviceDtos = new HashSet<>();
        for (Service service : services) {
            ServiceDto serviceDto = new ServiceDto();
            BeanUtils.copyProperties(service, serviceDto);
            Resource resource = service.getResource();
            Permission permission = resource.getPermissions().stream().findFirst().get();
            ServicePermissionDto servicePermissionDto = new ServicePermissionDto();
            BeanUtils.copyProperties(service, servicePermissionDto);
            servicePermissionDto.setResourceId(String.valueOf(resource.getId()));
            servicePermissionDto.setPermissionId(String.valueOf(permission.getId()));
            serviceDtos.add(servicePermissionDto);
//            serviceDtos.add(serviceDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(serviceDtos);
        return response;
    }
}
