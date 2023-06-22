package com.example.gestionepersistanza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestionepersistanza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db_Manager:DBManger

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db_Manager= DBManger(this) //contesto che passo alla classe DBManager
        db_Manager.open()
        val cursor=db_Manager.fetchAll() //restituisce tutte le informazioni all'interno del DB

        //controllo se il db è popolato
        if (cursor.count!=0){
            //LAYOUT MANAGER:Componente che dispone i singoli elementi dell'elenco
            binding.reciclerView.layoutManager=LinearLayoutManager(this) //il costruttore vuole il contesto dell'activity corrente
            //Configurazione dell’Adapter con la sorgente dati
            binding.reciclerView.adapter=CustomAdapter(cursor)//setto l'adapter con l'istanza della classe adapter in cui passo cursor
        } else {
            Toast.makeText()
        }

    }
}



