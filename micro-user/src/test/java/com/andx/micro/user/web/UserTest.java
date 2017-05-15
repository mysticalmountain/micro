package com.andx.micro.user.web;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by andongxu on 17-4-14.
 */
public class UserTest extends WebTest {

    @Test
    public void users() throws URISyntaxException {
        URI uri = new URI("http://localhost:8081/service/users");
        String res = restTemplate.getForObject(uri, String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }
}
