package es.cenecmalaga.lsedactilologico.adapters_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import kotlinx.android.synthetic.main.elementos_recycle_traduccion.view.*

/**
 * ViewHolder para el RecyclerView de la actividad sobre nosotros
 * @author Miguel Páramos
 * @author Nicolás Fernández Heredia
 * @since 0.2
 */
class HolderSobreNosotros (itemView : View):RecyclerView.ViewHolder(itemView){

    /** fotografía del contributor **/
    val foto:ImageView by lazy { itemView.findViewById<ImageView>(R.id.fotoContributor) }
    /** Nombre del contributor **/
    val nombre:TextView by lazy { itemView.findViewById(R.id.nombreContributor) }
    /** Rol del contributor. Puede ser "Alumno" o "Profesor". **/
    val rol:TextView by lazy { itemView.findViewById(R.id.rolContributor)}
    /** Descripción de la contribución que ese contributor ha realizado al código **/
    val contribucion:TextView by lazy { itemView.findViewById(R.id.contribucion) }

}