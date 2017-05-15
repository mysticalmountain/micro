package com.andx.micro.core.idempotent;

import com.andx.micro.api.core.chain.GenericChain;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.idempotent.Idempotent;
import com.andx.micro.api.core.module.idempotent.IdempotentException;
import com.andx.micro.api.core.module.idempotent.IdempotentProcessor;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;

/**
 * Created by andongxu on 16-12-19.
 */
public class IdempotentChain extends GenericChain<Request, Response> {

    private Idempotent<Request, Response> idempotent;
    private IdempotentProcessor<Request, Response> idempotentProcessor;

    public IdempotentChain(Idempotent<Request, Response> idempotent, IdempotentProcessor<Request, Response> idempotentProcessor) {
        this.idempotent = idempotent;
        this.idempotentProcessor = idempotentProcessor;
    }


    public Response chain(Request request, SampleService<Request, Response> processor, Object... args) {
        Response response = null;
        boolean isStorage = false;
        try {
            response = idempotent.check(request, idempotentProcessor, args);
            if (response != null) {
                return response;
            } else {
                isStorage = true;
                response =  nextChain.chain(request, processor, args);
                return response;
            }
        } catch (IdempotentException e) {
            response = processor.handlerException(request, e, args);
            return response;
        } finally {
            if (isStorage) {
                try {
                    idempotent.storage(request, response, idempotentProcessor, args);
                } catch (Exception e) {
                }
            }
        }
    }
}
