package miru.service3.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value={"/", "/weather"})
public class WeatherController {
//http://localhost:8792/weather?lat=37.568&lon=127.000
    @Autowired
    WeatherService weatherService;

    @GetMapping(value = {"/weather", "/weather?lat={lat}&lon={lon}"})
    public String weather(@RequestParam(value = "lat") String lat, @RequestParam(value = "lon") String lon) throws Exception {
        return weatherService.getWeather(lat,lon);
    }

}
