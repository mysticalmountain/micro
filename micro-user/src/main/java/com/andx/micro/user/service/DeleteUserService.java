package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.IdDto;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.ProfileRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

/**
 * Created by andongxu on 17-2-23.
 */
@Component
@Service(code = "deleteUser", name = "删除用户")
public class DeleteUserService implements ServiceProcessor<Request<IdDto>, Response> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public Response process(Request<IdDto> idDtoRequest, Object... args) throws ServiceException {
        IdDto idDto = idDtoRequest.getData();
        User user = userRepository.findOne(idDto.getId());
        profileRepository.deleteByUser(user);
        authorityRepository.deleteByUser(user);
        userRepository.delete(user);
        return Constant.RESPONSE_SUCCESS.clone();
    }

    @Override
    public Response handlerException(Request<IdDto> idDtoRequest, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }
}
