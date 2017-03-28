package com.andx.micro.user.repository;

import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-2-23.
 */
public class UserRepositoryTest extends BaseTest {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void del() {

        User user = userRepository.findOne(Long.valueOf(10000003));
        Profile profile = user.getProfile();
        if (profile != null) {
            profileRepository.delete(profile);
        }
        Set<Authority> authoritySet = user.getAuthorities();

        Iterator<Authority> authorityIterable = authoritySet.iterator();
        while (authorityIterable.hasNext()) {
            Authority authority = authorityIterable.next();
            authorityRepository.delete(authority);
        }
        userRepository.delete(user);


    }

    @Test
    public void delProfile() {
        User user = userRepository.findOne(Long.valueOf(10000008));
        profileRepository.deleteByUser(user);
        authorityRepository.deleteByUser(user);
        userRepository.delete(user);
//        profileRepository.deleteByUserId(Long.valueOf(10000003));
    }
}
