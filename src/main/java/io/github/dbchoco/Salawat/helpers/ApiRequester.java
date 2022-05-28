package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ApiRequester {
    public void requestLocation(){
        double latitude = 0.00;
        double longitude = 0.00;
        String timezone = "Europe/Brussels";
        ResourceBundle bundle = ResourceBundle.getBundle("data");
        JSONObject response = request("https://api.ipgeolocation.io/ipgeo?apiKey=" + bundle.getString("locationApiKey"));

        if (response != null){
            latitude = Double.parseDouble((String)response.get("latitude"));
            longitude = Double.parseDouble((String)response.get("longitude"));
            JSONObject time_zone = (JSONObject) response.get("time_zone");
            timezone = (String) time_zone.get("name");
        }

        UserSettings.latitude = latitude;
        UserSettings.longitude = longitude;
        UserSettings.timezone = timezone;
        try{
            Main.reload();
        }catch (ClassNotFoundException e){
            System.out.println("Couldn't reload the app to apply new location: " + e);
        }
    }

    public String requestVersion(){
        String githubVersion = null;
        JSONObject response = request("https://api.github.com/repos/DBChoco/Salawat/releases/latest");

        if (response != null){
            githubVersion = (String) response.get("name");
        }
        return githubVersion;
    }

    public String[] requestWeather(){
        String[] weather = {null, null, null};
        ResourceBundle bundle = ResourceBundle.getBundle("data");
        JSONObject response = request("https://api.openweathermap.org/data/2.5/weather?lat=" + UserSettings.latitude +
                "&lon=" + UserSettings.longitude +
                "&units=" + "metric" +
                "&appid=" + bundle.getString("weatherApiKey"));

        if (response != null){
            System.out.println(response);
            weather[0] = String.valueOf(((JSONObject)(((JSONArray)response.get("weather")).get(0))).get("id"));
            if (((String) ((JSONObject)(((JSONArray)response.get("weather")).get(0))).get("icon")).charAt(2) == 'd'){
                weather[1] = "day";
            } else weather[1] = "night";
            weather[2] = ((JSONObject)response.get("main")).get("temp").toString();
        }
        return weather;
    }

    private JSONObject request(String apiURL){
        try {
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();

                return (JSONObject) parse.parse(inline.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
