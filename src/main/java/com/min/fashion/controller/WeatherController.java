package com.min.fashion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/current-weather")
    public String getCurrentWeather() {
        String apiKey = "54267cba0985dd7831e6b2a000a9f2c0";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=37.57&lon=129.98&appid=" + apiKey + "&units=metric";

        // API 호출
        String weatherData = restTemplate.getForObject(apiUrl, String.class);

        // 날씨 데이터 처리 로직을 추가하세요.

        return "wearther"; // 날씨 정보를 표시하는 페이지로 리디렉션할 수 있습니다.
    }
}