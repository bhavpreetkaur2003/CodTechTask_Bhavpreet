// ============================================================
// PROJECT: REST API CLIENT - CODTECH INTERNSHIP
// TASK: Fetch and display weather data using public REST API
// ============================================================

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class WeatherAPIClient {

    public static void main(String[] args) {
        try {
            // Step 1: API endpoint (sample public API for demonstration)
            // You can replace "London" with any city name.
            String city = "London";
            String apiUrl = "https://api.weatherapi.com/v1/current.json?key=YOUR_API_KEY&q=" + city;

            // Step 2: Create URL object
            URL url = new URL(apiUrl);

            // Step 3: Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Step 4: Read API response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Step 5: Parse JSON using org.json library
            JSONObject jsonObj = new JSONObject(response.toString());
            JSONObject location = jsonObj.getJSONObject("location");
            JSONObject current = jsonObj.getJSONObject("current");

            // Step 6: Display structured output
            System.out.println("========================================");
            System.out.println("       WEATHER DATA - " + city.toUpperCase());
            System.out.println("========================================");
            System.out.println("City: " + location.getString("name"));
            System.out.println("Region: " + location.getString("region"));
            System.out.println("Country: " + location.getString("country"));
            System.out.println("Temperature (°C): " + current.getDouble("temp_c"));
            System.out.println("Condition: " + current.getJSONObject("condition").getString("text"));
            System.out.println("Humidity: " + current.getInt("humidity"));
            System.out.println("Wind Speed (kph): " + current.getDouble("wind_kph"));
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("? Error: " + e.getMessage());
        }
    }
}
