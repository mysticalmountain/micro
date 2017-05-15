package com.andx.micro.core.flowRate;

import com.andx.micro.api.core.chain.GenericChain;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.flowRate.FlowRate;
import com.andx.micro.api.core.module.flowRate.FlowRateException;
import com.andx.micro.api.core.module.flowRate.FlowRateProcessor;
import com.andx.micro.api.core.module.service.SampleService;

/**
 * Created by andongxu on 16-12-19.
 */
public class FlowRateChain extends GenericChain<Request, Response> {

    private FlowRate<Request, Response> flowRate;
    private FlowRateProcessor<Request, Response> flowRateProcessor;

    public FlowRateChain(FlowRate<Request, Response> flowRate, FlowRateProcessor<Request, Response> flowRateProcessor) {
        this.flowRate = flowRate;
        this.flowRateProcessor = flowRateProcessor;
    }

    public Response chain(Request request, SampleService<Request, Response> processor, Object... args) {
        try {
            Response response  = flowRate.flowRate(request, flowRateProcessor, args);
            if (response.getSuccess() == true) {
                return nextChain.chain(request, processor, args);
            } else {
                throw new FlowRateException("failed");
            }
        } catch (FlowRateException e) {
            return processor.handlerException(request, e, args);
        }
    }
}
