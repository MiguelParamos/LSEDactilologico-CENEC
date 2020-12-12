package es.cenecmalaga.lsedactilologico.actividades

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.NicolasFernandez.lsedactilologico.R
import com.soepic.sefancytoast.FancyToast
import es.cenecmalaga.lsedactilologico.clases.ActividadBase
import java.io.File
import java.io.IOException

/**
 * Actividad que permite al usuario de la aplicación enviar un audio o un texto a los desarrolladores, para ayudar a mejorar la app.
 * @author Miguel Páramos
 * @author Kevin Rääk
 * @author Francisco España Quintana
 * @since 0.1
 */
class ActividadAyudanosAMejorar : ActividadBase(R.layout.actividad_ayudanos_a_mejorar) {

    /** Variable auxiliar con el código para pedir los permisos peligrosos que requiere esta actividad **/
    val REQUEST_PERMISSION_CODE:Int = 1000
    /** Ruta donde se guardará la grabación de audio, si el usuario la realiza **/
    var pathSave:String = ""
    /** Contenedor para enviar texto, se recoge aquí para hacerle una animación **/
    val contenedorEnviarTexto:ConstraintLayout by lazy { findViewById<ConstraintLayout>(R.id.contenedorEnviarTexto)}
    /** Contenedor para enviar audio, se recoge aquí para hacerle una animación **/
    val contenedorEnviarAudio:ConstraintLayout by lazy { findViewById<ConstraintLayout>(R.id.contenedorEnviarAudio)}


