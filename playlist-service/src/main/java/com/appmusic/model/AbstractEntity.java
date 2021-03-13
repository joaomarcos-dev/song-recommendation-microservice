package com.appmusic.model;

//@MappedSuperclass
//@JsonbNillable
public abstract class AbstractEntity {
//	@Id/
	// Telling the JPA/implementation that the DB will generate the
	// values for this column
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

//	@Version
	public Integer version;

	/* CONSTRUCTORS */
	public AbstractEntity() {
		super();
		// Generate entity identifier/primary key
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

	/*
	 * The implementation of equal() and hashCode is compliant with the behavior of
	 * Collections and the hashCode() Contract
	 */

	/**
	 * 22/12/2020 I probably broke something when commenting this. I commented this
	 * out in order to get the serialization of AdressPojo Works
	 */
//	@Override
//	public boolean equals(Object o) {
//		// Check if they are the same instance
//		if (this == o) {
//			return true;
//		}
//		// If the received object is null, they definetly not the same
//		// If the received object is not an instanceof this, they definetly not the same
//		if ((o == null) || !(o instanceof AbstractEntity)) {
//			return false;
//		}
//
//		// In order to get the id of ae
//		AbstractEntity ae = (AbstractEntity) o;
//
//		// If the id of this is null, we'll always return false
//		if (getId() == null) {
//			return false;
//		}
//
//		// Finally, directly comparing the two id's
//		return getId().equals(ae.getId());
//	}

//	@Override
//	public int hashCode() {
//		if (getId() != null) {
//			return getId().hashCode();
//		} else {
//			return super.hashCode();
//		}
//	}
}
