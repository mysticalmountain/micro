package com.andx.micro.user.repository;

import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.User;
import com.andx.micro.user.model.enums.AuthType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by andongxu on 17-2-27.
 */
public class AuthorityRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void save() {
        User user = userRepository.findOne(Long.valueOf(10000079));
        authorityRepository.deleteByUser(user);
        authorityRepository.flush();
        Authority authority = new Authority();
        authority.setAccountNo("bnaddd");
        authority.setAuthType(AuthType.USERNAME);
        authority.setPassword("ppp");
        authority.setUser(user);
        Authority au = authorityRepository.save(authority);
        Assert.assertNotNull(au);
    }
}
