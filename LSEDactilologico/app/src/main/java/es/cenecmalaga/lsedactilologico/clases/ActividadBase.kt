package es.cenecmalaga.lsedactilologico.clases

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.NicolasFernandez.lsedactilologico.R

/**
 * Superclase de las actividades Traductor, Sobre nosotros y Ayúdanos a mejorar. Pone en marcha las animaciones comunes, recibe por constructor el layout que usará.
 * @author Miguel Páramos
 * @since 0.3
 */
open class ActividadBase(var layoutIdentifier:Int): AppCompatActivity() {

    /** la imagen del logotipo de la aplicación, usada para añadirle una animación. **/
    val imagenLogo by lazy { findViewById<ImageView>(R.id.imagenLogo) }

    /**
     * Función onCreate, asigna el layout, e inicializa la animación de gradiente del fondo de pantalla.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutIdentifier)

        //Animación perpetua del fondo
        val contenedorPrincipal: View =
            findViewById<View>(R.id.contenedorPrincipal)
        val animacion: AnimationDrawable = contenedorPrincipal.background as AnimationDrawable
        animacion.setEnterFadeDuration(2000)
        animacion.setExitFadeDuration(3000)
        animacion.start()

    }

    /**
     * Función onStart, lanza la animación inicial del logotipo de la app.
     */
    override fun onStart() {
        super.onStart()

        //Animación puntual del logo, cuando se entra a la actividad
        val reducir: Animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animacion_reducir)
        imagenLogo.startAnimation(reducir)
    }
}