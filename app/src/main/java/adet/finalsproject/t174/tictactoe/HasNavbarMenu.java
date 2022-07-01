package adet.finalsproject.t174.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HasNavbarMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        navigate(item);
        return super.onOptionsItemSelected(item);
    }

    public void navigate(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings:
                navigateToSettings();
//                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info:
                navigateToInfo();
//                Toast.makeText(this, "Info Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public boolean onOptionsItemSelectedConfirmed( @NonNull MenuItem item ) {
        AlertDialog.Builder alert = new AlertDialog.Builder(HasNavbarMenu.this);
        alert.setTitle("Go to setting");
        alert.setMessage("Are you sure you? Current operations will be cleared.");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                navigate(item);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.show();

        return super.onOptionsItemSelected(item);
    }


    public void navigateToSettings() {
        Intent intent = new Intent(HasNavbarMenu.this,SettingsActivity.class);
        startActivity(intent);
    }

    public void navigateToInfo() {
        Intent intent = new Intent(HasNavbarMenu.this,InfoActivity.class);
        startActivity(intent);
    }
}