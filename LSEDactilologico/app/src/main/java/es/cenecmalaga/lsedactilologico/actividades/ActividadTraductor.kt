package es.cenecmalaga.lsedactilologico.actividades

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import com.soepic.sefancytoast.FancyToast
import es.cenecmalaga.lsedactilologico.adapters_holders.RecyclerViewTraductorAdapter


class ActividadTraductor : AppCompatActivity() {

    val labelTraduccionRealizada:TextView by lazy { findViewById<TextView>(R.id.labelTraduccionRealizada) }
    val campoTraduccion:EditText by lazy { findViewById<EditText>(R.id.campoTraduccion) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_traductor)
        val contenedorPrincipal:ConstraintLayout=findViewById<ConstraintLayout>(R.id.contenedorPrincipal)
        val animacion: AnimationDrawable = contenedorPrincipal.background as AnimationDrawable
        animacion.setEnterFadeDuration(2000)
        animacion.setExitFadeDuration(3000)
        animacion.start()

        campoTraduccion.setOnKeyListener(
            View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    traducir(v)

                    //Ocultar el teclado
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken, 0)

                    return@OnKeyListener true
                }
                false
            })

    }


    /**
     * Funcion que usaremos para traducir el texto y pasarlo a imagenes en el recycleView Principal
     */
    fun traducir(view: View) {

        if(campoTraduccion.text.isNotBlank()) {
            labelTraduccionRealizada.visibility = View.VISIBLE
            //Convertimos a String el EditText donde hemos escrito las palabras
            val cadena: String = campoTraduccion.text.toString().toLowerCase()

            var datos: ArrayList<Char> = ArrayList()

            for (i in 0 until cadena.length) {

                datos.add(cadena.get(i))
            }

            //cogemos en una variable el contenedor que es el recycleview
            val contenedorRecycler: RecyclerView = findViewById(R.id.recyclerTraduccion)
            //creamos el adapter
            val adapter: RecyclerViewTraductorAdapter = RecyclerViewTraductorAdapter(this, datos)
            //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
            contenedorRecycler.adapter = adapter
            //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
            contenedorRecycler.layoutManager = LinearLayoutManager(this)
        }else{
            FancyToast() // context
                .with(this) // gravity of FancyToast
                .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                .setIcon(R.mipmap.appicon_round) // set text for FancyToast
                .setText(this.resources.getString(R.string.nadaQueTraducir)) // corner radius of FancyToast view
                .cornerRadius(16f) // show/hide icon
                .hideIcon(false) // finally show the FancyToast
                .show()
        }




    }


    /**
     * Funcion para cambiar de ventana sobre nosotros
     */
    fun irSobreNosotros(view: View) {
        var intent:Intent=Intent(this, ActividadSobreNosotros::class.java)
        startActivity(intent)
    }

    /**
     * Funcion para cambiar de ventana sobre Ayudanos a mejorar
     */
    fun irAMejorar(view: View) {
        var intent: Intent = Intent(this, AyudanosAMejorar::class.java)
        startActivity(intent)
    }

    /**
    * Funcion para recoger el texto escrito
    */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        var texto: EditText = this.findViewById(R.id.campoTraduccion)
        val cadena: String = texto.text.toString().toLowerCase()

        outState.putString("texto", cadena)
        outState.putBoolean(
            "labelTraduccionRealizadaVisible",
            labelTraduccionRealizada.visibility == View.VISIBLE
        )


    }


    /**
     * Funicion para recoger el bundle de girar la pantalla y meter las imagenes al recycleView
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var texto = savedInstanceState.getString("texto")
        labelTraduccionRealizada.visibility=if(savedInstanceState.getBoolean("labelTraduccionRealizadaVisible")) View.VISIBLE else View.INVISIBLE
        var datos: ArrayList<Char> = ArrayList()

        if (texto != null) {
            for (i in 0 until texto.length) {

                datos.add(texto.get(i))
            }
            //cogemos en una variable el contenedor que es el recycleview
            val contenedorRecycler: RecyclerView = findViewById(R.id.recyclerTraduccion)
            //creamos el adapter
            val adapter: RecyclerViewTraductorAdapter = RecyclerViewTraductorAdapter(this, datos)
            //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
            contenedorRecycler.adapter = adapter
            //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
            contenedorRecycler.layoutManager = LinearLayoutManager(this)


        }
    }

    fun limpiar(view: View){
        campoTraduccion.setText("")
        labelTraduccionRealizada.visibility=View.INVISIBLE
        var rv: RecyclerView = this.findViewById<RecyclerView>(R.id.recyclerTraduccion)

        var cadena: String = campoTraduccion.text.toString()
        var array = ArrayList<Char>()
        for (i in 0 until cadena.length) {
            array.remove(cadena.get(i))
        }
        var adapter: RecyclerViewTraductorAdapter = RecyclerViewTraductorAdapter(this, array)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

    }


}