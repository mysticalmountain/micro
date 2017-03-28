package com.andx.micro.api.core.chain;

import com.andx.micro.api.core.dto.BaseRequest;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceProcessor;

/**
 * Created by andongxu on 16-12-17.
 */
public interface ModuleChain<I, O> {

    void initChain(ModuleChain<I, O> chain);

    O chain(I i, ServiceProcessor<I, O> processor, Object... args);

}
