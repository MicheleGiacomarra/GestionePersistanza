package com.example.gestionepersistanza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gestionepersistanza.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddBinding
    private lateinit var db_Manager:DBManger

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db_Manager= DBManger(this)
        db_Manager.open()

        val bottoneAggiungi=binding.buttonAdd
        bottoneAggiungi.setOnClickListener {
            val subject=binding.editSubject.text.toString()
            val description=binding.editDescription.text.toString()
            db_Manager.insert(subject,description)

            val intent= Intent(this, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            //Questo flag viene utilizzato per cancellare lo stack di attività, rimuovendo
            // tutte le attività che si trovano sopra l'attività di destinazione.
            // Pertanto, se sono presenti attività tra l'attività corrente e MainActivity, verranno rimosse.
            startActivity(intent)

        }
    }
}