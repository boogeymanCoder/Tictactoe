package adet.finalsproject.t174.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DrawActivity extends HasNavbarMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
    }

    public void navigateToMain(View v) {
        Intent intent = new Intent(DrawActivity.this,GridActivity.class);
        startActivity(intent);
    }
}