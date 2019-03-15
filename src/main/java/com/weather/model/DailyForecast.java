package main.java.com.weather.model;

public class DailyForecast {

	private String dt_txt;
	private float temperature_average;

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	public float getTemperature_average() {
		return temperature_average;
	}

	public void setTemperature_average(float temperature_average) {
		this.temperature_average = temperature_average;
	}
}
