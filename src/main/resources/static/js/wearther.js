$(document).ready(function() {
  let weatherIconBaseUrl = "https://openweathermap.org/img/wn/";
  let apiKey = "54267cba0985dd7831e6b2a000a9f2c0"; // OpenWeatherMap API 키를 여기에 입력하세요

  $.ajax({
    url: "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey + "&units=metric",
    dataType: 'json',
    type: 'GET',
    success: function(data) {
      let iconCode = data.weather[0].icon;
      let iconUrl = weatherIconBaseUrl + iconCode + "@2x.png";
      let temperature = Math.floor(data.main.temp) + 'º';
      let city = data.name;

      $('.CurrIcon').html('<img src="' + iconUrl + '" alt="Weather Icon">');
      $('.CurrTemp').html(temperature);
      $('.City').html(city);
    }
  });
});