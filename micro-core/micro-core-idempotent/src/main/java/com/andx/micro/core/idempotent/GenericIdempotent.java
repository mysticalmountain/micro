package com.andx.micro.core.idempotent;

import com.andx.micro.api.core.dto.BaseResponse;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.idempotent.Idempotent;
import com.andx.micro.api.core.module.idempotent.IdempotentException;
import com.andx.micro.api.core.module.idempotent.IdempotentProcessor;
import com.andx.micro.api.core.module.idempotent.ReqRsp;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.idempotent.model.ReqRspFlow;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-12-20.
 */
@Component
public class GenericIdempotent implements Idempotent<Request, Response> {

    private Log log = Log4jLogFactory.getLogFactory().getLog(GenericIdempotent.class);

    /**
     * 弭等开关
     */
    boolean idempotentSwitch = false;
    /**
     * 持久化开关
     */
    boolean storageSwitch = false;

    public void init() throws Throwable {

    }

    public void destory() throws Throwable {

    }

    public void notify(Request request) {

    }

    public Response check(final Request request, IdempotentProcessor<Request, Response> processor, Object... args) throws IdempotentException {
        long begin = System.currentTimeMillis();
        try{
            Response response = processor.get(request);
            return response;
        } finally {
            long end = System.currentTimeMillis();
            log.info("execute module [idempotent -> check] time mills:" + (end - begin) + " ms");
        }
    }

    public Response storage(Request request, Response response, IdempotentProcessor<Request, Response> processor, Object... args) throws IdempotentException {
        long begin = System.currentTimeMillis();
        try {
            processor.storage(request, response, args);
            return response;
        }finally {
            long end = System.currentTimeMillis();
            log.info("execute module [idempotent -> storage] time mills:" + (end - begin) + " ms");
        }
    }
}
