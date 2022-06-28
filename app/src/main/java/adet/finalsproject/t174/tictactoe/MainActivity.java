package adet.finalsproject.t174.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends HasNavbarMenu {

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
            Toast.makeText(this, "Please enter player names", Toast.LENGTH_SHORT).show();
            return;
        }

        if(playerOName.equals(playerXName)) {
            Toast.makeText(this, "Players cannot have the same names", Toast.LENGTH_SHORT).show();
            return;
        }

        navigateToGrid();
    }

    public void navigateToGrid() {
        Intent intent = new Intent(MainActivity.this,GridActivity.class);
        startActivity(intent);
    }
}