    /**
     * Función onCreate, se inicializan todas las variables, y se asignan eventos onClick.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se obtiene el fichero donde se guardará la grabación
        val externalStorageVolumes: Array<out File> =
        ContextCompat.getExternalFilesDirs(applicationContext, null)
        pathSave ="${externalStorageVolumes[0].absolutePath}/grabacion.3gp"

        //Se piden permisos si es necesario
        if (!checkPermissionFromDevice()) {
            requestPermission()
        }

        //Se declaran e inicializan todas las variables necesarias más adelante
        var botonReproducir:Button = findViewById(R.id.botonReproducir)
        var botonEnviarGrabacion:Button = findViewById(R.id.botonEnviarGrabacion)
        var botonGrabar:Button = findViewById(R.id.botonGrabar)
        var imagenGrabando:ImageView = findViewById(R.id.imagenGrabando)
        var imagenReprocudiendo:ImageView = findViewById(R.id.imagenReproduciendo)
        imagenGrabando.visibility = View.GONE
        imagenReprocudiendo.visibility = View.GONE
        botonEnviarGrabacion.isEnabled = false
        botonReproducir.isEnabled=false
        var mediaRecorder:MediaRecorder =MediaRecorder()
        var mediaPlayer:MediaPlayer= MediaPlayer()

            //OnClick del botón grabar. Hará también las veces de botón para detener la grabación, cuando esta esté en curso.
            botonGrabar.setOnClickListener{
                if (checkPermissionFromDevice()) {
                    //Si el botón pone Grabar, es la acción que realizaremos
                    if(botonGrabar.text.toString().equals(this.resources.getString(R.string.grabar))) {
                        mediaRecorder= MediaRecorder()
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
                        println(pathSave)
                        mediaRecorder.setOutputFile(pathSave)

                        try {
                            mediaRecorder.prepare()
                            mediaRecorder.start()

                            botonReproducir.isEnabled = false
                            botonEnviarGrabacion.isEnabled = false
                            imagenGrabando.visibility = View.VISIBLE
                            botonReproducir.visibility=View.INVISIBLE
                            botonEnviarGrabacion.visibility=View.INVISIBLE

                            FancyToast() // context
                                    .with(this) // gravity of FancyToast
                                    .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                                    .setIcon(R.mipmap.appicon_round) // set text for FancyToast
                                    .setText(this.resources.getString(R.string.grabando)) // corner radius of FancyToast view
                                    .cornerRadius(16f) // show/hide icon
                                    .hideIcon(false) // finally show the FancyToast
                                    .show()
                            botonGrabar.setText(R.string.parar)
                        } catch (e: IOException) {
                            FancyToast() // context
                                    .with(this) // gravity of FancyToast
                                    .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                                    .setIcon(R.drawable.error_sign) // set text for FancyToast
                                    .setText(this.resources.getString(R.string.noPudeGrabar)+e.message) // corner radius of FancyToast view
                                    .cornerRadius(16f) // show/hide icon
                                    .hideIcon(false) // finally show the FancyToast
                                    .show()
                        }

                    //Si el botón no pone grabar, sirve para parar la grabación.
                    }else{
                        botonEnviarGrabacion.isEnabled = true
                        botonGrabar.isEnabled = true
                        botonReproducir.isEnabled = true

                        botonEnviarGrabacion.visibility=View.VISIBLE
                        botonGrabar.visibility=View.VISIBLE
                        botonReproducir.visibility=View.VISIBLE

                        mediaRecorder.stop()
                        mediaRecorder.release()

                        imagenGrabando.visibility = View.GONE

                        //Información para obtener la duración de la grabación
                        var playerParaDuracionGrabacion:MediaPlayer = MediaPlayer()
                        try {
                            playerParaDuracionGrabacion.setDataSource(pathSave)
                            playerParaDuracionGrabacion.prepare()
                        }catch (e:IOException){
                            FancyToast() // context
                                    .with(this) // gravity of FancyToast
                                    .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                                    .setIcon(R.drawable.error_sign) // set text for FancyToast
                                    .setText(this.resources.getString(R.string.noPudeGrabar)+e.message) // corner radius of FancyToast view
                                    .cornerRadius(16f) // show/hide icon
                                    .hideIcon(false) // finally show the FancyToast
                                    .show()
                        }


                        FancyToast() // context
                                .with(this) // gravity of FancyToast
                                .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                                .setIcon(R.mipmap.appicon_round) // set text for FancyToast
                                .setText(this.resources.getString(R.string.grabacionFinalizada)+" "+(playerParaDuracionGrabacion.duration/1000f)+" "+this.resources.getString(R.string.segundos)) // corner radius of FancyToast view
                                .cornerRadius(16f) // show/hide icon
                                .hideIcon(false) // finally show the FancyToast
                                .show()
                        playerParaDuracionGrabacion.release()
                        botonGrabar.setText(R.string.grabar)
                    }


                }else{
                    requestPermission()
                }
            }
            //Botón que envía la grabación, sin implementar
            botonEnviarGrabacion.setOnClickListener {
                FancyToast() // context
                        .with(this) // gravity of FancyToast
                        .setGravity(Gravity.BOTTOM, 0, 10) // set custom icon resource
                        .setIcon(R.drawable.error_sign) // set text for FancyToast
                        .setText(this.resources.getString(R.string.pendienteDeImplementar)) // corner radius of FancyToast view
                        .cornerRadius(16f) // show/hide icon
                        .hideIcon(false) // finally show the FancyToast
                        .show()
            }
            //Botón que reproduce la grabación, y también hace las veces de botón de parar reproducción.
            botonReproducir.setOnClickListener {
                //Si el botón pone reproducir, eso es lo que tendremos que hacer.
                if(botonReproducir.text.toString().equals(this.resources.getString(R.string.reproducir))) {
                    mediaPlayer= MediaPlayer()
                    botonEnviarGrabacion.isEnabled = false
                    botonGrabar.isEnabled = false
                    botonReproducir.isEnabled = true
                    imagenReprocudiendo.visibility = View.VISIBLE

                    botonEnviarGrabacion.visibility=View.INVISIBLE
                    botonGrabar.visibility=View.INVISIBLE

                    try {
                        mediaPlayer.setDataSource(pathSave)
                        mediaPlayer.prepare()
                    } catch (e: IOException) {
                        FancyToast() // context
                                .with(this) // gravity of FancyToast
                                .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                                .setIcon(R.drawable.error_sign) // set text for FancyToast
                                .setText(this.resources.getString(R.string.noPudeReproducirGrabacion) + e.message) // corner radius of FancyToast view
                                .cornerRadius(16f) // show/hide icon
                                .hideIcon(false) // finally show the FancyToast
                                .show()
                    }

                    mediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener() {
                        botonReproducir.setText(R.string.reproducir)
                        botonEnviarGrabacion.isEnabled = true
                        botonGrabar.isEnabled = true
                        botonReproducir.isEnabled = true
                        botonEnviarGrabacion.visibility=View.VISIBLE
                        botonGrabar.visibility=View.VISIBLE
                        botonGrabar.visibility=View.VISIBLE
                        imagenReprocudiendo.visibility = View.GONE
                    });

                    mediaPlayer.start()
                    botonReproducir.setText(R.string.parar)
                    FancyToast() // context
                            .with(this) // gravity of FancyToast
                            .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                            .setIcon(R.mipmap.appicon_round) // set text for FancyToast
                            .setText(this.resources.getString(R.string.reproduciendoGrabacion)) // corner radius of FancyToast view
                            .cornerRadius(16f) // show/hide icon
                            .hideIcon(false) // finally show the FancyToast
                            .show()
                    //Si el botón pone parar, lo usamos para detener la grabación
                }else{
                    mediaPlayer.stop()
                    mediaPlayer.release()
                    botonReproducir.setText(R.string.reproducir)
                    botonEnviarGrabacion.isEnabled = true
                    botonGrabar.isEnabled = true
                    botonReproducir.isEnabled = true
                    botonEnviarGrabacion.visibility=View.VISIBLE
                    botonGrabar.visibility=View.VISIBLE
                    botonGrabar.visibility=View.VISIBLE
                    imagenReprocudiendo.visibility = View.GONE
                }
            }


    }

    /**
     * Método onStart, se usa únicamente para inicializar las animaciones
     */
    override fun onStart() {
        super.onStart()
        //Animación de enviar texto
        val desdeLaIzda: Animation = AnimationUtils.loadAnimation(this,
                R.anim.animacion_desde_la_dcha)
        contenedorEnviarTexto.startAnimation(desdeLaIzda)

        //Animación de enviar audio
        val aparecer: Animation = AnimationUtils.loadAnimation(this,
                R.anim.animacion_aparecer)
        contenedorEnviarAudio.startAnimation(aparecer)
    }

