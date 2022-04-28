package com.example.weatherapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;

import com.example.weatherapp.GetWeatherFromAPI;
import org.json.JSONObject;

public class WeatherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text tempNow;

    @FXML
    private Text tempMinMax;

    @FXML
    private Text tempFact;

    @FXML
    private Text windSpeed;

    @FXML
    private TextField textCity;

    @FXML
    private Button btnFindData;

    @FXML
    void ff4848(ActionEvent event) {

    }

    @FXML
    void initialize() {
        btnFindData.setOnMouseClicked( mouseEvent -> {

            if (textCity.getText().trim().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Ошибка");
                alert.setContentText("Не введено название города");
                alert.showAndWait();
            } else {
                try{
                    GetWeatherFromAPI getWeather = new GetWeatherFromAPI("e1591c72b33a231c5c64c23bc9e705d6");
                    String dataWeather = getWeather.getDataFromAPI(textCity.getText().trim());
                    JSONObject jobj = new JSONObject(dataWeather);
                    double temp = jobj.getJSONObject("main").getDouble("temp");
                    double tempFeels = jobj.getJSONObject("main").getDouble("feels_like");
                    double tempMin = jobj.getJSONObject("main").getDouble("temp_min");
                    double tempMax = jobj.getJSONObject("main").getDouble("temp_max");
                    double windS = jobj.getJSONObject("wind").getDouble("speed");
                    tempNow.setText((temp <= 0 ? "Мороз: " : "Тепло: ") + temp);
                    tempFact.setText("По ощущению " + (tempFeels <= 0 ? "мороз: " : "тепло: ") + tempFeels);
                    tempMinMax.setText("Min: " + tempMin + " Max: " + tempMax);
                    windSpeed.setText("Скорость ветра: " + windS);

                } catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }
}
