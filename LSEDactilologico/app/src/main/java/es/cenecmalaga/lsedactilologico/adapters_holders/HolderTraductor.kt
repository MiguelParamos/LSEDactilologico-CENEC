package es.cenecmalaga.lsedactilologico.adapters_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R

class HolderTraductor (itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Inicializamos la variable imagen

    val imagen:ImageView by lazy {itemView.findViewById(R.id.fotoContribuidor)}// obtenemos la variable imagen
    val texto:TextView by lazy { itemView.findViewById(R.id.nombreContribuidor) }

}