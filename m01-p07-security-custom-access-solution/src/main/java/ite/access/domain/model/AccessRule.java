package ite.access.domain.model;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class AccessRule {

	private String id;
	private String customerId;
	private String projectId;
	private String service;
	private String path;
	private String query;
	private List<RequestMethod> allowedMethod;
	private String securityRole;
	private String securityGroup;

	public AccessRule() {
	}

	private AccessRule(Builder builder) {
		this.id = builder.id;
		this.customerId = builder.customerID;
		this.projectId = builder.projectID;
		this.service = builder.service;
		this.path = builder.path;
		this.query = builder.query;
		this.allowedMethod = builder.allowedMethod;
		this.securityRole = builder.securityRole;
		this.securityGroup = builder.securityGroup;
	}

	public String getId() {
		return id;
	}


	public String getCustomerId() {
		return customerId;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getService() {
		return service;
	}

	public String getPath() {
		return path;
	}

	public String getQuery() {
		return query;
	}

	public List<RequestMethod> getAllowedMethod() {
		return allowedMethod;
	}

	public String getSecurityRole() {
		return securityRole;
	}

	public String getSecurityGroup() {
		return securityGroup;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setService(String service) {
		this.service = service;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setAllowedMethod(List<RequestMethod> allowedMethod) {
		this.allowedMethod = allowedMethod;
	}

	public static class Builder {
		private String id;
		private String customerID;
		private String projectID;
		private String service;
		private String path;
		private String query;
		private List<RequestMethod> allowedMethod;
		private String securityRole;
		private String securityGroup;
		
		public Builder id(String id){
			this.id = id;
			return this;
		}
		
		public Builder customerID(String customerID){
			this.customerID = customerID;
			return this;
		}
		
		public Builder projectID(String projectID){
			this.projectID = projectID;
			return this;
		}
		
		public Builder service(String service){
			this.service = service;
			return this;
		}
		
		public Builder path(String path){
			this.path = path;
			return this;
		}
		
		public Builder query(String query){
			this.query = query;
			return this;
		}
		
		public Builder allowedMethod(List<RequestMethod> allowedMethod){
			this.allowedMethod = allowedMethod;
			return this;
		}

		public Builder securityRole(String securityRole){
			this.securityRole = securityRole;
			return this;
		}

		public Builder securityGroup(String securityGroup){
			this.securityGroup = securityGroup;
			return this;
		}

		public AccessRule build() {
			return new AccessRule(this);
		}
	}
}
