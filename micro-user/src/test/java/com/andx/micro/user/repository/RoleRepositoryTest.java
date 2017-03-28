package com.andx.micro.user.repository;

import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 17-2-21.
 */
public class RoleRepositoryTest extends BaseTest {

    @Autowired
    private RolebakRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        Rolebak rolebak = new Rolebak();
        roleRepository.save(rolebak);
    }

    @Test
    public void save2() {
        User user = userRepository.findOne(Long.valueOf("10000042"));
        Rolebak rolebak = new Rolebak();
        rolebak.setName("admin");
        rolebak.setRoleId(Long.valueOf(1001));
        rolebak = roleRepository.save(rolebak);
        Set<Rolebak> rolebaks = new HashSet<Rolebak>();
        rolebaks.add(rolebak);
        user.setRoles(rolebaks);
    }

    @Test
    public void delete1() {
//        roleRepository.de／
    }
}
