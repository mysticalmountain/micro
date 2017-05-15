package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    public Service findByCode(String code);

    public Service findByResource(Resource resource);

}
