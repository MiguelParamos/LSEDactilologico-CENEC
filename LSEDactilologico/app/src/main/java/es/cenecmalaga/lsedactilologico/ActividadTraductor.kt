package com.NicolasFernandez.lsedactilologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ActividadTraductor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_traductor)




    }
    //funcion para que cuando le demos un click traduzca el texto que hayamos escrito mediante imagenes
    fun TraducirTexto(view: View) {





        //es nuestro contener vertical
        var contenedor:LinearLayout =this.findViewById<LinearLayout>(R.id.contenedor)//buscar la id del xml y pasarlo a una variable
        //nuestro texto principal
        contenedor.removeAllViewsInLayout()//borrar las imagenes del scrollview ya puestas
        var  texto:EditText=this.findViewById(R.id.txtTraducir)
        //Convertimos a String el EditText donde hemos escrito las palabras
        var cadena:String= texto.text.toString()
        //para realizar una prueba lo hemos sacado por pantalla
        Toast.makeText(this,cadena,Toast.LENGTH_LONG).show()
        //probar este for mediante este for vamos a intentar ir cogiendo las imagenes  e ir añadiendolas
        for (i in 0 until cadena.length){
            var imagen:ImageView= ImageView(this)
            when(cadena.get(i)){
                'a'-> {
                    imagen.setImageResource(R.drawable.aa)
                    contenedor.addView(imagen)
                }
                'b'-> {
                    imagen.setImageResource(R.drawable.b)
                    contenedor.addView(imagen)
                }

                'c'->{
                    imagen.setImageResource(R.drawable.c)
                    contenedor.addView(imagen)
                }
                'd'->{
                    imagen.setImageResource(R.drawable.d)
                    contenedor.addView(imagen)
                }
                'e'->{
                    imagen.setImageResource(R.drawable.e)
                    contenedor.addView(imagen)
                }
                'f'->{
                    imagen.setImageResource(R.drawable.f)
                    contenedor.addView(imagen)
                }
                'g'->{
                    imagen.setImageResource(R.drawable.g)
                    contenedor.addView(imagen)
                }
                'h'->{
                    imagen.setImageResource(R.drawable.h)
                    contenedor.addView(imagen)
                }
                'i'->{
                    imagen.setImageResource(R.drawable.i)
                    contenedor.addView(imagen)

                }
                'j'->{
                    imagen.setImageResource(R.drawable.j)
                    contenedor.addView(imagen)
                }
                'k'->{
                    imagen.setImageResource(R.drawable.k)
                    contenedor.addView(imagen)
                }
                'l'->{
                    imagen.setImageResource(R.drawable.l)
                    contenedor.addView(imagen)
                }
                //  'll'->imagen.setImageResource(R.drawable.ll) corregir esto de la ll
                'ñ'->{
                    imagen.setImageResource(R.drawable.enie)
                    contenedor.addView(imagen)
                }
                'n'->{
                    imagen.setImageResource(R.drawable.n)
                    contenedor.addView(imagen)
                }
                'm'->{
                    imagen.setImageResource(R.drawable.m)
                    contenedor.addView(imagen)
                }
                'o'->{
                    imagen.setImageResource(R.drawable.o)
                    contenedor.addView(imagen)

                }
                'p'-> {
                    imagen.setImageResource(R.drawable.p)
                    contenedor.addView(imagen)
                }
                'q'->{

                    imagen.setImageResource(R.drawable.q)
                    contenedor.addView(imagen)
                }
                'r'->{
                    imagen.setImageResource(R.drawable.r)
                    contenedor.addView(imagen)
                }
                // 'rr'->imagen.setImageResource(R.drawable.rr)
                's'->{
                    imagen.setImageResource(R.drawable.s)
                    contenedor.addView(imagen)
                }
                't'->{
                    imagen.setImageResource(R.drawable.t)
                    contenedor.addView(imagen)
                }
                'u'-> {
                    imagen.setImageResource(R.drawable.u)
                    contenedor.addView(imagen)
                }
                'v'->{
                    imagen.setImageResource(R.drawable.v)
                    contenedor.addView(imagen)
                }

                'w'->{
                    imagen.setImageResource(R.drawable.w)
                    contenedor.addView(imagen)
                }
                'x'->{
                    imagen.setImageResource(R.drawable.x)
                    contenedor.addView(imagen)
                }
                'y'->{
                    imagen.setImageResource(R.drawable.y)
                    contenedor.addView(imagen)
                }
                'z'->{
                    imagen.setImageResource(R.drawable.z)
                    contenedor.addView(imagen)

                }


            }




        }
    }
}