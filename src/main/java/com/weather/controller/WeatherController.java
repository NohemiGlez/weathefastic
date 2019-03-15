package main.java.com.weather.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import main.java.com.weather.model.Response;
import main.java.com.weather.model.DailyForecast;
import main.java.com.weather.util.OpenWeatherAPIConfig;
import main.java.com.weather.util.RestTemplateMethods;

@RestController
public class WeatherController {

	int forecast_day_counter, day_counter = 0;
	float temperature_average = 0;
	String current_temperature, temperature_max, temperature_min;

	@RequestMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private @ResponseBody Response getCityByURLParam(@PathVariable("city") String city) {

		try {
			String URL = OpenWeatherAPIConfig.getCityInformationURL(city);
			Response response = RestTemplateMethods.getRestTemplate().getForObject(URL, Response.class);
			return response;
		} catch (RestClientException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			e.getStackTrace().toString();
		}
		return null;
	}

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	private @ResponseBody Model getCityByPassedParams(@RequestParam(value = "city") String city, Model model) {

		try {
			String URL = OpenWeatherAPIConfig.getCityInformationURL(city);
			Response response = RestTemplateMethods.getRestTemplate().getForObject(URL, Response.class);

//			String day_number = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//			String day_number = "2019-03-16";
			LocalTime local_time_asia = LocalTime.now(ZoneId.of("Asia/Singapore"));
			DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
			Date current_date = new Date();
			System.out.println(date_format.format(local_time_asia));
			Calendar c = Calendar.getInstance();
			c.setTime(current_date);
			c.add(Calendar.DATE, 1);
			Date current_date_plus_one = c.getTime();

			DailyForecast daily_forecast = new DailyForecast();
			List<DailyForecast> daily_forecast_list = new ArrayList<DailyForecast>();

			response.getList().forEach(forecast -> {
				if (forecast.getDt_txt().contains(current_date_plus_one.toString())) {
					temperature_average += forecast.getMain().getTemp();
					forecast_day_counter++;
				}
			});

			daily_forecast.setDt_txt(current_date_plus_one.toString());
			daily_forecast.setTemperature_average((temperature_average / forecast_day_counter));
			System.out.println(daily_forecast.getTemperature_average());
			daily_forecast_list.add(daily_forecast);

			current_temperature = String.valueOf(response.getList().iterator().next().getMain().getTemp());
			temperature_min = String.valueOf(response.getList().iterator().next().getMain().getTemp_min());
			temperature_max = String.valueOf(response.getList().iterator().next().getMain().getTemp_max());

			model.addAttribute("city", response.getCity().getName());
			model.addAttribute("current_temperature", current_temperature);
			model.addAttribute("min_temperature", temperature_min);
			model.addAttribute("max_temperature", temperature_max);
			model.addAttribute("current_date", current_date_plus_one);
			model.addAttribute("daily_forecast", daily_forecast_list);

		} catch (RestClientException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			e.getStackTrace().toString();
		}
		return model;
	}
}
