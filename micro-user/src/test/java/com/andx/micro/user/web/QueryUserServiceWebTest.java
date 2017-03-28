package com.andx.micro.user.web;

import com.alibaba.fastjson.JSON;
import com.andx.micro.api.core.dto.BaseRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andongxu on 17-2-7.
 */
public class QueryUserServiceWebTest extends WebTest {

    @Test
    public void queryUser() {
//        String res = restTemplate.postForObject("http://127.0.0.1:8081/service/queryUser", "{\"data\":{\"name\":\"ceshiyuan7\"},\"requestId\":\"1007\",\"serviceId\":\"1001\"}", String.class);
        String res = restTemplate.postForObject("http://localhost:8081/service/queryUser?callback=jQuery311045342622860334814_1487250971516&_=1487250971517", "{\"data\":{\"name\":\"ceshiyuan7\"},\"requestId\":\"1007\",\"serviceId\":\"1001\"}", String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }

//    @Test
//    public void tmp() {
//        System.out.println(this.getClass() == EditRoleServiceWebTest.class);
//        System.out.println(this.getClass().getName());
//        BaseRequest<EditRoleDto> request = new BaseRequest<EditRoleDto>();
//        EditRoleDto editRoleDto = new EditRoleDto();
//        editRoleDto.setName("测试员7");
//        request.setData(editRoleDto);
//        request.setRequestId("1007");
//        request.setServiceId("1001");
//        System.out.println(JSON.toJSON(request));
//    }
}
