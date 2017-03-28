package com.andx.micro.core.idempotent.repository;

import com.andx.micro.core.idempotent.model.ReqPK;
import com.andx.micro.core.idempotent.model.ReqRspFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 9/20/16.
 */
@Repository
public interface ReqRspFlowRepository extends JpaRepository<ReqRspFlow, ReqPK> {


}
