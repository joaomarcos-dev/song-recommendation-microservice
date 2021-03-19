package com.appmusic.model;

//@MappedSuperclass
//@JsonbNillable
public abstract class AbstractEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

//	@Version
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
