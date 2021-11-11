package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main8.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.jvm.internal.Intrinsics

class MainActivity8 : AppCompatActivity() {
    var indice = 0
    var nombreNota = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        //Bloquear boton exportar para evitar que el usuario lo presione sin antes elegir la consulta
        //Se desbloquea al ingresar una consulta correcta
        btnExportar.isEnabled=false


        btnEjecutarConsultas.setOnClickListener {

            indice = txtIndiceConsulta.text.toString().toInt()

            // Consulta Numero 1-------------------------------------------------------------------------------------
            if(indice == 1) {

                var resultadosC1 = ConsultasAvanzadas(this).consultar()


                txtResultadoConsulta.setText(resultadosC1)
                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = true
            }

            //Consulta Numero 2------------------------------------------------------------------------------------------
            if(indice == 2) {
                var resultadosC2 = ConsultasAvanzadas(this).consulta2()
                txtResultadoConsulta.setText(resultadosC2)
                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = true

            }

            //Consulta Numero 3--------------------------------------------------------------------------------------------
            if(indice == 3) {
                var year = 0
                val c = Calendar.getInstance()

                val yearConsulta = EditText(this)
                yearConsulta.setHint("YEAR N")
                AlertDialog.Builder(this)
                    .setTitle("CONSULTA 3")
                    .setMessage("Ingresa El Valor: ")
                    .setView(yearConsulta)
                    .setPositiveButton("ACEPTAR") {d, i ->
                        var years = (yearConsulta.text.toString()).toInt()
                        year = c.get(Calendar.YEAR) - years
                        var resultadosC3 = ConsultasAvanzadas(this).consulta3(year)
                        txtResultadoConsulta.setText(resultadosC3)
                        //Desbloqueo de boton Exportar para poder Exportar Documento
                        btnExportar.isEnabled = true
                        d.dismiss()
                    }
                    .setNegativeButton("CANCELAR") {d, i ->
                        d.cancel()
                    }
                    .show()

            }

            //Consulta Numero 4-------------------------------------------------------------------------------------------------
            if(indice == 4){
                var nombreCondutor = ""
                val consulta4 = EditText(this)
                consulta4.setHint("Nombre Repartidor")
                AlertDialog.Builder(this)
                    .setTitle("CONSULTA 4")
                    .setMessage("INGRESA EL NOMBRE DEL REPARTIDOR")
                    .setView(consulta4)
                    .setPositiveButton("ACEPTAR") {d, i ->

                        nombreCondutor = consulta4.text.toString()

                        var idConductor = ConsultasAvanzadas(this).obtenerIdConsulta4(nombreCondutor)
                        var resultadoC4 = ConsultasAvanzadas(this).consulta4(idConductor)

                        txtResultadoConsulta.setText(resultadoC4)

                        //Desbloqueo de boton Exportar para poder Exportar Documento
                        btnExportar.isEnabled = true
                        d.dismiss()
                    }
                    .setNegativeButton("CANCELAR") {d, i ->
                        d.cancel()
                    }
                    .show()
            }

            //Consulta Numero 5--------------------------------------------------------------------------------------------------------
            if(indice == 5){
                var placaVehiculo= ""
                val consulta5 = EditText(this)
                consulta5.setHint("Placa Vehiculo")
                AlertDialog.Builder(this)
                    .setTitle("CONSULTA 5")
                    .setMessage("INGRESA LA PLACA DEL VEHICULO")
                    .setView(consulta5)
                    .setPositiveButton("ACEPTAR") {d, i ->

                        placaVehiculo = consulta5.text.toString()

                        var idVehiculo= ConsultasAvanzadas(this).obtenerIdConsulta5(placaVehiculo)
                        var resultadoC5 = ConsultasAvanzadas(this).consulta5(idVehiculo)

                        txtResultadoConsulta.setText(resultadoC5)

                        //Desbloqueo de boton Exportar para poder Exportar Documento
                        btnExportar.isEnabled = true

                        d.dismiss()
                    }
                    .setNegativeButton("CANCELAR") {d, i ->
                        d.cancel()
                    }
                    .show()

            }

            //CONSULTA Numero 6--------------------------------------------------------------------------------------------------------
            if(indice==6){
                var resultadoC6 = ConsultasAvanzadas(this).consulta6()
                txtResultadoConsulta.setText(resultadoC6)

                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = true

            }

            //CONSULTA Numero 7--------------------------------------------------------------------------------------------------------
            if(indice==7){
                var resultadoC7 = ConsultasAvanzadas(this).consulta7()
                txtResultadoConsulta.setText(resultadoC7)

                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = true
            }

            //CONSULTA Numero 8 --------------------------------------------------------------------------------------------------------

            if(indice==8){
                var resultadoC8 = ConsultasAvanzadas(this).consulta8()
                txtResultadoConsulta.setText(resultadoC8)

                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = true
            }


            if(indice==0 || indice>8){
                Toast.makeText(this, "Ingresa Un Indice Valido (1...8)", Toast.LENGTH_LONG).show()

                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = false
            }

            //Boton Exportar--------------------------------------------------------------------------------------------------------------
            btnExportar.setOnClickListener {
                val nombre = EditText(this)
                nombre.setHint("NOMBRE ARCHIVO .CVS")
                nombreNota = nombre.text.toString()
                nombreNota=nombreNota+".cvs"

                AlertDialog.Builder(this)
                    .setTitle("NUEVO ARCHIVO")
                    .setMessage("Escribe nombre para el archivo")
                    .setView(nombre)
                    .setPositiveButton("ACEPTAR") {d, i ->
                        guardarArchivoNuevo(nombreNota,txtResultadoConsulta.text.toString())
                        d.dismiss()
                    }
                    .setNegativeButton("CANCELAR") {d, i ->
                        d.cancel()
                    }
                    .show()
            }



            //Borrar todos los resultados---------------------------------------------------------------------------------------------------
            btnLimpiar.setOnClickListener {
                indice = 0
                txtResultadoConsulta.text = ""
                txtIndiceConsulta.setText("")

                //Desbloqueo de boton Exportar para poder Exportar Documento
                btnExportar.isEnabled = false


            }
        }

    }

    fun guardarArchivoNuevo(nombreNota: String, descripcion:String){
        try {
            val tarjeta = getExternalFilesDir(null);
            val file = File(tarjeta?.getAbsolutePath(), nombreNota)
            val osw = OutputStreamWriter(FileOutputStream(file))
            osw.write(descripcion)
            osw.flush()
            osw.close()
            Toast.makeText(this, "DATOS EXPORTADOS CORRECTAMENTE", Toast.LENGTH_LONG).show()
        } catch (ioe: IOException) {
            Toast.makeText(this, "ERROR DE ALMACENAMIENTO",Toast.LENGTH_LONG).show()
        }
    }
}