package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main5.*
import kotlinx.android.synthetic.main.activity_main7.*

class MainActivity7 : AppCompatActivity() {
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        var extra = intent.extras
        id = extra!!.getString("idActualizarPedido")!!


        val pedidos = Pedido(this).consultar(id)
        ActualizarNombreCliente.setText(pedidos.nombrecliente)
        ActualizarDomicilioPedido.setText(pedidos.pdomicilio)
        ActualizarTelefonoCliente.setText(pedidos.telefono)
        ActualizarPedido.setText(pedidos.descripcion)
        ActualizarTotal.setText((pedidos.total).toString())
        ActualizaridrepartidorP.setText((pedidos.idrepartidor).toString())

        btnActualizarPedido.setOnClickListener {
            val pedidoActualizar = Pedido(this)
            pedidoActualizar.nombrecliente = ActualizarNombreCliente.text.toString()
            pedidoActualizar.pdomicilio = ActualizarDomicilioPedido.text.toString()
            pedidoActualizar.telefono = ActualizarTelefonoCliente.text.toString()
            pedidoActualizar.descripcion = ActualizarPedido.text.toString()
            pedidoActualizar.total = (ActualizarTotal.text.toString()).toInt()
            pedidoActualizar.idrepartidor = ActualizaridrepartidorP.text.toString().toInt()

            val resultado = pedidoActualizar.actualizarPedido(id)
            if(resultado){
                Toast.makeText(this,"Exito, Se Actualizo", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"ERROR, No se Actualizo", Toast.LENGTH_LONG).show()
            }
        }

        btnRegresarPedido.setOnClickListener {
            finish()
        }
    }
}