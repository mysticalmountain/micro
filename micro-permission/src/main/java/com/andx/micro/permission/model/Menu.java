package com.andx.micro.permission.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;
import com.andx.micro.support.jpa.model.Entity;

/**
 * Created by andongxu on 16-11-24.
 */
@javax.persistence.Entity
public class Menu extends Entity {

    @Id
    @GenericGenerator(name = "menuId", strategy = "enhanced-table",
            parameters = {
                    @Parameter(name = "table_name", value = "seq_id"),
                    @Parameter(name = "value_column_name", value = "next"),
                    @Parameter(name = "segment_column_name", value = "segment_name"),
                    @Parameter(name = "segment_value", value = "seq_menu"),
                    @Parameter(name = "initial_value", value = "10000000"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "menuId")
    private Long id;

    private String name;

    private String action;

    @OneToOne(targetEntity = Menu.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Menu parent;

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<Menu> sons;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Set<Menu> getSons() {
        return sons;
    }

    public void setSons(Set<Menu> sons) {
        this.sons = sons;
    }
}
