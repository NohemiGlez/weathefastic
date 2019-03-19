package main.java.com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow {
	@JsonProperty("3h")
	private String last_three_hours_volume;

	public String getLast_three_hours_volume() {
		return last_three_hours_volume;
	}

	public void setLast_three_hours_volume(String last_three_hours_volume) {
		this.last_three_hours_volume = last_three_hours_volume;
	}

}
