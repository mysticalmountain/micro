package com.andx.micro.core.flowRate;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.flowRate.FlowRateProcessor;

/**
 * Created by andongxu on 16-12-20.
 */
public class GenericFlowRateProcessor implements FlowRateProcessor<Request, Response> {

    public Response flowRate(Request request, Object... args) {
        return null;
    }

    public Response concurrent(Request request, Object... args) {
        return null;
    }
}
