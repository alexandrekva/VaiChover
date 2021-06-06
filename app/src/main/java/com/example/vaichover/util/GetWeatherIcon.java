package com.example.vaichover.util;

import android.graphics.drawable.Drawable;

import com.example.vaichover.R;

public class GetWeatherIcon {

    public static Integer getIconById(Integer id) {

        if (id >= 200 && id <= 232) {
            return R.drawable.ic_thunderstorm;
        } else if (id >= 300 && id <= 321) {
            return R.drawable.ic_drizzle;
        } else if (id >= 500 && id <= 531) {
            return R.drawable.ic_rain;
        } else if (id >= 600 && id <= 622) {
            return R.drawable.ic_snow;
        } else if (id >= 701 && id <= 781) {
            return R.drawable.ic_atmosphere;
        } else if (id == 800) {
            return R.drawable.ic_sun;
        } else {
            return R.drawable.ic_cloudy;
        }
    }

}
