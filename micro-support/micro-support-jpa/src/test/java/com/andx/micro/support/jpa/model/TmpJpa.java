package com.andx.micro.support.jpa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by andongxu on 17-1-4.
 */
@javax.persistence.Entity
public class TmpJpa extends Entity{
    @Id
	@GeneratedValue
	private Long id;

	private String cola;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCola() {
		return cola;
	}

	public void setCola(String cola) {
		this.cola = cola;
	}
}
