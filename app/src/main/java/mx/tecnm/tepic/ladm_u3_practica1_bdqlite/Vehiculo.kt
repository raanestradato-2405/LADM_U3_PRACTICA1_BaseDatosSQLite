package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.ContentValues
import android.content.Context

class Vehiculo (p: Context) {

    var placa = ""
    var marca= ""
    var modelo = ""
    var year = 0
    var idrepartidor = 0
    val puntero = p

    fun insert() : Boolean {
        val tablaVehiculo = BaseDatos(puntero, "BDPractica1",null,1).writableDatabase

        val datos = ContentValues()
        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("year",year)
        datos.put("idrepartidor",idrepartidor)

        val resultado = tablaVehiculo.insert("VEHICULO",null,datos)

        if(resultado==-1L){
            return false
        }
        return true
    }

    fun consultar() : ArrayList<String>{

        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase

        val resultado = ArrayList<String>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                //Leer la Data
                var dato = cursor.getString(1)+" - "+cursor.getString(2)+"\n"+cursor.getString(3)+" - "+cursor.getInt(4)
                resultado.add(dato)

            }while (cursor.moveToNext())

        }
        else{
            //Si moveToFirst retorna falso es porque no existio ningun resultado
            resultado.add("No Se Encontro Datos ")
        }

        return resultado
    }
    fun obtenerIDs() : ArrayList<Int>{

        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if(cursor.moveToFirst()){
            do {
                resultado.add(cursor.getInt(0))
            }while (cursor.moveToNext())
        }
        return resultado
    }
    fun eliminar(idElimianr:Int) : Boolean {
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).writableDatabase
        val resultado = tablaVehiculo.delete("VEHICULO","IDVEHICULO=?", arrayOf(idElimianr.toString()))
        if(resultado == 0) return false

        return true

    }

    fun consultar(idABuscar:String) : Vehiculo{
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"IDVEHICULO=?", arrayOf(idABuscar),null,null,null)
        val vehiculo = Vehiculo(MainActivity4())

        if(cursor.moveToFirst()){
            vehiculo.placa = cursor.getString(1)
            vehiculo.marca = cursor.getString(2)
            vehiculo.modelo = cursor.getString(3)
            vehiculo.year = cursor.getInt(4)
            vehiculo.idrepartidor = cursor.getInt(5)
        }
        return vehiculo
    }

    fun actualizarVehiculo(idActualizar:String):Boolean{
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase

        val datos = ContentValues()
        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("year",year)
        datos.put("IDREPARTIDOR",idrepartidor)

        val resultado = tablaVehiculo.update("VEHICULO",datos,"IDVEHICULO=?", arrayOf(idActualizar))
        if(resultado==0) return false
        return true
    }
}