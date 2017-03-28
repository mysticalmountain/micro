package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.IdDto;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.andx.micro.core.util.Constant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by andongxu on 17-2-3.
 */
@Component
@Service(code = "deleteRole", name = "删除角色")
public class DeleteRoleService implements ServiceProcessor<Request<IdDto>, Response> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Response process(Request<IdDto> idDto, Object... args) throws ServiceException {
        roleRepository.delete(idDto.getData().getId());
        return Constant.RESPONSE_SUCCESS.clone();
    }

    @Override
    public Response handlerException(Request<IdDto> idDtoRequest, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }
}
