package com.appmusic.model.exception;

import javax.annotation.Generated;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	private HttpStatus statusCode;
	public String errorTitle;
	public String message;
	public String detail;
	
	@Generated("SparkTools")
	private ApiError(Builder builder) {
		this.statusCode = builder.statusCode;
		this.errorTitle = builder.errorTitle;
		this.message = builder.message;
		this.detail = builder.detail;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorTitle() {
		return errorTitle;
	}
	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * Creates builder to build {@link ApiError}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link ApiError}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private HttpStatus statusCode;
		private String errorTitle;
		private String message;
		private String detail;

		private Builder() {
		}

		public Builder withStatusCode(HttpStatus statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder withErrorTitle(String errorTitle) {
			this.errorTitle = errorTitle;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withDetail(String detail) {
			this.detail = detail;
			return this;
		}

		public ApiError build() {
			return new ApiError(this);
		}
	}

	

}
