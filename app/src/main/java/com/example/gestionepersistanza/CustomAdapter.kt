package com.example.gestionepersistanza

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionepersistanza.databinding.CardViewDesignBinding

/*ADAPTER: Componente responsabile dell’estrazione dei dati dal Data Source e di usare questi per creare e popolare il db*/
class CustomAdapter(private val cursor: Cursor):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    //passo un cursore perchè è il risultato della mia query

    //ha il compito di creare un contenitore vuoto con gli elementi contenuti nel file xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //gonfia la vista card_view_design utilizzata per contenere l'elemento dell'elenco
        val view=CardViewDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) //restituisco un instanza di viewHolder
    }

    override fun getItemCount(): Int {
        return cursor.count //restituisce il numero di righe presenti nel cursore
        //questo valore verrà utilizzato per determinare quanti elementi verranno visualizzati nel RecyclerView 
    }

    //ha il compito di collegare l’Holder ai dati
    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //devo aggiornare il viewHolder con in dati del database
        if(cursor.moveToPosition(position)){ //se è presente un record alla i-posizione
            holder.numero.text=cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMN_ID)).toString()
            holder.nomeMateria.text=cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_SUBJECT)).toString()
            holder.nomeProf.text=cursor.getString(cursor.getColumnIndex(DbHelper.TABLE_NAME)).toString()
            //aggiungere l’OnClickListener all’oggetto itemView della CardView richiamando il metodo setOnClickListener
            holder.itemView.setOnClickListener {
                //passo gli oggetti con l'intent
                val intent=Intent(holder.itemView.context, ModifyActivity::class.java)
                intent.putExtra("ID", holder.numero.text)
                intent.putExtra("Subject", holder.nomeMateria.text)
                intent.putExtra("Description", holder.nomeMateria.text)
                holder.itemView.context.startActivity(intent)
            }
        }

    }

    /*VIEWHOLDER: Componente che definisce ogni elemento (layout) nella lista dinamica.
    Quando un nuovo View Holder viene creato, esso non contiene alcun dato.
    Viene popolato dal RecyclerView tramite l’operazione di data binding svolta dall’Adapter.*/
    //Creo la classe ViewHolder come estensione di RecyclerView.ViewHolder
    class ViewHolder(binding: CardViewDesignBinding):RecyclerView.ViewHolder(binding.root){
        //ViewHolder() crea il contenitore vuoto che verrà riempito con i dati dall’Adapter
        //Il ViewHolder tiene traccia dell’oggetto binding che lo collega a card_view_design.xml -> binding: CardViewDesignBinding
        var numero=binding.numero
        var nomeMateria=binding.nomeMateria
        var nomeProf=binding.nomeProf
    }

}