package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.IdDto;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.AuthorityDto;
import com.andx.micro.user.dto.ProfileDto;
import com.andx.micro.user.dto.RolebakDto;
import com.andx.micro.user.dto.UserDetailDto;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-2-24.
 */
@Component
@Service(code = "queryUserDetail", name = "查询用户")
public class QueryUserDetailService implements ServiceProcessor<Request<IdDto>, Response<UserDetailDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<UserDetailDto> process(Request<IdDto> idDtoRequest, Object... args) throws ServiceException {
        User user = userRepository.findOne(idDtoRequest.getData().getId());
        UserDetailDto userDtailDto = new UserDetailDto();
        BeanUtils.copyProperties(user, userDtailDto);
        Profile profile = user.getProfile();
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto);
        Iterator<Authority> authorityIterator = user.getAuthorities().iterator();
        Set<AuthorityDto> authorityDtoSet = new HashSet<>();
        while (authorityIterator.hasNext()) {
            AuthorityDto authorityDto = new AuthorityDto();
            Authority authority = authorityIterator.next();
            BeanUtils.copyProperties(authority, authorityDto);
            authorityDtoSet.add(authorityDto);
        }
        Iterator<Rolebak> rolebaks = user.getRoles().iterator();
        Set<RolebakDto> rolebakDtos = new HashSet<RolebakDto>();
        while(rolebaks.hasNext()) {
            Rolebak rolebak = rolebaks.next();
            RolebakDto rolebakDto = new RolebakDto();
            BeanUtils.copyProperties(rolebak, rolebakDto);
            rolebakDtos.add(rolebakDto);
        }
        userDtailDto.setProfileDto(profileDto);
        userDtailDto.setAuthorityDtos(authorityDtoSet);
        userDtailDto.setRolebakDtos(rolebakDtos);
        Response response = Constant.RESPONSE_SUCCESS.clone();
        response.setData(userDtailDto);
        return response;
    }

    @Override
    public Response<UserDetailDto> handlerException(Request<IdDto> idDtoRequest, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }
}
