package com.appmusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@MappedSuperclass
//@JsonbNillable
public abstract class AbstractEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	public Integer id;

//	@Version
	@JsonIgnore
	public Integer version;

	/* CONSTRUCTORS */
	public AbstractEntity() {
		super();
	}

	/* GETTERS AND SETTERS */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
