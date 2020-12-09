package es.cenecmalaga.lsedactilologico.actividades

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.ActivityChooserView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.NicolasFernandez.lsedactilologico.R
import kotlinx.android.synthetic.main.activity_ayudanos_a_mejorar.*
import java.io.File
import java.io.IOException
import java.util.*
import java.util.jar.Manifest

class AyudanosAMejorar : AppCompatActivity() {

    final var REQUEST_PERMISSION_CODE:Int = 1000
    var pathSave:String = ""
    var mediaRecorder:MediaRecorder = MediaRecorder()
    var mediaPlayer:MediaPlayer = MediaPlayer()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayudanos_a_mejorar)
        val externalStorageVolumes: Array<out File> =
        ContextCompat.getExternalFilesDirs(applicationContext, null)
        pathSave ="${externalStorageVolumes[0].absolutePath}/grabacion.3gp"

        if (!checkPermissionFromDevice()) {
            requestPermission()
        }

        var botonReproducir:Button = findViewById(R.id.botonReproducir)
        var botonGuardar:Button = findViewById(R.id.botonGuardar)
        var botonParar:Button = findViewById(R.id.botonParar)
        var botonGrabar:Button = findViewById(R.id.botonGrabar)
        var imagenGrabando:ImageView = findViewById(R.id.imagenGrabando)
        var imagenReprocudiendo:ImageView = findViewById(R.id.imagenReproduciendo)
        imagenGrabando.visibility = View.GONE
        imagenReprocudiendo.visibility = View.GONE
        botonGuardar.isEnabled = false


            botonGrabar.setOnClickListener{
                if (checkPermissionFromDevice()) {
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                    mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
                    println(pathSave)
                    mediaRecorder.setOutputFile(pathSave)

                    try {
                        mediaRecorder.prepare()
                        mediaRecorder.start()
                    } catch (e: IOException) {
                        e.printStackTrace();
                    }

                    botonReproducir.isEnabled = false
                    botonParar.isEnabled = false
                    botonGuardar.isEnabled = true
                    imagenGrabando.visibility = View.VISIBLE

                    Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show()
                }else{
                    requestPermission()
                }
            }
            botonGuardar.setOnClickListener {
                mediaRecorder.stop()
                botonGuardar.isEnabled = false
                botonReproducir.isEnabled = true
                botonGrabar.isEnabled = true
                botonParar.isEnabled = false
                imagenGrabando.visibility = View.GONE
            }
            botonReproducir.setOnClickListener {
                botonParar.isEnabled = true
                botonGuardar.isEnabled = false
                botonGrabar.isEnabled = false
                botonReproducir.isEnabled = false
                imagenReprocudiendo.visibility = View.VISIBLE

                mediaPlayer = MediaPlayer()
                try {
                    mediaPlayer.setDataSource(pathSave)
                    mediaPlayer.prepare()
                }catch (e:IOException){
                    e.printStackTrace()
                }

                mediaPlayer.start()
                Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show()
            }
            botonParar.setOnClickListener {
                botonGuardar.isEnabled = false
                botonGrabar.isEnabled = true
                botonParar.isEnabled = false
                botonReproducir.isEnabled = true
                imagenReprocudiendo.visibility = View.GONE

                mediaPlayer.stop()
                mediaPlayer.release()

            }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.RECORD_AUDIO
        ), REQUEST_PERMISSION_CODE)
    }

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

    private fun checkPermissionFromDevice(): Boolean {
        var write_external_storage_result:Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var record_audio_result:Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED
    }
}