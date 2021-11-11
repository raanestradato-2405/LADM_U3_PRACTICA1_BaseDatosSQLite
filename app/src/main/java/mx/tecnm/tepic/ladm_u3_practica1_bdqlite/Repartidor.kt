package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.ContentValues
import android.content.Context

class Repartidor (p:Context) {

    var nombre = ""
    var domicilio = ""
    var noLicencia = ""
    var vence = ""
    val puntero = p

    fun insert() : Boolean {
        val tablaRepartidor = BaseDatos(puntero, "BDPractica1",null,1).writableDatabase

        val datos = ContentValues()
        datos.put("nombre",nombre)
        datos.put("Domicilio",domicilio)
        datos.put("noLicencia",noLicencia)
        datos.put("vence",vence)

        val resultado = tablaRepartidor.insert("REPARTIDOR",null,datos)

        //El metodo insert regresa un ID unico de renglon de la tabla. Regresa -1 si no se inserto

        if(resultado==-1L){
            return false
        }
        return true
    }

    fun consultar() : ArrayList<String>{

        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase

        val resultado = ArrayList<String>()
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"),null,null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta

        if(cursor.moveToFirst()){
            //Si se posiciona en el primer renglor resultado si se obtuvo un resultado
            do {
                //Leer la Data
                var dato = cursor.getString(1)+" - "+cursor.getString(2)+"\n"+cursor.getString(3)+" - "+cursor.getString(4)+ "("+cursor.getInt(0)+")"
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

        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"),null,null,null,null,null)
        if(cursor.moveToFirst()){
            do {
                resultado.add(cursor.getInt(0))
            }while (cursor.moveToNext())
        }
        return resultado
    }
    fun eliminar(idElimianr:Int) : Boolean {
        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).writableDatabase
        val resultado = tablaRepartidor.delete("REPARTIDOR","IDREPARTIDOR=?", arrayOf(idElimianr.toString()))
        if(resultado == 0) return false

        return true

    }

    fun consultar(idABuscar:String) : Repartidor{
        val tablaArtista = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaArtista.query("REPARTIDOR", arrayOf("*"),"IDREPARTIDOR=?", arrayOf(idABuscar),null,null,null)
        val repartidor = Repartidor(MainActivity2())
        if(cursor.moveToFirst()){
            repartidor.nombre = cursor.getString(1)
            repartidor.domicilio = cursor.getString(2)
            repartidor.noLicencia = cursor.getString(3)
            repartidor.vence = cursor.getString(4)
        }
        return repartidor
    }

    fun actualizar(idActualizar:String):Boolean{
        val tablaArtista = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val datos = ContentValues()

        datos.put("nombre",nombre)
        datos.put("Domicilio",domicilio)
        datos.put("noLicencia",noLicencia)
        datos.put("vence",vence)

        val resultado = tablaArtista.update("REPARTIDOR",datos,"IDREPARTIDOR=?", arrayOf(idActualizar))
        if(resultado==0) return false
        return true
    }
}