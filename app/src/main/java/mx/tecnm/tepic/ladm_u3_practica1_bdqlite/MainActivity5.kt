package mx.tecnm.tepic.ladm_u3_practica1_bdqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivity5 : AppCompatActivity() {
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        var extra = intent.extras
        id = extra!!.getString("idActualizarVehiculo")!!


        val vehiculos = Vehiculo(this).consultar(id)
        actualizarPlaca.setText(vehiculos.placa)
        actualizarMarca.setText(vehiculos.marca)
        actualizarModelo.setText(vehiculos.modelo)
        actualizarYear.setText((vehiculos.year).toString())
        actualizaridRepartidorV.setText((vehiculos.idrepartidor).toString())

        btnActualizarVehiculo.setOnClickListener {
            val vehiculoActualizar = Vehiculo(this)
            vehiculoActualizar.placa = actualizarPlaca.text.toString()
            vehiculoActualizar.marca = actualizarMarca.text.toString()
            vehiculoActualizar.modelo = actualizarModelo.text.toString()
            vehiculoActualizar.year = (actualizarYear.text.toString()).toInt()
            vehiculoActualizar.idrepartidor = actualizaridRepartidorV.text.toString().toInt()

            val resultado = vehiculoActualizar.actualizarVehiculo(id)
            if(resultado){
                Toast.makeText(this,"Exito, Se Actualizo",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"ERROR, No se Actualizo",Toast.LENGTH_LONG).show()
            }
        }

        btnRegresarVehiculo.setOnClickListener {
            finish()
        }



    }
}