package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ApiRequester {
    public void requestLocation(){
        Double latitude = 0.00;
        Double longitude = 0.00;
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

    private JSONObject request(String apiURL){
        try {

            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                System.out.println(inline);

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                return data_obj;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
