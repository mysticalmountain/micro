package com.andx.micro.permission;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.permission.dto.PermissionRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 17-2-3.
 */
public class PermissionValidatorTest extends AppTest {

    @Autowired
    @Qualifier("permissionValidator")
    private Validator<PermissionRequest, Response> permissionValidator;
    @Autowired
    @Qualifier("permissionValidatorProcessor")
    private ValidatorProcessor<PermissionRequest, Response> permissionValidatorProcessor;

    @Test
    public void success() throws ValidatorException {

        PermissionRequest request = new PermissionRequest();
        request.setUserType(PermissionRequest.UserType.ROLE);
        request.setOwnerId("10000000");
        request.setResourceId("10000000");
        Response response = permissionValidator.validate(request, permissionValidatorProcessor);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getSuccess());
    }

    @Test
    public void testEquals(){
        Long a = Long.valueOf(10000000);
        Long b = Long.valueOf(10000000);
        System.out.println("=====================>" + a.equals(b));
    }
}
