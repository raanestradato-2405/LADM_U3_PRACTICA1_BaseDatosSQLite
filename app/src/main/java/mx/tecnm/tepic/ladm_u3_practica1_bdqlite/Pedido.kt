package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.ContentValues
import android.content.Context

class Pedido (p:Context) {
    var nombrecliente = ""
    var pdomicilio= ""
    var telefono = ""
    var descripcion = ""
    var total = 0
    var idrepartidor = 0
    val puntero = p

    fun insert() : Boolean {
        val tablaPedido = BaseDatos(puntero, "BDPractica1",null,1).writableDatabase

        val datos = ContentValues()
         datos.put("NOMBRECLIENTE",nombrecliente)
         datos.put("PDOMICILIO",pdomicilio)
         datos.put("TELEFONO",telefono)
         datos.put("DESCRIPCION",descripcion)
         datos.put("TOTAL",total)
         datos.put("IDREPARTIDOR",idrepartidor)

        val resultado = tablaPedido.insert("PEDIDO",null,datos)

        if(resultado==-1L){
            return false
        }
        return true
    }

    fun consultar() : ArrayList<String>{

        val tablaPedido = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase

        val resultado = ArrayList<String>()
        val cursor = tablaPedido.query("PEDIDO", arrayOf("*"),null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                //Leer la Data
                var dato = cursor.getString(1)+" - "+cursor.getString(2)+" - "+cursor.getString(3) +" \n"+cursor.getString(4) + "\n"+cursor.getInt(5)
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

        val tablaPedido = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaPedido.query("PEDIDO", arrayOf("*"),null,null,null,null,null)
        if(cursor.moveToFirst()){
            do {
                resultado.add(cursor.getInt(0))
            }while (cursor.moveToNext())
        }
        return resultado
    }
    fun eliminar(idElimianr:Int) : Boolean {
        val tablaPedido = BaseDatos(puntero,"BDPractica1",null,1).writableDatabase
        val resultado = tablaPedido.delete("PEDIDO","IDPEDIDO=?", arrayOf(idElimianr.toString()))
        if(resultado == 0) return false

        return true

    }

    fun consultar(idABuscar:String) : Pedido{
        val tablaPedido = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaPedido.query("PEDIDO", arrayOf("*"),"IDPEDIDO=?", arrayOf(idABuscar),null,null,null)
        val pedido = Pedido(MainActivity6())

        if(cursor.moveToFirst()){
            pedido.nombrecliente = cursor.getString(1)
            pedido.pdomicilio = cursor.getString(2)
            pedido.telefono = cursor.getString(3)
            pedido.descripcion = cursor.getString(4)
            pedido.total = cursor.getInt(5)
            pedido.idrepartidor = cursor.getInt(6)
        }
        return pedido
    }

    fun actualizarPedido(idActualizar:String):Boolean{
        val tablaPedido = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase

        val datos = ContentValues()
        datos.put("NOMBRECLIENTE",nombrecliente)
        datos.put("PDOMICILIO",pdomicilio)
        datos.put("TELEFONO",telefono)
        datos.put("DESCRIPCION",descripcion)
        datos.put("TOTAL",total)
        datos.put("IDREPARTIDOR",idrepartidor)

        val resultado = tablaPedido.update("PEDIDO",datos,"IDPEDIDO=?", arrayOf(idActualizar))
        if(resultado==0) return false
        return true
    }

}