package com.example.datetime

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ActivityTwo : AppCompatActivity() {

    private lateinit var toolbarTwo: Toolbar
private lateinit var NameTV: TextView
private lateinit var secondNameTV: TextView
private lateinit var BirthdayTV:TextView
private lateinit var editImageTV:ImageView
private lateinit var PhoneTV: TextView
private lateinit var daysUntilBirthdayTextView: TextView

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        toolbarTwo = findViewById(R.id.toolbarTwo)
        setSupportActionBar(toolbarTwo)

        NameTV = findViewById(R.id.NameTV)
        secondNameTV = findViewById(R.id.secondNameTV)
        BirthdayTV = findViewById(R.id.BirthdayTV)
        editImageTV = findViewById(R.id.editImageTV)
        PhoneTV = findViewById(R.id.PhoneTV)
        daysUntilBirthdayTextView = findViewById(R.id.daysUntilBirthdayTextView)

        val name = intent.getStringExtra("NAME")
        val surname = intent.getStringExtra("SECONDNAME")
        val birthDateString = intent.getStringExtra("BIRTHDAY")
        val phone = intent.getStringExtra("PHONE")
        val photoUriString = intent.getStringExtra("PHOTO_URI")

        NameTV.text = "Имя: $name"
        secondNameTV.text = "Фамилия: $surname"
        PhoneTV.text = "Номер телефона: $phone"
        editImageTV.setImageURI(Uri.parse(photoUriString))


        if (birthDateString != null) {
            val birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            val currentDate = LocalDate.now()
            val age = calculateAge(birthDate, currentDate)
            BirthdayTV.text = "Возраст: ${age.first} лет, ${age.second} месяцев, ${age.third} дней"
        }

        if (birthDateString != null) {
            val birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            val daysUntilBirthday = calculateDaysUntilBirthday(birthDate)
            daysUntilBirthdayTextView.text = "Осталось до ДР: $daysUntilBirthday дней(дня)"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(birthDate: LocalDate, currentDate: LocalDate): Triple<Int, Int, Int> {
        val period = Period.between(birthDate, currentDate)
        return Triple(period.years, period.months, period.days)
    }

    @RequiresApi(Build.VERSION_CODES.O)

    private fun calculateDaysUntilBirthday(birthDate: LocalDate): Long {
        val currentDate = LocalDate.now()
        var nextBirthday = birthDate.withYear(currentDate.year)

        if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
            nextBirthday = nextBirthday.plusYears(1)
        }

        return ChronoUnit.DAYS.between(currentDate, nextBirthday)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_exit -> finishAffinity()
        }

        Toast.makeText(applicationContext, "Работа завершена", Toast.LENGTH_LONG).show()

        return super.onOptionsItemSelected(item)
    }
}