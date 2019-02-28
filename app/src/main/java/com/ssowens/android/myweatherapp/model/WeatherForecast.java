package com.ssowens.android.myweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import timber.log.Timber;

import static com.ssowens.android.myweatherapp.ui.MainActivity.BASE_URL;
import static com.ssowens.android.myweatherapp.ui.MainActivity.IMAGE_URL;

/**
 * Created by Sheila Owens on 2/28/19.
 */
public class WeatherForecast {

    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private Double message;
    @SerializedName("cnt")
    private Integer cnt;
    @SerializedName("list")
    private List<WeatherList> list = null;
    @SerializedName("city")
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(WeatherList weatherList) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public class City {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("coord")
        private Coord coord;
        @SerializedName("country")
        private String country;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coord=" + coord +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

    public class Clouds {

        @SerializedName("all")
        private Integer all;

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    public class Coord {

        @SerializedName("lat")
        private Double lat;
        @SerializedName("lon")
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }

    public class WeatherList {

        @SerializedName("dt")
        private Integer dt;
        @SerializedName("main")
        private Main main;
        @SerializedName("weather")
        private java.util.List<Weather> weather = null;
        @SerializedName("clouds")
        private Clouds clouds;
        @SerializedName("wind")
        private Wind wind;
        @SerializedName("snow")
        private Snow snow;
        @SerializedName("sys")
        private Sys sys;
        @SerializedName("dt_txt")
        private String dtTxt;

        public Integer getDt() {
            return dt;
        }

        public void setDt(Integer dt) {
            this.dt = dt;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public java.util.List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(java.util.List<Weather> weather) {
            this.weather = weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Snow getSnow() {
            return snow;
        }

        public void setSnow(Snow snow) {
            this.snow = snow;
        }

        public Sys getSys() {
            return sys;
        }

        public void setSys(Sys sys) {
            this.sys = sys;
        }

        public String getDtTxt() {
            return dtTxt;
        }

        public void setDtTxt(String dtTxt) {
            this.dtTxt = dtTxt;
        }

        public String getDescription() {
            if (getWeather() != null) {
                return getWeather().get(0).description;
            }
            return "";
        }

        @Override
        public String toString() {
            return "WeatherList{" +
                    "dt=" + dt +
                    ", main=" + main +
                    ", weather=" + weather +
                    ", clouds=" + clouds +
                    ", wind=" + wind +
                    ", snow=" + snow +
                    ", sys=" + sys +
                    ", dtTxt='" + dtTxt + '\'' +
                    '}';
        }
    }

    public class Main {

        @SerializedName("temp")
        private Double temp;
        @SerializedName("temp_min")
        private Double tempMin;
        @SerializedName("temp_max")
        private Double tempMax;
        @SerializedName("pressure")
        private Double pressure;
        @SerializedName("sea_level")
        private Double seaLevel;
        @SerializedName("grnd_level")
        private Double grndLevel;
        @SerializedName("humidity")
        private Integer humidity;
        @SerializedName("temp_kf")
        private Double tempKf;

        public String getTemp() {
            return temp.toString();
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public String getTempMin() {
            return tempMin.toString();
        }

        public void setTempMin(Double tempMin) {
            this.tempMin = tempMin;
        }

        public Double getTempMax() {
            return tempMax;
        }

        public void setTempMax(Double tempMax) {
            this.tempMax = tempMax;
        }

        public Double getPressure() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        public Double getSeaLevel() {
            return seaLevel;
        }

        public void setSeaLevel(Double seaLevel) {
            this.seaLevel = seaLevel;
        }

        public Double getGrndLevel() {
            return grndLevel;
        }

        public void setGrndLevel(Double grndLevel) {
            this.grndLevel = grndLevel;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }

        public Double getTempKf() {
            return tempKf;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    ", pressure=" + pressure +
                    ", seaLevel=" + seaLevel +
                    ", grndLevel=" + grndLevel +
                    ", humidity=" + humidity +
                    ", tempKf=" + tempKf +
                    '}';
        }

        public void setTempKf(Double tempKf) {
            this.tempKf = tempKf;
        }

    }


    public class Snow {


    }

    public class Sys {

        @SerializedName("pod")
        private String pod;

        public String getPod() {
            return pod;
        }

        public void setPod(String pod) {
            this.pod = pod;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "pod='" + pod + '\'' +
                    '}';
        }
    }


    public class Weather {

        @SerializedName("id")
        private Integer id;
        @SerializedName("main")
        private String main;
        @SerializedName("description")
        private String description;
        @SerializedName("icon")
        private String icon;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

//                if (getOffers() != null) {
//            if (!TextUtils.isEmpty(getOffers().get(0).getGuests().getAdults()))
//                return getOffers().get(0).getGuests().getAdults() + " " + "guests";
//        }
//        return "0";
//    }
//
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

//        @BindingAdapter("photoUrl")
//        public static void loadImage(ImageView view, String photoUrl) {
//            Glide.with(view.getContext())
//                    .load(photoUrl)
//                    .into(view);
//        }
//        @BindingAdapter("photoUrl")
        public String getIcon() {
            String iconUrl = BASE_URL + IMAGE_URL + icon;
            Timber.d("Sheila forecast url %s", iconUrl);
            return iconUrl;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public class Wind {

        @SerializedName("speed")
        private Double speed;
        @SerializedName("deg")
        private Double deg;

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        public Double getDeg() {
            return deg;
        }

        public void setDeg(Double deg) {
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", list=" + list +
                ", city=" + city +
                '}';
    }
}
