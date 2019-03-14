package main.java.com.weather.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

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
	private @ResponseBody void getCityByPassedParams(@PathVariable("city") String city, Model model) {

		try {
			String URL = OpenWeatherAPIConfig.getCityInformationURL(city);
			Response response = RestTemplateMethods.getRestTemplate().getForObject(URL, Response.class);
			model.addAttribute("date", response.getCity().toString());
		} catch (RestClientException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			e.getStackTrace().toString();
		}
//		return null;
	}

//	@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	private @ResponseBody void getCityByPassedParam(@RequestParam(value = "city") String city, Model model) {
//		String URL = OpenWeatherAPIConfig.getCityInformationURL(city);
//
//		RestTemplate restTemplate = new RestTemplate();
//		Response response = restTemplate.getForObject(URL, Response.class);
//		for (Forecast forecast : response.getList()) {
//			String dt_txt = (forecast.getDt_txt());
//			if (dt_txt != null) {
//				model.addAttribute("date", dt_txt.toString());
//			}
//		}
//	}
}
