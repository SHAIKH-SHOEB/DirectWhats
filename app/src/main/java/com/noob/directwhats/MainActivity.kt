package com.noob.directwhats

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find variables
        val numberEv: EditText = findViewById(R.id.whatsNumber)
        val messageEv: EditText = findViewById(R.id.whatsMessage)
        val sendBn: Button = findViewById(R.id.SendBn)

        //set on click listener on button
        sendBn.setOnClickListener {
            if (numberEv.text.isNotEmpty()) {
                val packageManager: PackageManager = packageManager
                val i = Intent(Intent.ACTION_VIEW)
                val url: String =
                    "https://api.whatsapp.com/send?phone=+91" + numberEv.text.toString() + "&text=" + URLEncoder.encode(
                        messageEv.text.toString(),
                        "UTF-8"
                    )
                i.setPackage("com.whatsapp")
                i.data = Uri.parse(url)
                if (i.resolveActivity(packageManager) != null) {
                    startActivity(i)
                } else {
                    Toast.makeText(this, "Whatsapp Are Not Install Your Device", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Please Enter Number !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //call menu function and inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_nav -> {
                Toast.makeText(this, "Share Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.setting_nav -> {
                Toast.makeText(this, "Setting Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.about_nav -> {
                Toast.makeText(this, "About Selected", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}