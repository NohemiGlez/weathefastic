package main.java.com.weather.controller;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.java.com.weather.model.DayForecast;
import main.java.com.weather.util.OpenWeatherAPIConfig;

@RestController
public class CityController {

	@RequestMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private @ResponseBody DayForecast getCityByURLParam(@PathVariable("city") String city) {

		String URL = OpenWeatherAPIConfig.getCityInformationURL(city);

		RestTemplate restTemplate = new RestTemplate();
		DayForecast response = restTemplate.getForObject(URL, DayForecast.class);
		return response;
	}

	@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private @ResponseBody Model getCityByPassedParam(@RequestParam(value = "city") String city, Model model) {
		String URL = OpenWeatherAPIConfig.getCityInformationURL(city);

		RestTemplate restTemplate = new RestTemplate();
		DayForecast response = restTemplate.getForObject(URL, DayForecast.class);
		return model.addAttribute("cityName", response.getCity().getName());
	}
}
