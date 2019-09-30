package com.example.reminder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.model.Reminder

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

const val EXTRA_REMINDER = "EXTRA_REMINDER"

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            this.onSaveClick()
        }
    }

    // Save the reminder
    private fun onSaveClick(){
        val reminderTxt = etAddReminder.text.toString()
        if (reminderTxt.isNotBlank()) {
            // Create the new reminder
            val reminder = Reminder(reminderTxt)
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_REMINDER, reminder)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            // Show error message telling the user that the reminder cannot be empty
            Snackbar.make(etAddReminder, "The reminder cannot be empty!", Snackbar.LENGTH_SHORT).show()
        }
    }
}
