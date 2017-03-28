package com.andx.micro.core.flowRate;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.flowRate.FlowRate;
import com.andx.micro.api.core.module.flowRate.FlowRateException;
import com.andx.micro.api.core.module.flowRate.FlowRateProcessor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Semaphore;

/**
 * Created by andongxu on 16-12-20.
 */
public class GenericFlowRate implements FlowRate<Request, Response> {

    private Semaphore semaphore = null;
    private RateLimiter rateLimiter = null;
    private FlowRateConfig flowRateConfig = new FlowRateConfig();

    public void init() throws Throwable {
        semaphore = new Semaphore(flowRateConfig.getMaxConcurrentCount(), true);
        rateLimiter = RateLimiter.create(flowRateConfig.getMaxQpsCount());
    }

    public void destory() throws Throwable {

    }

    public void notify(Request i) {

    }

    public Response flowRate(Request request, FlowRateProcessor<Request, Response> processor, Object... args) throws FlowRateException {
        //switch off
        if (!flowRateConfig.isFlowRateSwitch()) {
            processor.flowRate(request, args);
        }
        if (flowRateConfig.isCcSwitch()) {
            processor.concurrent(request, args);
        }
        return null;
    }

}
