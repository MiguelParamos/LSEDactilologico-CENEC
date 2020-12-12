package es.cenecmalaga.lsedactilologico.adapters_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R

/**
 * ViewHolder para el RecyclerView que contendrá las imágenes de las letras que componen la palabra.
 * @author Nicolás Fernández Heredia
 * @author Rafael Carrión Ponce
 * @since 0.1
 */
class HolderTraductor (itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Inicializamos la variable imagen

    /** ImageView que contendrá la imagen de la letra que toque colocar en el holder **/
    val imagen:ImageView by lazy {itemView.findViewById(R.id.imagenLetra)}// obtenemos la variable imagen

}