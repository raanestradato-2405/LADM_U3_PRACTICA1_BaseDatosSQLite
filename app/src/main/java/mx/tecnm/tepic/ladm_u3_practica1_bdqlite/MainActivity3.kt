package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var extra = intent.extras
        id = extra!!.getString("idActualizar")!!

        //Recuperar la data

        val repartidor = Repartidor(this).consultar(id)
        actualizarNombre.setText(repartidor.nombre)
        actualizarDomicilio.setText(repartidor.domicilio)
        actualizarLicencia.setText(repartidor.noLicencia)
        actualizarVencimiento.setText(repartidor.vence)

        btnActualizarRepartidor.setOnClickListener {
            val repartidorActualizar = Repartidor(this)
            repartidorActualizar.nombre = actualizarNombre.text.toString()
            repartidorActualizar.domicilio = actualizarDomicilio.text.toString()
            repartidorActualizar.noLicencia = actualizarLicencia.text.toString()
            repartidorActualizar.vence = actualizarVencimiento.text.toString()

            val resultado = repartidorActualizar.actualizar(id)
            if(resultado){
                Toast.makeText(this,"Exito, Se Actualizo", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"ERROR, No se Actualizo", Toast.LENGTH_LONG).show()
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
