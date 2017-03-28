package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.LoginDto;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-2-27.
 */
@Component
@Service(code = "login", name = "用户登录")
public class LoginService implements ServiceProcessor<Request<LoginDto>, Response> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Response process(Request<LoginDto> loginDtoRequest, Object... args) throws ServiceException {
        LoginDto loginDto = loginDtoRequest.getData();
        Authority authority = authorityRepository.findByAccountNo(loginDto.getUsername());
        if (authority != null) {
            if (authority.getPassword().equals(loginDto.getPassword())) {
                return Constant.RESPONSE_SUCCESS.clone();
            } else {
                throw new ServiceException("登录密码不正确");
            }
        } else {
            throw new ServiceException("登录帐号不存在");
        }
    }

    @Override
    public Response handlerException(Request<LoginDto> loginDtoRequest, Exception e, Object... args) {
        return new Response(e.getMessage(), false);
    }
}
