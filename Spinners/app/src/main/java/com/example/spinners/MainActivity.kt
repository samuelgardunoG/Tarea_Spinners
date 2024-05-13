package com.example.spinners

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var TextView: TextView
    private lateinit var selectedLanguage: Locale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.myButton)
        TextView = findViewById(R.id.titleTextView)

        // Lista de países e idiomas
        val countryLanguageMap = mapOf(
            "E.U.A." to Locale("en", "US"),
            "México" to Locale("es", "MX"),
            "Brasil" to Locale("pt", "BR"),
            "Francia" to Locale("fr", "FR"),
            "Alemania" to Locale("de", "DE"),
            "Italia" to Locale("it", "IT")
        )


        // Adaptador para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countryLanguageMap.keys.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Manejar la selección del Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectedLanguage = countryLanguageMap.values.toList()[position]


                updateButtonText()
                updateTitleText()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        // Manejar el clic del botón
        button.setOnClickListener {
            val welcomeMessage = getWelcomeMessage()
            showAlert(welcomeMessage)
        }
    }

    private fun updateButtonText() {
        button.text = getButtonText()
    }

    private fun updateTitleText() {
        TextView.text = getTitleText()
    }

    private fun getWelcomeMessage(): String {
        return when (selectedLanguage.language) {
            "es" -> "¡Bienvenido!"
            "en" -> "Welcome!"
            "fr" -> "Bienvenue!"
            "pt" -> "Bem-vindo!"
            "de" -> "Willkommen!"
            "it" -> "Benvenuto!"
            else -> "Welcome!"
        }
    }

    private fun getButtonText(): String {
        return when (selectedLanguage.language) {
            "es" -> "¡Siguiente!"
            "en" -> "Next!"
            "fr" -> "Suivant!"
            "pt" -> "Próximo!"
            "de" -> "Weiter!"
            "it" -> "Successivo!"
            else -> "Next!"
        }
    }

    private fun getTitleText(): String {
        return when (selectedLanguage.language) {
            "es" -> "¡Selecciona el país!"
            "en" -> "Select the country!"
            "fr" -> "Sélectionnez le pays!"
            "pt" -> "Selecione o país!"
            "de" -> "Wählen Sie das Land aus!"
            "it" -> "Seleziona il paese!"
            else -> "Select the country!"
        }
    }




    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alert_title))
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
}
