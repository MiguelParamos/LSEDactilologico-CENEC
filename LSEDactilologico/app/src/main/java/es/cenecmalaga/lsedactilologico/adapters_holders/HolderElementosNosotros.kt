package es.cenecmalaga.lsedactilologico.adapters_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import kotlinx.android.synthetic.main.elementos_recycle.view.*

class HolderElementosNosotros (itemView : View):RecyclerView.ViewHolder(itemView){
    //inicializamos las variables del layout elementos_recycleview_nosotros
    val foto:ImageView by lazy { itemView.findViewById<ImageView>(R.id.fotoContribuidor) }
    val nombre:TextView by lazy { itemView.findViewById(R.id.nombreContribuidor) }

}