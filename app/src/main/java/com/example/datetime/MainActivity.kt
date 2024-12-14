package com.example.datetime

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val GALERRY_REQUEST = 1
    var photoUri: Uri? = null



    private lateinit var toolbarMain: Toolbar
    private lateinit var NameET:EditText
    private lateinit var secondNameET:EditText
private lateinit var BirthdayET:EditText
private lateinit var editImageIV:ImageView
private lateinit var saveBTN:Button
private lateinit var PhoneET: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain = findViewById(R.id.toolbarMain)
        NameET = findViewById(R.id.NameET)
        secondNameET = findViewById(R.id.secondNameET)
        BirthdayET = findViewById(R.id.BirthdayET)
        editImageIV = findViewById(R.id.editImageIV)
        saveBTN = findViewById(R.id.saveBTN)
        PhoneET = findViewById(R.id.PhoneET)


        editImageIV.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALERRY_REQUEST)
        }




        saveBTN.setOnClickListener {
            val intent = Intent(this, ActivityTwo::class.java)
            intent.putExtra("NAME", NameET.text.toString())
            intent.putExtra("SECONDNAME", secondNameET.text.toString())
            intent.putExtra("BIRTHDAY", BirthdayET.text.toString())
            intent.putExtra("PHONE", PhoneET.text.toString())
            intent.putExtra("PHOTO_URI", photoUri.toString()) // Передача URI фото
            startActivity(intent)
        }



    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALERRY_REQUEST -> if (resultCode == RESULT_OK) {
                photoUri = data?.data
                editImageIV.setImageURI(photoUri)
            }
        }
    }
}