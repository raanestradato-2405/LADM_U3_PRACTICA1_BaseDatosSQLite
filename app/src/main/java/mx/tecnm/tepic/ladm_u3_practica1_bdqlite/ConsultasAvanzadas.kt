package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class ConsultasAvanzadas (p:Context) {

    val puntero = p




//--------------------------------------------------------------    CONSULTA 1---------------------------------------------------
    fun consultar() : String{

        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"),"VENCE < date('now')", null,null,null,null)
        val repartidor = Repartidor(MainActivity8())
        var resultadoC1 = ""
        if(cursor.moveToFirst()){
            do{
            repartidor.nombre = cursor.getString(1)
            repartidor.domicilio = cursor.getString(2)
            repartidor.noLicencia = cursor.getString(3)
            repartidor.vence = cursor.getString(4)
            resultadoC1 = resultadoC1 + repartidor.nombre+ ", "+repartidor.domicilio+", "+repartidor.noLicencia+", "+repartidor.vence+"\n"
            } while (cursor.moveToNext())
        }
        return resultadoC1
    }
//------------------------------------------------------------CONSULTA 2-------------------------------------------------------------

    fun consulta2() : String{
        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"), "IDREPARTIDOR NOT IN (SELECT IDREPARTIDOR FROM VEHICULO) ",null,null,null,null)

        val repartidor = Repartidor(MainActivity8())
        var resultadoC2 = ""
        if(cursor.moveToFirst()){
            do{
                repartidor.nombre = cursor.getString(1)
                repartidor.domicilio = cursor.getString(2)
                repartidor.noLicencia = cursor.getString(3)
                repartidor.vence = cursor.getString(4)

                resultadoC2 = resultadoC2  + repartidor.nombre+", "+repartidor.domicilio+", "+repartidor.noLicencia+", "+repartidor.vence+"\n"
            } while (cursor.moveToNext())
        }
        else{
            resultadoC2 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC2

    }
//------------------------------------------------------------CONSULTA 3-------------------------------------------------------------
    fun consulta3(years: Int) : String{
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"YEAR=?",arrayOf(years.toString()),null,null,null)
        val vehiculo = Vehiculo(MainActivity8())
        var resultadoC3 = ""
        if(cursor.moveToFirst()){
            do {
                vehiculo.placa = cursor.getString(1)
                vehiculo.marca = cursor.getString(2)
                vehiculo.modelo = cursor.getString(3)
                vehiculo.year = cursor.getInt(4)

                resultadoC3 = resultadoC3 + vehiculo.placa + ", " + vehiculo.marca + ", " + vehiculo.modelo + ", " + vehiculo.year + "\n"
            } while (cursor.moveToNext())
        }
        else{
            resultadoC3 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC3
    }
//----------------------------------------------------CONUSLTA 4-------------------------------------------------------------------------
    fun obtenerIdConsulta4(nombreCondutor: String): Int {
        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"),"NOMBRE=?",arrayOf(nombreCondutor),null,null,null)
        var resultadoC3 = 0
        if(cursor.moveToFirst()){
            resultadoC3 = cursor.getInt(0)
        }
        else{
            resultadoC3 = 0
        }
        return resultadoC3

    }

    fun consulta4(idConductor: Int): String {
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"IDREPARTIDOR=?", arrayOf(idConductor.toString()),null,null,null)
        val vehiculo = Vehiculo(MainActivity8())
        var resultadoC4 = ""
        if(cursor.moveToFirst()){
            do{
            vehiculo.placa = cursor.getString(1)
            vehiculo.marca = cursor.getString(2)
            vehiculo.modelo = cursor.getString(3)
            vehiculo.year = cursor.getInt(4)

            resultadoC4 = resultadoC4  + vehiculo.placa+", "+vehiculo.marca+", "+vehiculo.modelo+", "+vehiculo.year+"\n"
            } while (cursor.moveToNext())
        }
        else{
            resultadoC4 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC4
    }

    //-------------------------------------------   CONSULTA 5 ------------------------------------------------------------------
    fun obtenerIdConsulta5(placaVehiculo: String): Int {
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"PLACA=?",arrayOf(placaVehiculo),null,null,null)

        var resultadoC5 = 0
        if(cursor.moveToFirst()){
            resultadoC5 = cursor.getInt(5)
        }
        else{
            resultadoC5 = 0
        }
        return resultadoC5

    }

    fun consulta5(idRepartidor: Int): String {
        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"),"IDREPARTIDOR=?", arrayOf(idRepartidor.toString()),null,null,null)
        val repartidor = Repartidor(MainActivity8())
        var resultadoC5 = ""
        if(cursor.moveToFirst()){
            do{
            repartidor.nombre = cursor.getString(1)
            repartidor.domicilio = cursor.getString(2)
            repartidor.noLicencia = cursor.getString(3)
            repartidor.vence = cursor.getString(4)


            resultadoC5 = resultadoC5  + repartidor.nombre+", "+repartidor.domicilio+", "+repartidor.noLicencia+", "+repartidor.vence+"\n"
            } while (cursor.moveToNext())
        }
        else{
            resultadoC5 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC5
    }

    //--------------------------------------------------------------CONSULTA 6 ---------------------------------------------------------------------
    fun consulta6(): String {
        val tablaVehiculo = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null, null,null,null,null)
        val vehiculo = Vehiculo(MainActivity8())
        var resultadoC6 = ""
        if(cursor.moveToFirst()) {

            do {
                vehiculo.placa = cursor.getString(1)
                vehiculo.marca = cursor.getString(2)
                vehiculo.modelo = cursor.getString(3)
                vehiculo.year = cursor.getInt(4)

                resultadoC6 = resultadoC6 + vehiculo.placa + ", " + vehiculo.marca + ", " + vehiculo.modelo + ", " + vehiculo.year + "\n"

            } while (cursor.moveToNext())
        }
        else{
            resultadoC6 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC6
    }
    //--------------------------------------------------------------CONSULTA 7 -----------------------------------------------------------
    fun consulta7(): String {
        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"),null, null,null,null,null)
        val repartidor = Repartidor(MainActivity8())
        var resultadoC7 = ""
        if(cursor.moveToFirst()) {

            do {
                repartidor.nombre = cursor.getString(1)
                repartidor.domicilio = cursor.getString(2)
                repartidor.noLicencia = cursor.getString(3)
                repartidor.vence = cursor.getString(4)

                resultadoC7 = resultadoC7 + repartidor.nombre+", "+repartidor.domicilio+", "+repartidor.noLicencia+", "+repartidor.vence+"\n"

            } while (cursor.moveToNext())
        }
        else{
            resultadoC7 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC7
    }

    //---------------------------------------------------------------- CONSULTA 8----------------------------------------------------

    fun consulta8() : String{
        val tablaRepartidor = BaseDatos(puntero,"BDPractica1",null,1).readableDatabase
        val cursor = tablaRepartidor.query("REPARTIDOR", arrayOf("*"), "IDREPARTIDOR IN (SELECT IDREPARTIDOR FROM VEHICULO) ",null,null,null,null)
        val repartidor = Repartidor(MainActivity8())
        val vehiculo = Vehiculo(MainActivity8())
        var resultadoC8 = ""
        if(cursor.moveToFirst()){
            do{
                repartidor.nombre = cursor.getString(1)
                repartidor.domicilio = cursor.getString(2)
                repartidor.noLicencia = cursor.getString(3)
                repartidor.vence = cursor.getString(4)

                vehiculo.placa = cursor.getString(1)
                vehiculo.modelo = cursor.getString(2)
                vehiculo.marca = cursor.getString(3)
                vehiculo.year = cursor.getInt(4)

                resultadoC8 = resultadoC8  + repartidor.nombre+", "+repartidor.domicilio+", "+repartidor.noLicencia+", "+repartidor.vence+", "+vehiculo.placa+ ", "+vehiculo.modelo+ ", " + vehiculo.marca+ ", "+vehiculo.year+ "\n"
            } while (cursor.moveToNext())
        }
        else{
            resultadoC8 = "NO SE ENCONTRARON RESULTADOS"
        }
        return resultadoC8
    }

}