    /**
     * Pide los permisos peligrosos necesarios
     */
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.RECORD_AUDIO
        ), REQUEST_PERMISSION_CODE)
    }

    /**
     * gestiona lo que sucede cuando se conceden o no los permisos peligrosos necesarios
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
                                        }else{
                                            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                                        }
        }
    }

    /**
     * Comprueba si los permisos peligrosos requeridos ya han sido otorgados en este dispositivo
     */
    private fun checkPermissionFromDevice(): Boolean {
        var write_external_storage_result:Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var record_audio_result:Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Función onclick del botón enviar mensaje de texto. No implementada.
     */
    fun enviarMensajeTexto(view: View) {
        Log.d("evento de la vista",view.toString()) //Solo para eliminar warning de compilación
        FancyToast() // context
                .with(this) // gravity of FancyToast
                .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                .setIcon(R.drawable.error_sign) // set text for FancyToast
                .setText(this.resources.getString(R.string.pendienteDeImplementar)) // corner radius of FancyToast view
                .cornerRadius(16f) // show/hide icon
                .hideIcon(false) // finally show the FancyToast
                .show()
    }


    /**
     * Función onclick del botón ver mensajes de texto enviados. No implementada.
     */
    fun verMensajesTextoEnviados(view: View) {
        Log.d("evento de la vista",view.toString()) //Solo para eliminar warning de compilación
        FancyToast() // context
                .with(this) // gravity of FancyToast
                .setGravity(Gravity.CENTER, 0, 0) // set custom icon resource
                .setIcon(R.drawable.error_sign) // set text for FancyToast
                .setText(this.resources.getString(R.string.pendienteDeImplementar)) // corner radius of FancyToast view
                .cornerRadius(16f) // show/hide icon
                .hideIcon(false) // finally show the FancyToast
                .show()
    }
}