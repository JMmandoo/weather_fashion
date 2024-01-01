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
                let temperature = '현재온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(data.main.temp) + 'º';
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
                    case 'broken clouds':
                        videoElement.src = 'video/broken clouds.mp4';
                        break;
                    case 'scattered clouds':
                        videoElement.src = 'video/broken clouds.mp4';
                        break;
                }
                // 옷차림 추천 함수 호출
                recommendClothes(data);
            }

        });

    // 2초마다 옷차림 추천만 업데이트
    setInterval(function() {
        $.ajax({
            url: "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey + "&units=metric",
            dataType: 'json',
            type: 'GET',
            success: function (data) {
                // 옷차림 추천 함수 호출
                recommendClothes(data);
            }
        });
    }, 2000); // 10000 밀리초 = 10초
});

// 옷차림 추천 함수
function recommendClothes(data) {
    let temp = data.main.temp;

    // 각 온도 범위에 대한 이미지 배열 정의
    const clothingImages = {
        "5도 미만": ["images/date/date1.jpg", "images/date/date2.jpg", "images/date/date3.jpg","images/date/date4.jpg","images/date/date5.jpg","images/date/date6.jpg"],
        "5도 이상, 9도 미만": ["images/winter1.jpg", "images/winter2.jpg", "images/winter3.jpg"],
        "9도 이상, 12도 미만": ["images/winter1.jpg", "images/winter2.jpg", "images/winter3.jpg"],
        "12도 이상, 16도 미만": ["images/winter1.jpg", "images/winter1.jpg", "images/winter1.jpg"],
        "16도 이상, 20도 미만": ["image/winter1.jpg", "image/winter1.jpg", "image/winter1.jpg"],
        "20도 이상, 23도 미만": ["image/winter1.jpg", "image/winter1.jpg", "image/winter1.jpg"],
        "23도 이상": ["image/winter1.jpg", "image/winter1.jpg", "image/winter1.jpg"],
    };

    // 현재 온도 범위에 따라 랜덤한 이미지 선택
    let selectedImages = [];

    if (temp < 5) {
        selectedImages = clothingImages["5도 미만"].slice();
    } else if (temp >= 5 && temp < 9) {
        selectedImages = clothingImages["5도 이상, 9도 미만"].slice();
    } else if (temp >= 9 && temp < 12) {
        selectedImages = clothingImages["9도 이상, 12도 미만"].slice();
    } else if (temp >= 12 && temp < 16) {
        selectedImages = clothingImages["12도 이상, 16도 미만"].slice();
    } else if (temp >= 16 && temp <20 ) {
        selectedImages = clothingImages["16도 이상, 20도 미만"].slice();
    } else if (temp >= 20 && temp <23 ) {
        selectedImages = clothingImages['20도 이상, 23도 미만'].slice();
    } else {
        selectedImages = clothingImages['23도 이상'].slice();
    }

    // 랜덤 이미지 3개 선택
    let recommendedImages = [];
    for (let i = 0; i < 3; i++) {
        if (selectedImages.length === 0) {
            break;
        }
        let randomIndex = Math.floor(Math.random() * selectedImages.length);
        recommendedImages.push(selectedImages[randomIndex]);
        selectedImages.splice(randomIndex, 1); // 선택된 이미지 제거
    }

    // 선택된 이미지를 HTML에 추가
    if (recommendedImages.length > 0) document.getElementById('randomImage1').src = recommendedImages[0];
    if (recommendedImages.length > 1) document.getElementById('randomImage2').src = recommendedImages[1];
    if (recommendedImages.length > 2) document.getElementById('randomImage3').src = recommendedImages[2];
}

