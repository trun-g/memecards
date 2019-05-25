package com.example.memecards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume () {
        super.onResume();
        hideActionBar();
    }

    /** Called when the user taps the StartGame button */
    public void openStartGameActivity(View view) {
        Intent intent = new Intent(this, StartGameActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the CardLibrary button */
    public void openCardLibraryActivity(View view) {
        Intent intent = new Intent(this, CardLibraryActivity.class);
        startActivity(intent);
    }

    /** Hides the status bar and action bar for an activity**/
    private void hideActionBar(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide the action bar
        getSupportActionBar().hide();
    }
}
