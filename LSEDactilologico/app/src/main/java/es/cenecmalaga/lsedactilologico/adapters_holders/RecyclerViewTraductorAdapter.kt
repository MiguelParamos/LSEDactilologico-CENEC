package es.cenecmalaga.lsedactilologico.adapters_holders

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import com.soepic.sefancytoast.FancyToast


class RecyclerViewTraductorAdapter(val actividad: Activity, var datos: ArrayList<Char>): RecyclerView.Adapter<HolderTraductor>() {
    /*
    Metodo que infla
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTraductor {
       return HolderTraductor(
           actividad.layoutInflater.inflate(
               R.layout.elementos_recycle,
               parent,
               false
           )
       )
    }



    /**
     * Funcion para comprobar cuantos elementos hay
     */
    override fun getItemCount(): Int {
        return datos.size
    }
    override fun onBindViewHolder(holder: HolderTraductor, position: Int) {
        try {

            when (datos.get(position)) {
                'a' -> {
                    holder.imagen.setImageDrawable(getDrawable("aa"))
                }
                'ñ' -> {
                    holder.imagen.setImageDrawable(getDrawable("enie"))

                }
                ' ' -> {
                    holder.imagen.setImageResource(R.drawable.espacio)
                }
                else -> {
                    holder.imagen.setImageDrawable(getDrawable("" + datos.get(position)))
                }


            }
        }catch (e:Exception){
            FancyToast() // context
                .with(actividad) // gravity of FancyToast
                .setGravity(Gravity.BOTTOM, 0, 0) // set custom icon resource
                .setIcon(R.drawable.error_sign) // set text for FancyToast
                .setText(actividad.resources.getString(R.string.valorNoValido)+" "+datos.get(position)) // corner radius of FancyToast view
                .cornerRadius(16f) // show/hide icon
                .hideIcon(false) // finally show the FancyToast
                .show()
            holder.imagen.setImageResource(R.drawable.error_sign)
        }
    }

    fun getDrawable(name: String?): Drawable? {
        val resourceId: Int = actividad.getResources()
            .getIdentifier(name, "drawable", actividad.getPackageName())
        return ContextCompat.getDrawable(actividad,resourceId)
    }

}