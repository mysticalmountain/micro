package com.andx.micro.core.idempotent;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.module.idempotent.IdempotentException;
import com.andx.micro.api.core.module.idempotent.IdempotentProcessor;
import com.andx.micro.core.idempotent.model.ReqPK;
import com.andx.micro.core.idempotent.model.ReqRspFlow;
import com.andx.micro.core.idempotent.repository.ReqRspFlowRepository;
import com.andx.micro.core.util.ObjectSerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-12-20.
 */
@Component
public class GenericIdempotentProcessor implements IdempotentProcessor<Request,Response> {

    @Autowired
    private ReqRspFlowRepository reqRspFlowRepository;

    public Response<ReqRspFlow> get(Request request, Object... args) throws IdempotentException {
        ReqPK reqPK = new ReqPK();
        reqPK.setRequestId(request.getRequestId());
        reqPK.setServiceId(request.getServiceId());
        ReqRspFlow reqRspFlow = reqRspFlowRepository.findOne(reqPK);
        if (reqRspFlow == null) {
           return null;
        }
        try {
            Response response = ObjectSerializeUtil.deserializeObject(reqRspFlow.getRspMsg(), Response.class);
            return response;
        } catch (Exception e) {
            throw new IdempotentException(ExceptionType.UNKNOWN, "serialize response object error", e);
        }
    }

    public void storage(Request request, Response response, Object... args) throws IdempotentException {
        ReqPK reqPK = new ReqPK();
        reqPK.setRequestId(request.getRequestId());
        reqPK.setServiceId(request.getServiceId());
        ReqRspFlow reqRspFlow = new ReqRspFlow();
        reqRspFlow.setId(reqPK);
        try {
            byte [] requestByte = ObjectSerializeUtil.serializeObject(request);
            reqRspFlow.setReqMsg(requestByte);
            byte [] responseByte = ObjectSerializeUtil.serializeObject(response);
            reqRspFlow.setRspMsg(responseByte);
            reqRspFlowRepository.save(reqRspFlow);
        } catch (Exception e) {
            throw new IdempotentException(ExceptionType.UNKNOWN, "serialize response object error", e);
        }

    }
}
