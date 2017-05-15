package com.andx.micro.user.test;

import com.alibaba.fastjson.JSON;
import com.andx.micro.api.core.dto.Response;
import org.junit.Test;

/**
 * Created by andongxu on 17-4-14.
 */
public class JsonTest {

    @Test
    public void json2string() {
//        Response response = new Response("123", "失败", false);
//        String str = JSON.toJSONString(response);
//        System.out.println(str);

        String s = "aaaaabbbb=";

        System.out.println(s.charAt(s.length() - 1) == '=');
        if (s.charAt(s.length() - 1) == '=') {
            System.out.println(s.substring(0, s.length() - 1));
        }
    }
}
