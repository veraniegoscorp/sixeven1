package com.example.navegacionpermisos

import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Importaciones para hacer funcionar el codigo
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.content.Intent

class MainActivity : AppCompatActivity() {
    // Establecer variables para los componentes de la vista
    private lateinit var etTexto: EditText
    private lateinit var btnEnviar: Button
    private lateinit var btnPermiso: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Imprimir un Log en la terminal (Logcat)
        Log.d("MainActivity", "onCreate ejecutado")
        // Llamar la funcion para rescatar los elementos de la UI
        initViews()
        // Llamar la funcion para estar atento a la acciones de los elementos en la UI
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        // Imprimir un Log en la terminal (Logcat)
        Log.d("MainActivity", "onStart ejecutado")
    }

    // Funcion que va a ir a buscar los elementos de la UI
    private fun initViews() {
        etTexto = findViewById(R.id.etTexto) // Establece el campo editable de texto
        btnEnviar = findViewById(R.id.btnEnviar) // Establece boton para enviar data a Detalle
        btnPermiso = findViewById(R.id.btnPermiso) // Boton que pide un permiso de telefono
    }

    // Gestionar los eventos de escucha en caso de que el usuario interactue con la UI
    private fun setupListeners() {
        btnEnviar.setOnClickListener {
            val textoEnviado = etTexto.text.toString()

            if (textoEnviado.isNotEmpty()) {
                val intent = Intent(this, DetalleActivity::class.java)
                intent.putExtra("DATO_ENVIADO", textoEnviado)
                startActivity(intent)
            } else {
                // Mostrar mensaje si está vacío
                etTexto.error = "Ingresa un texto"
            }
        }
    }
}