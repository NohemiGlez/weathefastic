package main.java.com.weather.util;

public class OpenWeatherAPIConfig {

	static private String baseURL = "https://api.openweathermap.org/data/2.5/forecast?q=";
	static private String countryCodeURL = ",MX";
	static private String unitsMetricURL = "&units=metric";
	static private String appIdURL = "&appid=45de57963c94096d95e7e98e32f3e9d4";
	static private String cityWeatherInformationURL = "";

	public static String getCityInformationURL(String city) {
		cityWeatherInformationURL = baseURL + city + countryCodeURL + unitsMetricURL + appIdURL;
		return cityWeatherInformationURL;
	}
}
