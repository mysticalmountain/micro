package com.andx.micro.support.jpa.repository;

import com.andx.micro.support.jpa.model.TmpJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 17-1-4.
 */
@Repository
public interface TmpJpaRepository extends JpaRepository<TmpJpa, Long> {
}
