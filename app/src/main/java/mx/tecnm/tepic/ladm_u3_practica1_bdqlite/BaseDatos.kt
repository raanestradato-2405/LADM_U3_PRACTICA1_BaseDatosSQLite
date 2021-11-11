package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p: SQLiteDatabase) {
        p.execSQL("CREATE TABLE REPARTIDOR (IDREPARTIDOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(200),DOMICILIO VARCHAR(200), NOLICENCIA VARCHAR(200), VENCE DATE)")

        p.execSQL("CREATE TABLE VEHICULO (IDVEHICULO INTEGER PRIMARY KEY AUTOINCREMENT, PLACA VARCHAR(200),MARCA VARCHAR(200),MODELO VARCHAR(200),YEAR INTEGER,IDREPARTIDOR INTEGER, FOREIGN KEY (IDREPARTIDOR) REFERENCES REPARTIDOR (IDREPARTIDOR))")

        p.execSQL("CREATE TABLE PEDIDO (IDPEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRECLIENTE VARCHAR(200),PDOMICILIO VARCHAR(200), TELEFONO VARCHAR(100), DESCRIPCION VARCHAR(500),TOTAL INTEGER,IDREPARTIDOR INTEGER, FOREIGN KEY (IDREPARTIDOR) REFERENCES REPARTIDOR (IDREPARTIDOR))")

    }

    override fun onUpgrade(p: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}