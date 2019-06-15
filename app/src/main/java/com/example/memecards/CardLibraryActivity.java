package com.example.memecards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.domainobjects.MemeCard;
import com.example.memedatabase.DBLoader;
import com.example.memedatabase.MasterDeckInterface;
import com.example.memedatabase.MasterDeckStub;

public class CardLibraryActivity extends AppCompatActivity {
    private ArrayList<MemeCard> cards = new ArrayList<>();
    private static MasterDeckInterface masterDeck = new MasterDeckStub();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_library);

        // load DB into static stub
        DBLoader.loadMasterDeck(this.masterDeck, this.getApplicationContext());
        MemeCard card;
        for (String n : this.masterDeck.retrieveAllCardNames()) {
            card = this.masterDeck.retrieveCard(n);
            this.cards.add(card);
        }

        showCardStats();
        MakeCardsList();
    }

    /** Back to the home page when the user taps the BACK button */
    public void BackHomePage(View v) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        onBackPressed();
    }

    // Show the number of total cards and locked cards
    private void showCardStats() {
        TextView unlockedText = findViewById(R.id.UnlockedText);
        TextView unlockedNum = findViewById(R.id.UnlockedNum);

        unlockedText.setText("Unlocked Cards");
        unlockedNum.setText(
                ":  " + this.masterDeck.retrieveUnlockedCardNames().size() +
                        " / " + this.masterDeck.deckSize()
        );
    }

    private void MakeCardsList() {
        RecyclerView myRecyView = (RecyclerView)findViewById(R.id.RecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, this.cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyView.setLayoutManager(layoutManager);
        myRecyView.setAdapter(adapter);
    }

    @Override
    protected void onResume () {
        super.onResume();
        hideActionBar();
    }

    public void unlockCard(View v) {
        String new_card = null;
        for (MemeCard card : this.cards){
            if (card.isLocked()) {
                masterDeck.unlockCard(card.getName());
                new_card = card.getName();
                Toast.makeText(
                        CardLibraryActivity.this,
                        "Congratulations, you have unlocked " + new_card + "!!",
                        Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (new_card == null)
            Toast.makeText(
                    CardLibraryActivity.this,
                    "You've already unlocked all cards!!!",
                    Toast.LENGTH_SHORT).show();
        else {
            //update recycler view
            MakeCardsList();
            showCardStats();
        }

    }

    /** Hides the status bar and action bar for an activity**/
    private void hideActionBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide the action bar
        getSupportActionBar().hide();
    }
}
