package com.example.gestionepersistanza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gestionepersistanza.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityModifyBinding
    private lateinit var db_Manager:DBManger
    private lateinit var data: Bundle


    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityModifyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db_Manager= DBManger(this) //contesto che passo alla classe DBManager
        db_Manager.open()

        val bottoneModify=binding.buttonModify
        bottoneModify.setOnClickListener {
            val id=binding.IdModify.text.toString().toLong()
            val subject=binding.editSubject.text.toString()
            val description=binding.editDescription.toString()
            db_Manager.update(id,subject,description)
            returnBack()
        }

        val bottoneDelete=binding.buttonDelete
        bottoneDelete.setOnClickListener {
            val id=binding.IdDelete.text.toString().toLong()
            db_Manager.delete(id)
            returnBack()
        }

        /*vedi conc1*/
    }

    fun returnBack(){
        val intent= Intent(this, MainActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        //Questo flag viene utilizzato per cancellare lo stack di attività, rimuovendo
        // tutte le attività che si trovano sopra l'attività di destinazione.
        // Pertanto, se sono presenti attività tra l'attività corrente e MainActivity, verranno rimosse.
        startActivity(intent)
    }

    override fun onDestroy() {
        db_Manager.close()
        super.onDestroy()
    }
}