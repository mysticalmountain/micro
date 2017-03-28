package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.chain.ModuleChain;
import com.andx.micro.api.core.dto.BaseRequest;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.execute.ExecuteTemplate;
import com.andx.micro.api.core.module.service.Service;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.core.service.GenericService;
import com.andx.micro.core.service.ServiceChain;
import com.andx.micro.core.validator.ValidatorChain;
import com.andx.micro.permission.AppTest;
import com.andx.micro.permission.dto.role.EditRoleDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 17-2-3.
 */
public class EditRoleServiceTest extends AppTest {

    @Autowired
    @Qualifier("editRoleService")
    private ServiceProcessor<Request, Response> editRoleService;
    @Autowired
    @Qualifier("genericValidator")
    private Validator<Request, Response> genericValidator;
    @Autowired
    @Qualifier("genericValidatorProcessor")
    private ValidatorProcessor<Request, Response> genericValidatorProcessor;
    @Autowired
    private ExecuteTemplate<Request, ServiceProcessor, Response> execute;

    @Test
    public void editRole1() throws ServiceException {
        BaseRequest<EditRoleDto> request = new BaseRequest<EditRoleDto>();
        EditRoleDto editRoleDto = new EditRoleDto();
        editRoleDto.setName("操作员");
        request.setData(editRoleDto);
        Response response = editRoleService.process(request);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getSuccess());
    }

    @Test
    public void editRole2() {
        ModuleChain<Request, Response> validatorChain = new ValidatorChain(genericValidator, genericValidatorProcessor);
        Service<Request, Response> service = new GenericService();
        ModuleChain<Request, Response> serviceChain = new ServiceChain(service);
        validatorChain.initChain(serviceChain);
        BaseRequest<EditRoleDto> request = new BaseRequest<EditRoleDto>();
        EditRoleDto editRoleDto = new EditRoleDto();
        editRoleDto.setName("审核员");
        request.setData(editRoleDto);
        request.setRequestId("123");
        request.setServiceId("123");
        Response response = validatorChain.chain(request, editRoleService);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getSuccess());
    }

    @Test
    public void editRole3() {
        BaseRequest<EditRoleDto> request = new BaseRequest<EditRoleDto>();
        EditRoleDto editRoleDto = new EditRoleDto();
        editRoleDto.setName("测试员7");
        request.setData(editRoleDto);
        request.setRequestId("1007");
        request.setServiceId("1001");
        Response response = execute.execute(request, editRoleService);
        Assert.assertTrue(response.getSuccess());
    }

}
