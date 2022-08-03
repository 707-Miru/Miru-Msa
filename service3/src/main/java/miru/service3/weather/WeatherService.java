package miru.service3.weather;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@RefreshScope
@Service
public class WeatherService {


//http://localhost:8792/weather?lat=37.568&lon=127.000
//http://api.openweathermap.org/data/2.5/weather?lat=37.568&lon=127.000&appid=2631dc675bb833988445a6b5a85fbb19
    public String getWeather(String lat, String lon) throws Exception {
        String urlstr = "http://api.openweathermap.org/data/2.5/weather?"
                + "lat="+lat+"&lon="+lon
                +"&appid=2631dc675bb833988445a6b5a85fbb19";
        URL url = new URL(urlstr);
        BufferedReader bf;
        String line;
        String result="";

        bf = new BufferedReader(new InputStreamReader(url.openStream()));
        while((line=bf.readLine())!=null){
            result=result.concat(line);
            System.out.println(line);
        }
        return "";
    }
}
