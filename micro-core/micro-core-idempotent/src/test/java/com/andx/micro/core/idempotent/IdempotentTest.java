package com.andx.micro.core.idempotent;

import com.andx.micro.api.core.dto.BaseRequest;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.idempotent.Idempotent;
import com.andx.micro.api.core.module.idempotent.IdempotentException;
import com.andx.micro.api.core.module.idempotent.IdempotentProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by andongxu on 17-1-7.
 */
public class IdempotentTest extends BaseTest {

    @Autowired
    private Idempotent<Request ,Response> idempotent;
    @Autowired
    private IdempotentProcessor<Request, Response> idempotentProcessor;

    @Test
    public void check() throws IdempotentException {
        BaseRequest request = new BaseRequest();
        request.setRequestId("1001");
        request.setServiceId("123456");
        Response response = idempotent.check(request, idempotentProcessor);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getSuccess());
    }

    @Test
    public void checkStorage() throws IdempotentException {
        BaseRequest request = new BaseRequest();
        request.setRequestId("1001");
        request.setServiceId("123456");
        Response response = idempotent.check(request, idempotentProcessor);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getSuccess());
        idempotent.storage(request, response, idempotentProcessor);
    }

    @Test
    public void serialize() throws IOException {

        Request request = new Request() {
            public String getServiceId() {
                return "1001";
            }

            public String getRequestId() {
                return "123456";
            }

            public Object getData() {
                return null;
            }
        };

//        BaseRequest baseRequest = new BaseRequest();
//
//        ObjectSerializeUtil.serializeObject(baseRequest);
    }
}
