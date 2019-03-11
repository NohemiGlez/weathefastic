package main.java.com.weather.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.java.com.weather.model.City;
import main.java.com.weather.util.OpenWeatherAPIConfig;

@RestController
public class CityController {

	@RequestMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private @ResponseBody City getCityByURLParam(@PathVariable("city") String city) {

		String URL = OpenWeatherAPIConfig.getCityInformationURL(city);

		RestTemplate restTemplate = new RestTemplate();
		City response = restTemplate.getForObject(URL, City.class);
		return response;
	}

	@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private @ResponseBody City getCityByPassedParam(@RequestParam(value = "city") String city) {
		String URL = OpenWeatherAPIConfig.getCityInformationURL(city);

		RestTemplate restTemplate = new RestTemplate();
		City response = restTemplate.getForObject(URL, City.class);
		return response;
	}
}
