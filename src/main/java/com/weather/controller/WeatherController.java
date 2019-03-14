package main.java.com.weather.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import main.java.com.weather.model.Forecast;
import main.java.com.weather.model.Response;
import main.java.com.weather.util.OpenWeatherAPIConfig;
import main.java.com.weather.util.RestTemplateMethods;

@RestController
public class WeatherController {

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
			
			return model.addAttribute("forecasts", response.getList());
//			response.getList().forEach(date->System.out.println(date.getMain().getTemp()));
		} catch (RestClientException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			e.getStackTrace().toString();
		}
		return null;
	}
}
