package adet.finalsproject.t174.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText playerONameField;
    EditText playerXNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerONameField = findViewById(R.id.playerOName);
        playerXNameField = findViewById(R.id.playerXName);
    }

    public void startGame(View v) {
        String playerOName = playerONameField.getText().toString();
        String playerXName = playerXNameField.getText().toString();

        if(playerOName.isEmpty() || playerXName.isEmpty()) {
            Snackbar.make(v, "Please enter player names", Snackbar.LENGTH_LONG).show();
            return;
        }

        navigateToGrid();
    }

    public void navigateToGrid() {
        Intent intent = new Intent(MainActivity.this,GridActivity.class);
        startActivity(intent);
    }
}