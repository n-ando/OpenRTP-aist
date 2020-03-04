package jp.go.aist.rtm.systemeditor.ui.views.logview;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"time",
"name",
"manager",
"level",
"message"
})

public class LogParam {
	@JsonProperty("time")
	private String time;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("manager")
	private String manager;
	
	@JsonProperty("level")
	private String level;
	
	@JsonProperty("message")
	private String message;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("time")
	public String getTime() {
		return time;
	}
	@JsonProperty("time")
	public void setTime(String time) {
		this.time = time;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("manager")
	public String getManager() {
		return manager;
	}
	@JsonProperty("manager")
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@JsonProperty("level")
	public String getLevel() {
		return level;
	}
	@JsonProperty("level")
	public void setLevel(String level) {
		this.level = level;
	}
	
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}
	
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
