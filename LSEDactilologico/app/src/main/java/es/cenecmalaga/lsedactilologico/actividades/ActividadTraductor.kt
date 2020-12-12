package es.cenecmalaga.lsedactilologico.actividades

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import com.soepic.sefancytoast.FancyToast
import es.cenecmalaga.lsedactilologico.adapters_holders.AdapterTraductor
import es.cenecmalaga.lsedactilologico.clases.ActividadBase

/**
 * Actividad correspondiente a la pantalla inicial y principal de la app, donde se realiza la traducción.
 * @author Miguel Páramos
 * @author Nicolás Fernández Heredia
 * @author Rafael Carrión Ponce
 * @since 0.1
 */
class ActividadTraductor : ActividadBase(R.layout.actividad_traductor) {

    /** textView que pone "la traducción es: ". Se usa para visualización y User Experience. Se saca aquí para mostrarla/ocultarla y animarla **/
    val labelTraduccionRealizada:TextView by lazy { findViewById<TextView>(R.id.labelTraduccionRealizada) }
    /** De campoTraducción se recoge la frase que se va a traducir a LSE Dactilológico **/
    val campoTraduccion:EditText by lazy { findViewById<EditText>(R.id.campoTraduccion) }
    /** El contenedor del campoTraducción y sus botones, se saca aquí para ponerle una animación **/
    val contenedorBuscador:LinearLayout by lazy { findViewById<LinearLayout>(R.id.contenedorBuscador) }
    /** El contenedor de los dos botones inferiores. Se saca aquí para ponerle una animación **/
    val contenedorAboutAyuda:LinearLayout by lazy { findViewById<LinearLayout>(R.id.contenedorAboutAyuda) }

    /**
     * En onCreate inicializamos el onKeyListener del campo traducción. Lo usaremos para ocultar el teclado cuando se pulsa intro.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
     * En la función onStart inicializamos las animaciones iniciales del buscador y los botones inferiores.
     */
    override fun onStart() {
        super.onStart()
        //Animación del buscador
        val aparecer:Animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animacion_aparecer)
        contenedorBuscador.startAnimation(aparecer)

        //Animación de los botones inferiores
        contenedorAboutAyuda.startAnimation(aparecer)
    }


    /**
     * Funcion que usaremos para traducir el texto y pasarlo a imagenes en el recycleView Principal
     */
    fun traducir(view: View) {
        Log.d("vista pulsada",view.toString()) //Solo está aquí para quitarnos warnings de compilación
        if(campoTraduccion.text.isNotBlank()) {

            //Ocultar el teclado
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)

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
            val adapter: AdapterTraductor = AdapterTraductor(this, datos)
            //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
            contenedorRecycler.adapter = adapter
            //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
            contenedorRecycler.layoutManager = LinearLayoutManager(this)

            //Animación de entrada del texto: Traducido
            val aparecer: Animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.animacion_aparecer)
            labelTraduccionRealizada.startAnimation(aparecer)

            //Animación de entrada del recyclerView
            val reducir: Animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.animacion_reducir)
            contenedorRecycler.startAnimation(reducir)
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
        Log.d("vista pulsada",view.toString()) //Solo está aquí para quitarnos warnings de compilación
        var intent:Intent=Intent(this, ActividadSobreNosotros::class.java)
        startActivity(intent)
    }

    /**
     * Funcion para cambiar de ventana sobre Ayudanos a mejorar
     */
    fun irAMejorar(view: View) {
        Log.d("vista pulsada",view.toString()) //Solo está aquí para quitarnos warnings de compilación
        var intent: Intent = Intent(this, ActividadAyudanosAMejorar::class.java)
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
            val adapter: AdapterTraductor = AdapterTraductor(this, datos)
            //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
            contenedorRecycler.adapter = adapter
            //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
            contenedorRecycler.layoutManager = LinearLayoutManager(this)


        }
    }

    /**
     * Función para limpiar el buscador y el RecyclerView de traducción, dejándolos en blanco
     */
    fun limpiar(view: View){
        Log.d("vista pulsada",view.toString()) //Solo está aquí para quitarnos warnings de compilación
        campoTraduccion.setText("")
        labelTraduccionRealizada.visibility=View.INVISIBLE

        //Animación de salida del texto: Traducido
        val desaparecer: Animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animacion_desaparecer)
        labelTraduccionRealizada.startAnimation(desaparecer)


        var rv: RecyclerView = this.findViewById<RecyclerView>(R.id.recyclerTraduccion)

        var cadena: String = campoTraduccion.text.toString()
        var array = ArrayList<Char>()
        for (i in 0 until cadena.length) {
            array.remove(cadena.get(i))
        }
        var adapter: AdapterTraductor = AdapterTraductor(this, array)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

    }


}