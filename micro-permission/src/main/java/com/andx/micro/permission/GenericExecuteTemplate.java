package com.andx.micro.permission;

import com.andx.micro.api.core.chain.ModuleChain;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.execute.ExecuteTemplate;
import com.andx.micro.api.core.module.idempotent.Idempotent;
import com.andx.micro.api.core.module.idempotent.IdempotentProcessor;
import com.andx.micro.api.core.module.service.Service;
import com.andx.micro.api.core.module.service.ServiceProcessor;
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
@Component
public class GenericExecuteTemplate implements ExecuteTemplate<Request, ServiceProcessor<Request, Response>, Response> {

    private Log log = Log4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    @Qualifier("genericValidator")
    private Validator<Request, Response> genericValidator;
    @Autowired
    @Qualifier("permissionValidator")
    private Validator<Request, Response> permissionValidator;
    @Autowired
    @Qualifier("genericValidatorProcessor")
    private ValidatorProcessor<Request, Response> genericValidatorProcessor;
    @Autowired
    @Qualifier("permissionValidatorProcessor")
    private ValidatorProcessor<Request, Response> permissionValidatorProcessor;

    @Autowired
    @Qualifier("genericIdempotent")
    private Idempotent<Request, Response> genericIdempotent;
    @Autowired
    @Qualifier("genericIdempotentProcessor")
    private IdempotentProcessor<Request, Response> genericIdempotentProcessor;

    public Response execute(Request request, ServiceProcessor<Request, Response> serviceProcessor, Object... args) {
        long begin = System.currentTimeMillis();
        log.info("begin execute service [" + request.getServiceId() + "] requestId:" + request.getRequestId());
        try {
            ModuleChain<Request, Response> validatorChain = new ValidatorChain<Request, Response>(genericValidator, genericValidatorProcessor);
            ModuleChain<Request, Response> permissionvalidatorChain = new ValidatorChain<Request, Response>(permissionValidator, permissionValidatorProcessor);
            ModuleChain<Request, Response> idempotentChain = new IdempotentChain(genericIdempotent, genericIdempotentProcessor);
            Service<Request, Response> service = new GenericService();
            ModuleChain<Request, Response> serviceChain = new ServiceChain(service);
            idempotentChain.initChain(validatorChain);
            validatorChain.initChain(permissionvalidatorChain);
            permissionvalidatorChain.initChain(serviceChain);
            return idempotentChain.chain(request, serviceProcessor, args);
        } finally {
            long end = System.currentTimeMillis();
            log.info("end execute service [" + request.getServiceId() + "] requestId:" + request.getRequestId() + ", time mills:" + (end - begin) + " ms");
        }
    }
}

