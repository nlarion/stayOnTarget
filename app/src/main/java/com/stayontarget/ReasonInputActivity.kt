package com.stayontarget

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class ReasonInputActivity : Activity() {
    private lateinit var textViewPrompt: TextView
    private lateinit var editTextReason: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var buttonCancel: Button
    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reason_input)

        notificationHelper = NotificationHelper(this)
        
        textViewPrompt = findViewById(R.id.textViewPrompt)
        editTextReason = findViewById(R.id.editTextReason)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        buttonCancel = findViewById(R.id.buttonCancel)

        setRandomPrompt()

        buttonSubmit.setOnClickListener {
            val reason = editTextReason.text.toString().trim()
            if (reason.isNotEmpty()) {
                notificationHelper.showTaskNotification(reason)
                Toast.makeText(this, "Task tracked: $reason", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please enter a reason", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancel.setOnClickListener {
            finish()
        }

        editTextReason.requestFocus()
        showKeyboard()
    }

    override fun onNewIntent(intent: android.content.Intent?) {
        super.onNewIntent(intent)
        editTextReason.setText("")
        editTextReason.requestFocus()
        setRandomPrompt()
        showKeyboard()
    }

    private fun setRandomPrompt() {
        val prompts = resources.getStringArray(R.array.unlock_prompts)
        val randomPrompt = prompts[Random.nextInt(prompts.size)]
        textViewPrompt.text = randomPrompt
    }

    private fun showKeyboard() {
        editTextReason.postDelayed({
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editTextReason, InputMethodManager.SHOW_IMPLICIT)
        }, 100)
    }
}