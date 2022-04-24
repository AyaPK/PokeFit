package uk.co.ayaspace.pokefit.activities.introductionActivities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.activities.MainGameActivityWithNavBar
import uk.co.ayaspace.pokefit.utils.StringVariableParser
import uk.co.ayaspace.pokefit.utils.Typewriter

class FirstTimePlayerWelcomeScreenActivity : AppCompatActivity() {
    private var speechDialoguePosition = 0
    private var playerName: String? = null
    private var nextSpeechButton1: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_player_welcome_screen)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val playerExists = sharedPreferences.getBoolean("playerExists", false)
        if (playerExists) {
            val intent = Intent(this, MainGameActivityWithNavBar::class.java)
            startActivity(intent)
            finish()
        }
        nextSpeechButton1 = findViewById(R.id.oakIntroSpeechNextLineButton)
        nextSpeechButton1?.setOnClickListener { onNextSpeechButtonClicked() }
        oakFadeIn()
        displayText(resources.getStringArray(R.array.oak_introduction_speech_dialogue)[speechDialoguePosition])
    }

    private fun oakFadeIn() {
        val img = findViewById<View>(R.id.oakDisplayArea) as ImageView
        val fadeInAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fadein)
        img.visibility = View.VISIBLE
        img.startAnimation(fadeInAnimation)
    }

    private fun oakFadeOut() {
        val img = findViewById<View>(R.id.oakDisplayArea) as ImageView
        val fadeOutAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fadeout)
        img.startAnimation(fadeOutAnimation)
        img.visibility = View.INVISIBLE
    }

    private fun displayText(stringToDisplay: String) {
        val animatedText = findViewById<View>(R.id.typewriter) as Typewriter
        animatedText.setCharacterDelay(50)
        animatedText.animateText(stringToDisplay)
    }

    fun onNextSpeechButtonClicked() {
        if (speechDialoguePosition < 6) {
            speechDialoguePosition++
            displayText(resources.getStringArray(R.array.oak_introduction_speech_dialogue)[speechDialoguePosition])
        } else if (speechDialoguePosition == 6) {
            speechDialoguePosition++
            showInputPrompt("Please type your name", nextSpeechButton1)
        } else if (speechDialoguePosition < 10) {
            speechDialoguePosition++
            displayText(StringVariableParser().ParseString(resources.getStringArray(R.array.oak_introduction_speech_dialogue)[speechDialoguePosition], this))
        } else if (speechDialoguePosition == 10) {
            speechDialoguePosition++
            val intent = Intent(this, FirstTimePlayerPromptForFitnessActivity::class.java)
            intent.putExtra("text", "So, tell me a little bit about yourself, $playerName. How athletic are you?")
            fitnessActivityResultCaller.launch(intent)
        } else if (speechDialoguePosition < 13) {
            speechDialoguePosition++
            displayText(StringVariableParser().ParseString(resources.getStringArray(R.array.oak_introduction_speech_dialogue)[speechDialoguePosition], this))
        } else if (speechDialoguePosition == 13) {
            speechDialoguePosition++
            oakFadeOut()
            displayText(StringVariableParser().ParseString(resources.getStringArray(R.array.oak_introduction_speech_dialogue)[speechDialoguePosition], this))
        } else {
            val intent = Intent(this, FirstTimePlayerChooseStarterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun showInputPrompt(inputPromptText: String?, nextSpeechButton: ImageButton?) {
        val inflater = LayoutInflater.from(this)
        val promptsView = inflater.inflate(R.layout.input_prompt_dialog, null)
        val promptText = promptsView.findViewById<TextView>(R.id.editTextDialogUserPrompt)
        promptText.text = inputPromptText
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(promptsView)
        val userInput = promptsView
            .findViewById<View>(R.id.editTextDialogUserInput) as EditText
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("Submit"
            ) { dialog, id ->
                playerName = userInput.text.toString()
                val preferences = PreferenceManager.getDefaultSharedPreferences(this@FirstTimePlayerWelcomeScreenActivity)
                val editor = preferences.edit()
                editor.putString("playerName", playerName)
                editor.apply()
                nextSpeechButton!!.performClick()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    var fitnessActivityResultCaller = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data != null) {
                val currentFitness = data.getStringExtra("fitness")
                val preferences = PreferenceManager.getDefaultSharedPreferences(this@FirstTimePlayerWelcomeScreenActivity)
                val editor = preferences.edit()
                editor.putString("playerCurrentFitness", currentFitness)
                editor.apply()
                nextSpeechButton1!!.performClick()
            }
        }
    }
}