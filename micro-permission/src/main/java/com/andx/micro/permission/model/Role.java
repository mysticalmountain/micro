package com.andx.micro.permission.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;
import com.andx.micro.support.jpa.model.Entity;

/**
 * Created by andongxu on 16-8-25.
 */
@javax.persistence.Entity
@Table
public class Role extends Entity {

    @Id
    @GenericGenerator(name = "roleId", strategy = "enhanced-table",
            parameters = {
                    @Parameter(name = "table_name", value = "seq_id"),
                    @Parameter(name = "value_column_name", value = "next"),
                    @Parameter(name = "segment_column_name", value = "segment_name"),
                    @Parameter(name = "segment_value", value = "seq_role"),
                    @Parameter(name = "initial_value", value = "10000000"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "roleId")
    private Long id;

    private String name;

    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Role parent;

    @OneToMany(targetEntity = Role.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<Role> sons;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Role_Permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    public Set<Role> getSons() {
        return sons;
    }

    public void setSons(Set<Role> sons) {
        this.sons = sons;
    }
}
