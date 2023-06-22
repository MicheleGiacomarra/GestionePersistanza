package com.example.gestionepersistanza

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//contiene tutte le informazioni del database e delle tabelle
class DbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //RENDO TUTTE LE VARIABILI STATICHE, IN MODO DA
    //RENDERLE ACCESSIBILI DALL'ESTERNO
    companion object {
        //NOME DATABASE
        const val DATABASE_NAME="COUNTRIES_DATABASE"

        //NOME TABELLA
        const val TABLE_NAME = "COUNTRIES"

        //COLONNE TABELLA
        const val COLUMN_ID = "id"
        const val COLUMN_SUBJECT: String = "Subject"
        const val COLUMN_DESCRIPTION = "Description"

        //DATABESE VERSION(qualora decidessimo di aggiornare intermini di versione il DB)
        const val DATABASE_VERSION=1

        //STRINGA PER CREARE LA TABELLA
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TABLE_NAME} (" +
                    "${COLUMN_ID} INTEGER PRIMARY KEY," +
                    "${COLUMN_SUBJECT} TEXT NOT NULL," +
                    "${COLUMN_DESCRIPTION} TEXT);"

        //STRINGA PER ELIMINARE LA TABELLA
        private const val SQL_DELETE_ENTRIES=
            "DROP TABLE IF EXISTS ${TABLE_NAME};"
        //Questa variabile contiene l'istruzione SQL per eliminare una tabella, se presente.
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
        //il metodo execSQL viene utilizzato per eseguire istruzioni SQL come la creazione di tabelle,
        // l'inserimento di dati, l'aggiornamento dei dati o la rimozione di record.
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //analogo di db?.execSQL(SQL_DELETE_ENTRIES)
        if (db != null) {
            db.execSQL(SQL_DELETE_ENTRIES)
        }
        //simuliamo la cancellazione di tutto quello che c'è all'interno della tabella
        onCreate(db)
        //invoco onCreate per ricrearla
    }
}