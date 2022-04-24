package uk.co.ayaspace.pokefit.activities.introductionActivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.utils.Typewriter

class FirstTimePlayerPromptForFitnessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_player_prompt_for_fitness)
        val intent = intent
        val dialogue = intent.getStringExtra("text").toString()
        displayText(dialogue)
    }

    private fun displayText(stringToDisplay: String) {
        val animatedText = findViewById<View>(R.id.typewriter) as Typewriter
        animatedText.setCharacterDelay(50)
        animatedText.animateText(stringToDisplay)
    }

    fun onLowFitnessButtonPressed(view: View?) {
        val intent = Intent()
        intent.putExtra("fitness", "Snorlax")
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onMedFitnessButtonPressed(view: View?) {
        val intent = Intent()
        intent.putExtra("fitness", "Meowth")
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onHighFitnessButtonPressed(view: View?) {
        val intent = Intent()
        intent.putExtra("fitness", "Machoke")
        setResult(RESULT_OK, intent)
        finish()
    }
}