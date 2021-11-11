package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main4.*

class MainActivity4 : AppCompatActivity() {
    var idVehiculo = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        mostrarVehiculosCapturados()

        btnInsertarVehiculo.setOnClickListener {
            val vehiculo = Vehiculo(this)

            vehiculo.placa = txtPlaca.text.toString()
            vehiculo.marca = txtMarca.text.toString()
            vehiculo.modelo = txtModelo.text.toString()
            vehiculo.year = txtYear.text.toString().toInt()
            vehiculo.idrepartidor = txtidrepartidor.text.toString().toInt()

            val resultado = vehiculo.insert() // True = Insercion correcta; False = resultado negativo

            if(resultado){
                //Insercion correcta
                Toast.makeText(this,"EXITO!!, Insercion Correcta", Toast.LENGTH_SHORT).show()
                txtPlaca.text.clear()
                txtMarca.text.clear()
                txtModelo.text.clear()
                txtYear.text.clear()
                txtidrepartidor.text.clear()

                mostrarVehiculosCapturados()
            }
            else{
                Toast.makeText(this,"ERROR!, No se Realizo la insercion", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun mostrarVehiculosCapturados(){
        val arregloVehiculo = Vehiculo(this).consultar()
        listaVehiculos.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloVehiculo)
        idVehiculo.clear()
        idVehiculo = Vehiculo(this).obtenerIDs()
        activasEvento(listaVehiculos)
    }

    private fun activasEvento(listaVehiculo: ListView) {
        listaVehiculo.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idVehiculo[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Que desea hacer?")
                .setPositiveButton("Editar"){d,i-> actualizarVehiculo(idSeleccionado)}
                .setNegativeButton("Eliminar"){d,i-> eliminar(idSeleccionado)}
                .setNeutralButton("Cancelar"){d,i->}
                .show()

        }
    }

    private fun actualizarVehiculo(idSeleccionado: Int) {
        val intento = Intent(this,MainActivity5::class.java)
        intento.putExtra("idActualizarVehiculo",idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this).setMessage("Deseas Actualizar La Lista?")
            .setPositiveButton("SI"){d,i-> mostrarVehiculosCapturados()}
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }

    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("Seguro que deseas eliminar ID ${idSeleccionado}")
            .setPositiveButton("SI"){d,i->
                val resultado = Vehiculo(this).eliminar(idSeleccionado)
                if(resultado){
                    Toast.makeText(this,"Se elimino con exito", Toast.LENGTH_SHORT).show()
                    mostrarVehiculosCapturados()
                } else {
                    Toast.makeText(this,"Error no se elimino registro", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("NO"){d,i->
                d.cancel()
            }
            .show()
    }
}