package com.andx.micro.user.validator;

import com.andx.micro.api.core.dto.BaseRequest;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.user.BaseTest;
import com.andx.micro.user.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 17-1-17.
 */
public class ValidatorTest extends BaseTest {

    @Autowired
    private Validator validator;

    @Autowired
    private ValidatorProcessor validatorProcessor;

    @Test
    public void success() throws ValidatorException {
        UserDto userDto = new UserDto();
        Request<UserDto> request = new Request<UserDto>();
        request.setData(userDto);
        Response response = validator.validate(request, validatorProcessor);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void fail() throws ValidatorException {
        BaseRequest request = new BaseRequest();
        request.setServiceId("1001");
        request.setRequestId("123456");
        Response response = validator.validate(request, validatorProcessor);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getSuccess());
    }
}
