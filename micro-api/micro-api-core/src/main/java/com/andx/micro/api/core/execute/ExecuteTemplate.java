package com.andx.micro.api.core.execute;

/**
 * Created by andongxu on 17-1-4.
 */
public interface ExecuteTemplate<I, S, O> {

    O execute(I i, S s, Object ... args);

}
