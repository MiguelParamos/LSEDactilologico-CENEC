package es.cenecmalaga.lsedactilologico.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import es.cenecmalaga.lsedactilologico.adapters_holders.RecyclerViewTraductorAdapter
import kotlinx.android.synthetic.main.actividad_traductor.*

class ActividadTraductor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_traductor)


    }


    /**
     * Funcion que usaremos para traducir el texto y pasarlo a imagenes en el recycleView Principal
     */
    fun traducir(view: View) {

        var texto: EditText = this.findViewById(R.id.txtTraducir)
        //Convertimos a String el EditText donde hemos escrito las palabras
        val cadena: String = texto.text.toString().toLowerCase()

        var datos: ArrayList<Char> = ArrayList()

        if (cadena != null) {
            for (i in 0 until cadena.length) {

                datos.add(cadena.get(i))
            }


        }

        //cogemos en una variable el contenedor que es el recycleview
        val contenedorRecycler: RecyclerView = findViewById(R.id.recyclerView)
        //creamos el adapter
        val adapter: RecyclerViewTraductorAdapter = RecyclerViewTraductorAdapter(this, datos)
        //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
        contenedorRecycler.adapter = adapter
        //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
        contenedorRecycler.layoutManager = LinearLayoutManager(this)

    }


    /**
     * Funcion para cambiar de ventana sobre nosotros
     */
    fun irSobreNosotros(view: View) {
        var intent:Intent=Intent(this,ActividadSobreNosotros::class.java)
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


        var texto: EditText = this.findViewById(R.id.txtTraducir)
        val cadena: String = texto.text.toString().toLowerCase()

        outState.putString("texto",cadena)


    }


    /**
     * Funicion para recoger el bundle de girar la pantalla y meter las imagenes al recycleView
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var texto = savedInstanceState.getString("texto")

        var datos: ArrayList<Char> = ArrayList()

        if (texto != null) {
            for (i in 0 until texto.length) {

                datos.add(texto.get(i))
            }
            //cogemos en una variable el contenedor que es el recycleview
            val contenedorRecycler: RecyclerView = findViewById(R.id.recyclerView)
            //creamos el adapter
            val adapter: RecyclerViewTraductorAdapter = RecyclerViewTraductorAdapter(this, datos)
            //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
            contenedorRecycler.adapter = adapter
            //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
            contenedorRecycler.layoutManager = LinearLayoutManager(this)


        }
    }

    fun limpiar(view: View){
        txtTraducir.setText("")

        var rv: RecyclerView = this.findViewById<RecyclerView>(R.id.recyclerView)

        var cadena: String = txtTraducir.text.toString()
        var array = ArrayList<Char>()
        for (i in 0 until cadena.length) {
            array.remove(cadena.get(i))
        }
        var adapter: RecyclerViewTraductorAdapter = RecyclerViewTraductorAdapter(this, array)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

    }


}