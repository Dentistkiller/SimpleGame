package com.example.simplegame

import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //you're declaring a variable userInput that will be initialized later(lateinit var) and is of type EditText
    private lateinit var userInput: EditText
    private lateinit var submitButton: Button
    private lateinit var messageTextView: TextView
    //range between 0 and 100
    private var secretNumber: Int = (1..100).random()
    //number of attempts
    private var attempts: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing "userInput" declared earlier and linking it to the user_input edit text in the xml file
        userInput = findViewById(R.id.user_input)
        submitButton = findViewById(R.id.submit_button)
        messageTextView = findViewById(R.id.message_text_view)

        //button event "click"
        submitButton.setOnClickListener {
            //what happens when the button is clicked
            checkGuess()
        }
    }
//"fun" is method
// method to check input number vs generated number
    private fun checkGuess() {
        //get user input as string
        val userGuess = userInput.text.toString().toIntOrNull()
        //if userGues is not null
        if (userGuess != null) {
            //attemps count increases
            attempts++
            when {
                //user input == generated number
                userGuess == secretNumber -> {
                    showMessage("Congratulations! You guessed the number in $attempts attempts.")
                    disableInput()
                }
                userGuess < secretNumber -> showMessage("Too low! Try again.")
                else -> showMessage("Too high! Try again.")
            }
        } else {
            showMessage("Please enter a valid number.")
        }
        userInput.text.clear()
    }

    //method to change message in textview to message from above
    private fun showMessage(message: String) {
        messageTextView.text = message
    }

    //method to disable button and input from edit text
    private fun disableInput() {
        userInput.isEnabled = false
        submitButton.isEnabled = false
    }
}