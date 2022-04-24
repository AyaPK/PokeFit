package uk.co.ayaspace.pokefit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uk.co.ayaspace.pokefit.R;
import uk.co.ayaspace.pokefit.model.egg.Egg;
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon;
import uk.co.ayaspace.pokefit.utils.Database;

import com.google.gson.Gson;


public class EggStatsScreenActivity extends AppCompatActivity {

    Database dataAccess = new Database(this);
    Button hatchButton;
    Gson gson = new Gson();
    String eggString;
    Egg egg;
    int slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_egg_step_countdown);

        Intent data = getIntent();
        eggString = data.getStringExtra("egg");
        slot = data.getIntExtra("slot", 0);
        egg = gson.fromJson(eggString, Egg.class);
        TextView eggStepString = findViewById(R.id.stepCountdown1);
        eggStepString.setText(egg.getStepsLeftToHatch()+" steps left to hatch! \n "+egg.hatchingDescription());

//        hatchButton = findViewById(R.id.hatchButton1);
//        //hatchButton.setVisibility(View.INVISIBLE);
//        hatchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FirstEggStepCountdownActivity.this, FirstEggHatchingStepCountdownActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void onHatchButtonPressed(View view) {

        OwnedPokemon pokemonFromEgg = egg.getPokemon();
        String pstring = gson.toJson(pokemonFromEgg);
        dataAccess.updatePartySlot(""+slot, pstring);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EggStatsScreenActivity.this);
        Editor editor = sharedPreferences.edit();
        editor.putString("partySlot"+slot, pstring);
        editor.apply();
        Intent resultInt = new Intent();
        resultInt.putExtra("hatched", true);
        setResult(Activity.RESULT_OK, resultInt);
        finish();
    }

}