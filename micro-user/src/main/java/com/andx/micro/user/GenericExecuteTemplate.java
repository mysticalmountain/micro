package com.andx.micro.user;

import com.andx.micro.api.core.chain.ModuleChain;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.execute.ExecuteTemplate;
import com.andx.micro.api.core.module.idempotent.Idempotent;
import com.andx.micro.api.core.module.idempotent.IdempotentProcessor;
import com.andx.micro.api.core.module.service.Service;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.idempotent.IdempotentChain;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import com.andx.micro.core.service.GenericService;
import com.andx.micro.core.service.ServiceChain;
import com.andx.micro.core.validator.ValidatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-2-6.
 */
//@Component
public class GenericExecuteTemplate implements ExecuteTemplate<Request, SampleService<Request, Response>, Response> {

    private Log log = Log4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    @Qualifier("genericValidator")
    private Validator<Request, Response> genericValidator;
    @Autowired
    @Qualifier("genericValidatorProcessor")
    private ValidatorProcessor<Request, Response> genericValidatorProcessor;
    @Autowired
    @Qualifier("permissionValidator")
    private Validator<Request, Response> permissionValidator;
    @Autowired
    @Qualifier("permissionValidatorProcessor")
    private ValidatorProcessor<Request, Response> permissionValidatorProcessor;
    @Autowired
    @Qualifier("genericIdempotent")
    private Idempotent<Request, Response> genericIdempotent;
    @Autowired
    @Qualifier("genericIdempotentProcessor")
    private IdempotentProcessor<Request, Response> genericIdempotentProcessor;

    public Response execute(Request request, SampleService<Request, Response> serviceProcessor, Object... args) {
        long begin = System.currentTimeMillis();
        log.info("begin execute service [" + request.getServiceId() + "] requestId:" + request.getRequestId());
        try {
            ModuleChain<Request, Response> validatorChain = new ValidatorChain<Request, Response>(genericValidator, genericValidatorProcessor);
            ModuleChain<Request, Response> idempotentChain = new IdempotentChain(genericIdempotent, genericIdempotentProcessor);
            ModuleChain<Request, Response> validatorPermissionChain = new ValidatorChain<Request, Response>(permissionValidator, permissionValidatorProcessor);
            Service<Request, Response> service = new GenericService();
            ModuleChain<Request, Response> serviceChain = new ServiceChain(service);
            idempotentChain.initChain(validatorChain);
            validatorChain.initChain(validatorPermissionChain);
            validatorPermissionChain.initChain(serviceChain);
            return idempotentChain.chain(request, serviceProcessor, args);
        } finally {
            long end = System.currentTimeMillis();
            log.info("end execute service [" + request.getServiceId() + "] requestId:" + request.getRequestId() + ", time mills:" + (end - begin) + " ms");
        }
    }
}

