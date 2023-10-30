$(document).ready(function() {
  let weatherIconBaseUrl = "https://openweathermap.org/img/wn/";
  let apiKey = "54267cba0985dd7831e6b2a000a9f2c0"; // OpenWeatherMap API 키를 여기에 입력하세요
  // "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=54267cba0985dd7831e6b2a000a9f2c0&units=metric&lang=kr"
  let videoElement = document.querySelector('.custom-video');
  $.ajax({
    url: "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey + "&units=metric&lang=kr",
    dataType: 'json',
    type: 'GET',
    success: function(data) {
      let iconCode = data.weather[0].icon;
      let iconUrl = weatherIconBaseUrl + iconCode + "@2x.png";
      let weather_description = data.weather[0].description;
      let temperature = Math.floor(data.main.temp) + 'º';
      let humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + data.main.humidity+ ' %';
      let wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' +data.wind.speed + ' m/s';
      let city = data.name;
      let cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + data.clouds.all +"%";
      let temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(data.main.temp_min) + 'º';
      let temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(data.main.temp_max) + 'º';

      $('.CurrIcon').html('<img src="' + iconUrl + '" alt="Weather Icon">');
      $('.temperature').html(temperature);
      $('.weather_description').html(weather_description);
      $('.humidity').html(humidity);
      $('.City').html(city);
      $('.temp_max').html(temp_max);
      $('.temp_min').html(temp_min);
      $('.wind').html(wind);
      $('.cloud').html(cloud);

      // 이 부분에서 날씨 설명에 따라 동영상 변경
      switch (weather_description) {
        case '맑음':
          videoElement.src = 'video/sun.mp4';
          break;
        case '눈':
          videoElement.src = 'video/snow.mp4';
          break;
        case '비':
          videoElement.src = 'video/rain.mp4';
          break;
          // 다른 날씨 조건에 따른 동영상 파일을 추가할 수 있습니다.
      }
    }
  });
});

