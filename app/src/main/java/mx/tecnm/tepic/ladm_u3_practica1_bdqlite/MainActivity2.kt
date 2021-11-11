package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    var idRepartidor = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mostrarRepartidoresCapturados()
        btnInsertarRepartidor.setOnClickListener {
            val repartidor = Repartidor(this)

            repartidor.nombre = txtNombre.text.toString()
            repartidor.domicilio = txtDomicilio.text.toString()
            repartidor.noLicencia = txtLicencia.text.toString()
            repartidor.vence = txtVencimiento.text.toString()

            val resultado = repartidor.insert() // True = Insercion correcta; False = resultado negativo

            if(resultado){
                //Insercion correcta
                Toast.makeText(this,"EXITO!!, Insercion Correcta", Toast.LENGTH_SHORT).show()
                txtNombre.text.clear()
                txtDomicilio.text.clear()
                txtLicencia.text.clear()
                txtVencimiento.text.clear()
                mostrarRepartidoresCapturados()

            }
            else{
                Toast.makeText(this,"ERROR!, No se Realizo la insercion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarRepartidoresCapturados(){
        val arregloRepartidor = Repartidor(this).consultar()
        listaRepartidores.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloRepartidor)
        idRepartidor.clear()
        idRepartidor = Repartidor(this).obtenerIDs()
        activasEvento(listaRepartidores)
    }

    private fun activasEvento(listaArtistas: ListView) {
        listaArtistas.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idRepartidor[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Que desea hacer?")
                .setPositiveButton("Editar"){d,i-> actualizar(idSeleccionado)}
                .setNegativeButton("Eliminar"){d,i-> eliminar(idSeleccionado)}
                .setNeutralButton("Cancelar"){d,i->}
                .show()

        }
    }

    private fun actualizar(idSeleccionado: Int) {
        val intento = Intent(this,MainActivity3::class.java)
        intento.putExtra("idActualizar",idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this).setMessage("Deseas Actualizar La Lista?")
            .setPositiveButton("SI"){d,i-> mostrarRepartidoresCapturados()}
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }

    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("Seguro que deseas eliminar ID ${idSeleccionado}")
            .setPositiveButton("SI"){d,i->
                val resultado = Repartidor(this).eliminar(idSeleccionado)
                if(resultado){
                    Toast.makeText(this,"Se elimino con exito", Toast.LENGTH_SHORT).show()
                    mostrarRepartidoresCapturados()
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