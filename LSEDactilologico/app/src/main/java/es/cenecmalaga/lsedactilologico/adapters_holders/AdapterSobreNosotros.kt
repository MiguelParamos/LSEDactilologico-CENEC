package es.cenecmalaga.lsedactilologico.adapters_holders

import android.app.Activity
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import es.cenecmalaga.lsedactilologico.clases.Contributor
import kotlin.collections.ArrayList

/**
 * Adapter del RecyclerView sobre nosotros. Coloca a partir de un ArrayList de Contributor, la información en cada entrada del Recycler.
 * @author Miguel Páramos
 * @author Nicolás Fernández Heredia
 * @since 0.2
 */
class AdapterSobreNosotros (val actividad: Activity, val contribuidores:ArrayList<Contributor> )
    : RecyclerView.Adapter<HolderSobreNosotros>() {

    /**
     * Infla la vista
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSobreNosotros {
       return HolderSobreNosotros(actividad.layoutInflater.inflate(R.layout.elementos_recycleview_nosotros,parent,false))
    }


    /**
     * Funcion para comprobar elementos hay
     */
    override fun getItemCount(): Int {
        return contribuidores.size
    }

    /**
     * Liga los elementos de la vista al elemento correspondiente del Array de contribuidores.
     */
    override fun onBindViewHolder(holder: HolderSobreNosotros, position: Int) {

        holder.foto.setImageResource(actividad.resources.getIdentifier(contribuidores.get(position).nombreImagen,"drawable",actividad.packageName))
        holder.nombre.setText(contribuidores.get(position).nombre)
        holder.rol.setText(contribuidores.get(position).rol)
        holder.contribucion.setText(contribuidores.get(position).contribucion)
    }


}