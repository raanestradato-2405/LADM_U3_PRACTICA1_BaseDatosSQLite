package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main6.*

class MainActivity6 : AppCompatActivity() {
    var idPedido = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        mostrarPedidosCapturados()

        btnPedido.setOnClickListener {
            val pedido = Pedido(this)

            pedido.nombrecliente = txtNombreCliente.text.toString()
            pedido.pdomicilio= txtDomicilioPedido.text.toString()
            pedido.telefono = txtTelefonoCliente.text.toString()
            pedido.descripcion = txtPedido.text.toString()
            pedido.total = txtTotal.text.toString().toInt()
            pedido.idrepartidor = txtidrepartidorP.text.toString().toInt()

            val resultado = pedido.insert() // True = Insercion correcta; False = resultado negativo

            if(resultado){
                //Insercion correcta
                Toast.makeText(this,"EXITO!!, Insercion Correcta", Toast.LENGTH_SHORT).show()
                txtNombreCliente.text.clear()
                txtDomicilioPedido.text.clear()
                txtTelefonoCliente.text.clear()
                txtPedido.text.clear()
                txtTotal.text.clear()
                txtidrepartidorP.text.clear()
                mostrarPedidosCapturados()

            }
            else{
                Toast.makeText(this,"ERROR!, No se Realizo la insercion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mostrarPedidosCapturados(){
        val arregloPedidos = Pedido(this).consultar()
        listaPedidos.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloPedidos)
        idPedido.clear()
        idPedido = Pedido(this).obtenerIDs()
        activasEvento(listaPedidos)
    }

    private fun activasEvento(listaPedidos: ListView) {
        listaPedidos.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idPedido[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Que desea hacer?")
                .setPositiveButton("Editar"){d,i-> actualizarPedido(idSeleccionado)}
                .setNegativeButton("Eliminar"){d,i-> eliminar(idSeleccionado)}
                .setNeutralButton("Cancelar"){d,i->}
                .show()

        }
    }

    private fun actualizarPedido(idSeleccionado: Int) {
        val intento = Intent(this,MainActivity7::class.java)
        intento.putExtra("idActualizarPedido",idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this).setMessage("Deseas Actualizar La Lista?")
            .setPositiveButton("SI"){d,i-> mostrarPedidosCapturados()}
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }

    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("Seguro que deseas eliminar ID ${idSeleccionado}")
            .setPositiveButton("SI"){d,i->
                val resultado = Pedido(this).eliminar(idSeleccionado)
                if(resultado){
                    Toast.makeText(this,"Se elimino con exito", Toast.LENGTH_SHORT).show()
                    mostrarPedidosCapturados()
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