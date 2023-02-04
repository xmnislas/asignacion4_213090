package islas.ximena.asignacion4_calculadora_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtResultado: TextView = findViewById(R.id.tvResultado)
        var txtEstado: TextView = findViewById(R.id.tvEstado)
        val etEstatura: EditText = findViewById(R.id.etEstatura)
        val etPeso: EditText = findViewById(R.id.etPeso)
        val btnCalcula: Button = findViewById(R.id.btnCalcular)

        btnCalcula.setOnClickListener(){
            if(!etEstatura.text.isBlank() || !etPeso.text.isBlank()){
                //se calcula el indice de masa corporal y se ubica el resultado
                val imcNum = this.calculaIMC(etEstatura.text.toString().toDouble(),
                    etPeso.text.toString().toDouble())

                txtResultado.setText(imcNum.toString())

                //se obtiene el estado del usuario
                val estado = this.obtenEstado(imcNum)
                txtEstado.setText(estado)

                //se le añade el color dependiendo del resultado
                when(estado){
                    "Bajo peso" -> txtEstado.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> txtEstado.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> txtEstado.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad de grado 1" -> txtEstado.setBackgroundResource(R.color.colorYellow)
                    "Obesidad de grado 2" -> txtEstado.setBackgroundResource(R.color.colorOrange)
                    "Obesidad de grado 3" -> txtEstado.setBackgroundResource(R.color.colorRed)
                }
            }
        }
    }

    /**
     * Función encargada de calcular el imc en base a la altura
     * y peso del usuario.
     */
    fun calculaIMC(altura: Double, peso: Double):Double{
        val imc: Double = (peso/(Math.pow(altura, 2.0)))
        return imc
    }

    /**
     * Función encargada de devolver el estado del usuario en base
     * al imc
     */
    fun obtenEstado(imc: Double): String{
        when{
            imc <18.5 -> return "Bajo peso"
            imc >= 18.5 && imc <= 24.9 -> return "Saludable"
            imc >= 24.9 && imc <= 29.9 -> return "Sobrepeso"
            imc >= 29.9 && imc <= 34.9 -> return "Obesidad de grado 1"
            imc >= 34.9 && imc <= 29.9 -> return "Obesidad de grado 2"
            imc >= 40 -> return "Obesidad de grado 3"
        }
        return "error"
    }
}