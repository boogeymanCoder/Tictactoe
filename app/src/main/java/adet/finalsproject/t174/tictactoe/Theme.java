package adet.finalsproject.t174.tictactoe;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class Theme extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.theme_preference_key), Context.MODE_PRIVATE);
        String theme = sharedPref.getString(getString(R.string.application_theme), "Razzmatazz");

        System.out.println("Theme from preferance: " + theme);
        switch(theme) {
            case "Carolina Blue":
                setTheme(R.style.Theme_CarolinaBlue);
                break;
            case "Dark Orchid":
                setTheme(R.style.Theme_DarkOrchid);
                break;
            case "Emerald":
                setTheme(R.style.Theme_Emerald);
                break;
            case "Grape":
                setTheme(R.style.Theme_Grape);
                break;
            case "Lapis Lazuli":
                setTheme(R.style.Theme_LapisLazuli);
                break;
            case "Medium Turquoise":
                setTheme(R.style.Theme_MediumTurquoise);
                break;
            case "Persian Blue":
                setTheme(R.style.Theme_PersianBlue);
                break;
            case "Razzmatazz":
                setTheme(R.style.Theme_Razzmatazz);
                break;
            case "Rose Red":
                setTheme(R.style.Theme_RoseRed);
                break;
            default:
                setTheme(R.style.Theme_Razzmatazz);
                break;
        }

        super.onCreate(savedInstanceState);
    }
}
