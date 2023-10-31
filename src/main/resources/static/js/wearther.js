// 날씨 api - 서울
$(document).ready(function () {
    let weatherIconBaseUrl = "https://openweathermap.org/img/wn/";
    let apiKey = "54267cba0985dd7831e6b2a000a9f2c0"; // OpenWeatherMap API 키를 여기에 입력하세요
    // "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=54267cba0985dd7831e6b2a000a9f2c0&units=metric&lang=kr"
    let videoElement = document.querySelector('.custom-video');
    $.ajax({
        url: "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey + "&units=metric",
        dataType: 'json',
        type: 'GET',
        success: function (data) {
            let iconCode = data.weather[0].icon;
            let iconUrl = weatherIconBaseUrl + iconCode + "@2x.png";
            let weather_description = data.weather[0].description;
            let temperature = Math.floor(data.main.temp) + 'º';
            let humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + data.main.humidity + ' %';
            let wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' + data.wind.speed + ' m/s';
            let city = data.name;
            let cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + data.clouds.all + "%";
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

            // 현재 날씨에 따라 메인페이지 백그라운드 동영상 변경
            switch (weather_description) {
                case 'clear sky':
                    videoElement.src = 'video/sun.mp4';
                    break;
                case 'snow':
                    videoElement.src = 'video/snow.mp4';
                    break;
                case 'rain':
                    videoElement.src = 'video/rain.mp4';
                    break;
                case 'mist':
                    videoElement.src = 'video/mist.mp4';
                    break;
            }
        }
    });
});

