package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 17-2-16.
 */
@Component
@Service(code = "queryUser", name = "查询用户")
public class QueryUserService implements ServiceProcessor<Request, Response<Set<UserDto>>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<Set<UserDto>> process(Request request, Object... args) throws ServiceException {
        List<User> users = userRepository.findAll();

        Set<UserDto> userDtos = new HashSet<UserDto>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }
        Response<Set<UserDto>> response = new Response<Set<UserDto>>();
        response.setData(userDtos);
        response.setSuccess(true);
        return response;
    }

    @Override
    public Response<Set<UserDto>> handlerException(Request request, Exception e, Object... args) {
        Response response = new Response();
        response.setErrorCode(e.getMessage());
        return response;
    }
}
