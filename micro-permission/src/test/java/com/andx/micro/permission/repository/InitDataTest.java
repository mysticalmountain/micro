package com.andx.micro.permission.repository;

import com.andx.micro.permission.AppTest;
import com.andx.micro.permission.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 17-2-3.
 */
public class InitDataTest extends AppTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    //step 1
    @Test
    public void initRole() {
        Role admin = new Role();
        admin.setName("管理员");
        roleRepository.save(admin);
    }
    //step 2
    @Test
    public void initService() {
        Resource resource = new Resource();
        resource.setResourceType(ResourceType.SERVICE);
        resourceRepository.save(resource);
        Service service = new Service();
        service.setCode("1001");
        service.setContent("编辑角色");
        service.setResource(resource);
        serviceRepository.save(service);

    }
    //step 3
    @Test
    public void initPermission() {
        Resource resource = resourceRepository.findOne(Long.valueOf(10000000));
        Permission permission = new Permission();
        permission.setResource(resource);
        permission.setOperate(Operate.READ);
        permissionRepository.save(permission);
    }
    //step 4
    @Test
    public void initRolePermission() {
        Role role = roleRepository.findOne(Long.valueOf(10000000));
        Permission permission = permissionRepository.findOne(Long.valueOf(10000000));
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleRepository.save(role);

    }

    @Test
    public void init() {
        Resource resource = new Resource();
        resource.setResourceType(ResourceType.SERVICE);
        resource = resourceRepository.save(resource);
        Service service = new Service();
        service.setCode("login");
        service.setContent("登录");
        service.setResource(resource);
        service = serviceRepository.save(service);

        Role role = roleRepository.findOne(Long.valueOf(10000000));
        Permission p1 = new Permission();
        p1.setResource(resource);
        p1.setOperate(Operate.READ);
        p1 = permissionRepository.save(p1);
        Set<Permission> permissions = permissionRepository.findByRoles(role);
        permissions.add(p1);
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    @Test
    public void init2() {
        Role role = roleRepository.findOne(Long.valueOf(10000000));
        Resource resource = resourceRepository.findOne(Long.valueOf("10000002"));
        Set<Permission> permissions = new HashSet<Permission>();
        Permission p1 = permissionRepository.findOne(Long.valueOf("10000001"));
        p1.setResource(resource);
        p1.setOperate(Operate.READ);
        permissions.add(p1);
        permissionRepository.save(p1);
        role.setPermissions(permissions);
        roleRepository.save(role);

    }

    @Test
    public void init3() {
        Role role = roleRepository.findOne(Long.valueOf(10000000));
        Resource resource = resourceRepository.findOne(Long.valueOf("10000002"));
        Set<Permission> permissions = new HashSet<Permission>();
        Permission p1 = new Permission();
        p1.setResource(resource);
        p1.setOperate(Operate.READ);
        p1 = permissionRepository.save(p1);
        permissions.add(p1);
        role.setPermissions(permissions);
        roleRepository.save(role);

    }
}
