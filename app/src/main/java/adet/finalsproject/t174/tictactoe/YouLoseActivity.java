package adet.finalsproject.t174.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class YouLoseActivity extends HasNavbarMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_lose);
    }

    public void navigateToMain(View v) {
        this.finish();
    }
}