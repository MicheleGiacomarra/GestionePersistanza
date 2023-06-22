package com.example.gestionepersistanza

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

//CLASSE CHE SI INTERFACCERA' CON IL DbHelper:gestisce l'interazione con il database
class DBManger(val context: Context) {
    private lateinit var helper: DbHelper
    private lateinit var db: SQLiteDatabase

    fun open():DBManger {
        //creo un'istanza di Helper, dove ho definito il db
        helper= DbHelper(context)
        //apro il db un modalità scrittura
        db = helper.writableDatabase
        return this
    }

    fun close(){
        helper.close()
    }

    fun insert(nome:String, descrizione:String){
        //inserisco i valori nel db, dove il nome delle colonne sono la chiave
        val val_toInsert=ContentValues().apply {
            put(DbHelper.COLUMN_SUBJECT, nome)
            put(DbHelper.COLUMN_DESCRIPTION, descrizione)
        }
        //inserisco una nuova riga
        //insert() restituisce l'ID per la riga appena creata, oppure -1 se si è verificato un errore durante l'inserimento dei dati
        val newRowId=db?.insert(DbHelper.TABLE_NAME, null, val_toInsert)
        //arg1: nome della tabella
        //arg2: indica cosa fare nel caso in cui the ContentValues sia vuoto
                //Se specifichi null il framework non inserisce una riga quando non ci
                //sono valori
        //arg3: i valori da inserire
    }

    //Esegue un'operazione di aggiornamento su un database, utilizzando i valori forniti per modificare il record corrispondente all'ID
    fun update(id:Long, nome:String, descrizione:String):Int{
        //viene creato il criterio di selezione per l'aggiornamento del record
        val selection="${DbHelper.COLUMN_ID}=?" //clausola di selezione SQL:colonna che voglio andare a selezionare in base all'id
        val selectionArgs= arrayOf(id.toString())//array che contiene il valore dell'ID convertito in stringa

        //creo un oggetto ContentValues, che viene utilizzato per specificare i nuovi valori dei campi da aggiornare nel record.
        val values=ContentValues().apply {
            put(DbHelper.COLUMN_SUBJECT, nome)
            put(DbHelper.COLUMN_DESCRIPTION,descrizione)
        }
        //viene effettuato l'aggiornamento effettivo del record nel database.
        val n_righe_modificate=db.update(DbHelper.TABLE_NAME,values,selection,selectionArgs)
        return n_righe_modificate
    }

    fun delete(id: Long){
        //viene creato il criterio di selezione
        val selection="${DbHelper.COLUMN_ID}=?" //clausola di selezione SQL:colonna che voglio andare a selezionare in base all'id
        val selectionArgs= arrayOf(id.toString())//array che contiene il valore dell'ID convertito in stringa
        db.delete(DbHelper.TABLE_NAME, selection, selectionArgs)

    }

    //metodo per recuperare tutti i record dal database utilizzando una query
    fun fetchAll():Cursor{
        //Definisco una proiezione(PROJ) che specifichi quali colonne del database voglio ritornate dalla query
        val projection= arrayOf(DbHelper.COLUMN_ID,DbHelper.COLUMN_SUBJECT, DbHelper.COLUMN_DESCRIPTION)

        //Esecuzione della query
        val cursor=db.query(
            DbHelper.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        //sposta il cursore alla prima riga dei risultati.
        cursor?.moveToFirst()
        return cursor
    }

}

