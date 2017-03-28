package com.andx.micro.permission.service.role;

import com.alibaba.fastjson.JSON;
import com.andx.micro.api.core.dto.BaseRequest;
import com.andx.micro.permission.WebTest;
import com.andx.micro.permission.dto.role.EditRoleDto;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andongxu on 17-2-7.
 */
public class EditRoleServiceWebTest extends WebTest {

    @Test
    public void editRole() {
        String res = restTemplate.postForObject("http://127.0.0.1:8080/service/editRole", "{\"data\":{\"name\":\"ceshiyuan7\"},\"requestId\":\"1007\",\"serviceId\":\"1001\"}", String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }

    @Test
    public void tmp() {
        System.out.println(this.getClass() == EditRoleServiceWebTest.class);
        System.out.println(this.getClass().getName());
        BaseRequest<EditRoleDto> request = new BaseRequest<EditRoleDto>();
        EditRoleDto editRoleDto = new EditRoleDto();
        editRoleDto.setName("测试员7");
        request.setData(editRoleDto);
        request.setRequestId("1007");
        request.setServiceId("1001");
        System.out.println(JSON.toJSON(request));
    }
}
