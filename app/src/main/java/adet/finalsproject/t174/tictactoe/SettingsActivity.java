package adet.finalsproject.t174.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

public class SettingsActivity extends Theme implements CompoundButton.OnCheckedChangeListener{
    private Switch lightDarkSwitch;
    private RadioButton carolinaBlue;
    private RadioButton darkOrchid;
    private RadioButton emerald;
    private RadioButton grape;
    private RadioButton lapisLazuli;
    private RadioButton mediumTurquoise;
    private RadioButton persianBlue;
    private RadioButton razzmatazz;
    private RadioButton roseRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lightDarkSwitch = (Switch) findViewById(R.id.lightDarkSwitch);
        lightDarkSwitch.setOnCheckedChangeListener(this);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                lightDarkSwitch.setText("Dark Mode");
                lightDarkSwitch.setChecked(true);
                break;

            default:
                lightDarkSwitch.setText("Light Mode");
                lightDarkSwitch.setChecked(false);
        }

        carolinaBlue = findViewById(R.id.theme_carolina_blue);
        darkOrchid = findViewById(R.id.theme_dark_orchid);
        emerald = findViewById(R.id.theme_emerald);
        grape = findViewById(R.id.theme_grape);
        lapisLazuli = findViewById(R.id.theme_lapis_lazuli);
        mediumTurquoise = findViewById(R.id.theme_medium_turquoise);
        persianBlue = findViewById(R.id.theme_persian_blue);
        razzmatazz = findViewById(R.id.theme_razzmatazz);
        roseRed = findViewById(R.id.theme_rose_red);

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.theme_preference_key), Context.MODE_PRIVATE);
        String theme = sharedPref.getString(getString(R.string.application_theme), "Razzmatazz");
        System.out.println("Theme from preferance: " + theme);
        switch(theme) {
            case "Carolina Blue":
                carolinaBlue.toggle();
                break;
            case "Dark Orchid":
                darkOrchid.toggle();
                break;
            case "Emerald":
                emerald.toggle();
                break;
            case "Grape":
                grape.toggle();
                break;
            case "Lapis Lazuli":
                lapisLazuli.toggle();
                break;
            case "Medium Turquoise":
                mediumTurquoise.toggle();
                break;
            case "Persian Blue":
                persianBlue.toggle();
                break;
            case "Razzmatazz":
                razzmatazz.toggle();
                break;
            case "Rose Red":
                roseRed.toggle();
                break;
            default:
                razzmatazz.toggle();
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            System.out.println("checked");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            lightDarkSwitch.setText("Dark Mode");
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            System.out.println("unchecked");
            lightDarkSwitch.setText("Light Mode");
        }
    }

    public void onThemeSelect(View view) {
        System.out.println("Theme selected: " + view.getId());
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.theme_preference_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch(view.getId()) {
            case R.id.theme_carolina_blue:
                System.out.println("Theme to set: " + "Carolina Blue");
                editor.putString(getString(R.string.application_theme), "Carolina Blue");
                break;
            case R.id.theme_dark_orchid:
                System.out.println("Theme to set: " + "Dark Orchid");
                editor.putString(getString(R.string.application_theme), "Dark Orchid");
                break;
            case R.id.theme_emerald:
                System.out.println("Theme to set: " + "Emerald");
                editor.putString(getString(R.string.application_theme), "Emerald");
                break;
            case R.id.theme_grape:
                System.out.println("Theme to set: " + "Grape");
                editor.putString(getString(R.string.application_theme), "Grape");
                break;
            case R.id.theme_lapis_lazuli:
                System.out.println("Theme to set: " + "Lapis Lazuli");
                editor.putString(getString(R.string.application_theme), "Lapis Lazuli");
                break;
            case R.id.theme_medium_turquoise:
                System.out.println("Theme to set: " + "Medium Turquoise");
                editor.putString(getString(R.string.application_theme), "Medium Turquoise");
                break;
            case R.id.theme_persian_blue:
                System.out.println("Theme to set: " + "Persian Blue");
                editor.putString(getString(R.string.application_theme), "Persian Blue");
                break;
            case R.id.theme_razzmatazz:
                System.out.println("Theme to set: " + "Razzmatazz");
                editor.putString(getString(R.string.application_theme), "Razzmatazz");
                break;
            case R.id.theme_rose_red:
                System.out.println("Theme to set: " + "Rose Red");
                editor.putString(getString(R.string.application_theme), "Rose Red");
                break;
            default:
                System.out.println("default: Theme to set: " + "Razzmatazz");
                editor.putString(getString(R.string.application_theme), "Razzmatazz");
        }
        editor.apply();


        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;

            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}