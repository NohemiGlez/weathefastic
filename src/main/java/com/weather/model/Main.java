package main.java.com.weather.model;

public class Main {

	private int temp;
	private int temp_min;
	private int temp_max;
	private int pressure;
	private int humidity;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(int temp_min) {
		this.temp_min = temp_min;
	}

	public int getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(int temp_max) {
		this.temp_max = temp_max;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

}
