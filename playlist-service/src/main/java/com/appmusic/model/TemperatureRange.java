package com.appmusic.model;

import javax.annotation.Generated;

public class TemperatureRange {

	public Boolean getMinTmpInclusive() {
		return minTmpInclusive;
	}

	public void setMinTmpInclusive(Boolean minTmpInclusive) {
		this.minTmpInclusive = minTmpInclusive;
	}

	public Boolean getMaxTmpInclusive() {
		return maxTmpInclusive;
	}

	public void setMaxTmpInclusive(Boolean maxTmpInclusive) {
		this.maxTmpInclusive = maxTmpInclusive;
	}

	private Float minTmp;
	private Boolean minTmpInclusive;

	private Float maxTmp;
	private Boolean maxTmpInclusive;

	@Generated("SparkTools")
	private TemperatureRange(Builder builder) {
		this.minTmp = builder.minTmp;
		this.maxTmp = builder.maxTmp;
	}

	public Float getMinTmp() {
		return minTmp;
	}

	public void setMinTmp(Float minTmp) {
		this.minTmp = minTmp;
	}

	public Float getMaxTmp() {
		return maxTmp;
	}

	public TemperatureRange() {
		super();
	}

	public void setMaxTmp(Float maxTmp) {
		this.maxTmp = maxTmp;
	}

	/**
	 * Creates builder to build {@link TemperatureRange}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TemperatureRange}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Float minTmp;
		private Float maxTmp;

		private Builder() {
		}

		public Builder withMinTmp(Float minTmp) {
			this.minTmp = minTmp;
			return this;
		}

		public Builder withMaxTmp(Float maxTmp) {
			this.maxTmp = maxTmp;
			return this;
		}

		public TemperatureRange build() {
			return new TemperatureRange(this);
		}
	}

	@Override
	public String toString() {
		return "TemperatureRange [minTmp=" + minTmp + ", minTmpInclusive=" + minTmpInclusive + ", maxTmp=" + maxTmp
				+ ", maxTmpInclusive=" + maxTmpInclusive + "]";
	}
	
	

}
