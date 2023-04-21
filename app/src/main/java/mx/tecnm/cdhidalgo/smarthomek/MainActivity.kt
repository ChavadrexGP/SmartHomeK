package mx.tecnm.cdhidalgo.smarthomek

import android.content.ContextParams
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var bLogin: Button

    var sesion: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        bLogin = findViewById(R.id.bLogin)
        //archivo xml para gusrdar el token
        sesion = getSharedPreferences("sesion", 0)
        bLogin.setOnClickListener{ login() }
    }

    private fun login() {
        val url = Uri.parse(Config.URL+"login")
            .buildUpon()
            .build().toString()

        val dato: JSONObject = JSONObject()
        dato.put("username", etUsername?.text.toString())
        dato.put("password", etPassword?.text.toString())

        val peticion = JsonObjectRequest(
            Request.Method.POST, url, dato,
            {
                    res -> respuesta(res)
            },
            {
                err -> Toast.makeText(this, "Error en la peticion: "+err, Toast.LENGTH_SHORT).show()
            })

        MySingleton.getInstance(applicationContext).addToRequestQueue(peticion)
    }

    private fun respuesta(res: JSONObject) {
        with(sesion!!.edit()){
            putString("token", res.getString("jwt"))
            putString("user", etUsername.text.toString())
            apply()
        }
        Toast.makeText(this, "Bienvenido ${etUsername.text.toString()}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity2::class.java))
    }
}