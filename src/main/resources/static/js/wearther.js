// 날씨 api - fontawesome 아이콘
let weatherIcon = {
    '01' : 'fas fa-sun',
    '02' : 'fas fa-cloud-sun',
    '03' : 'fas fa-cloud',
    '04' : 'fas fa-cloud-meatball',
    '09' : 'fas fa-cloud-sun-rain',
    '10' : 'fas fa-cloud-showers-heavy',
    '11' : 'fas fa-poo-storm',
    '13' : 'far fa-snowflake',
    '50' : 'fas fa-smog'
};

// 날씨 api - 서울
let apiURI = "https://api.openweathermap.org/data/2.5/weather?lat=37.531615&lon=126.94835949565962&appid=54267cba0985dd7831e6b2a000a9f2c0&units=metric";
$.ajax({
    url: apiURI,
    dataType: "json",
    type: "GET",
    success: function(resp) {

        var $Icon = (resp.weather[0].icon).substr(0,2);
        var $weather_description = resp.weather[0].main;
        var $Temp = Math.floor(resp.main.temp- 273.15) + 'º';
        var $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + resp.main.humidity+ ' %';
        var $wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' +resp.wind.speed + ' m/s';
        var $city = '서울';
        var $cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + resp.clouds.all +"%";
        var $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_min- 273.15) + 'º';
        var $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_max- 273.15) + 'º';


        $('.weather_icon').html('<i class="' + weatherIcon[$Icon] +' fa-5x" style="height : 150px; width : 150px;"></i>');
        $('.weather_description').html($weather_description);
        $('.current_temp').html($Temp);
        $('.humidity').html($humidity);
        $('.wind').html($wind);
        $('.city').html($city);
        $('.cloud').html($cloud);
        $('.temp_min').html($temp_min);
        $('.temp_max').html($temp_max);
    }
})



// 날씨 api - 경기도
let apiURI = "https://api.openweathermap.org/data/2.5/weather?lat=37.531615&lon=127.028084&appid=54267cba0985dd7831e6b2a000a9f2c0&units=metric";
$.ajax({
    url: apiURI,
    dataType: "json",
    type: "GET",
    success: function(resp) {

        var $g_Icon = (resp.weather[0].icon).substr(0,2);
        var $g_weather_description = resp.weather[0].main;
        var $g_Temp = Math.floor(resp.main.temp- 273.15) + 'º';
        var $g_humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + resp.main.humidity+ ' %';
        var $g_wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' +resp.wind.speed + ' m/s';
        var $g_city = '경기도';
        var $g_cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + resp.clouds.all +"%";
        var $g_temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_min- 273.15) + 'º';
        var $g_temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_max- 273.15) + 'º';


        $('.g_weather_icon').html('<i class="' + weatherIcon[$g_Icon] +' fa-5x" style="height : 150px; width : 150px;"></i>');
        $('.g_weather_description').html($g_weather_description);
        $('.g_current_temp').html($g_Temp);
        $('.g_humidity').html($g_humidity);
        $('.g_wind').html($g_wind);
        $('.g_city').html($g_city);
        $('.g_cloud').html($g_cloud);
        $('.g_temp_min').html($g_temp_min);
        $('.g_temp_max').html($g_temp_max);
    }
})