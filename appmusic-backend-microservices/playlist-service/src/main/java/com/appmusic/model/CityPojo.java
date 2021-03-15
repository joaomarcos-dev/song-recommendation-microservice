package com.appmusic.model;

import javax.annotation.Generated;

public class CityPojo {
	
	private Double latitude;
	private Double longitude;
	private String name;

	@Generated("SparkTools")
	private CityPojo(Builder builder) {
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
		this.name = builder.name;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityPojo() {
		super();
	}
	/**
	 * Creates builder to build {@link CityPojo}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link CityPojo}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Double latitude;
		private Double longitude;
		private String name;

		private Builder() {
		}

		public Builder withLatitude(Double latitude) {
			this.latitude = latitude;
			return this;
		}

		public Builder withLongitude(Double longitude) {
			this.longitude = longitude;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public CityPojo build() {
			return new CityPojo(this);
		}
	}
	
	
	

}
