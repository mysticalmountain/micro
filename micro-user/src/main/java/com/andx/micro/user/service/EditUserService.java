package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.dto.RolebakDto;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.enums.AuthType;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.ProfileRepository;
import com.andx.micro.user.repository.RolebakRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-2-17.
 */
@Component
@Service(code = "editUser", name = "编辑用户")
public class EditUserService implements ServiceProcessor<Request<UserDto>, Response> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RolebakRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public Response process(Request<UserDto> userDtoRequest, Object... args) throws ServiceException {
        UserDto userDto = userDtoRequest.getData();
        User user = null;
        if (userDto != null && userDto.getId() != null) {
            user = userRepository.findOne(userDto.getId());
        }
        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(userDto, user);
            user = userRepository.save(user);
        } else {
            BeanUtils.copyProperties(userDto, user);
            user = userRepository.save(user);
        }
        if (userDto.getProfileDto() != null) {
            Profile profile = user.getProfile();
            if (profile == null) {
                profile = new Profile();
            }
            BeanUtils.copyProperties(userDto.getProfileDto(), profile);
            profile.setUser(user);
            profileRepository.save(profile);
        }
        if (userDto.getAuthorityDto() != null) {
            authorityRepository.deleteByUser(user);
            authorityRepository.flush();
            Authority authority = new Authority();
            BeanUtils.copyProperties(userDto.getAuthorityDto(), authority);
            authority.setAuthType(AuthType.USERNAME);
            authority.setUser(user);
            authorityRepository.saveAndFlush(authority);
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authority);
            user.setAuthorities(authorities);
        }
        if (userDto.getRoleDtos() != null) {
            roleRepository.deleteByUsers(user);
            Iterator<RolebakDto> userRoleDtoIterable = userDto.getRoleDtos().iterator();
            Set<Rolebak> rolebakSet = new HashSet<Rolebak>();
            while (userRoleDtoIterable.hasNext()) {
                RolebakDto rolebakDto = userRoleDtoIterable.next();
                Rolebak rolebak = roleRepository.findOne(rolebakDto.getRoleId());
                if (rolebak == null) {
                    rolebak = new Rolebak();
                    BeanUtils.copyProperties(rolebakDto, rolebak);
                    roleRepository.save(rolebak);
                }
                rolebakSet.add(rolebak);
            }
            user.setRoles(rolebakSet);
            userRepository.save(user);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }

    @Override
    public Response handlerException(Request<UserDto> userDtoRequest, Exception e, Object... args) {
        Response response = new Response();
        response.setErrorMessage(e.getMessage());
        return response;
    }
}
