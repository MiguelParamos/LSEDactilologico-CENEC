package es.cenecmalaga.lsedactilologico.adapters_holders

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R

class RecyclerViewTraductorAdapter (val actividad :Activity, var datos :ArrayList<Char>): RecyclerView.Adapter<HolderTraductor>() {
    /*
    Metodo que infla
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTraductor {
       return HolderTraductor(actividad.layoutInflater.inflate(R.layout.elementos_recycle,parent,false))
    }



    /**
     * Funcion para comprobar cuantos elementos hay
     */
    override fun getItemCount(): Int {
        return datos.size
    }
    override fun onBindViewHolder(holder: HolderTraductor, position: Int) {

        when(datos.get(position)){
            'a'-> {
                holder.imagen.setImageResource(R.drawable.aa)

            }
            'b'-> {

                holder.imagen.setImageResource(R.drawable.b)

            }

            'c'->{

                holder.imagen.setImageResource(R.drawable.c)

            }
            'd'->{
                holder.imagen.setImageResource(R.drawable.d)

            }
            'e'->{
                holder.imagen.setImageResource(R.drawable.e)
            }
            'f'->{
                holder.imagen.setImageResource(R.drawable.f)

            }
            'g'->{
                holder.imagen.setImageResource(R.drawable.g)
            }
            'h'->{
                holder.imagen.setImageResource(R.drawable.h)
            }
            'i'->{
                holder.imagen.setImageResource(R.drawable.i)

            }
            'j'->{
                holder.imagen.setImageResource(R.drawable.j)
            }
            'k'->{
                holder.imagen.setImageResource(R.drawable.k)
            }
            'l'->{
                holder.imagen.setImageResource(R.drawable.l)
            }
            //  'll'->imagen.setImageResource(R.drawable.ll) corregir esto de la ll
            'ñ'->{
                holder.imagen.setImageResource(R.drawable.enie)
            }
            'n'->{
                holder.imagen.setImageResource(R.drawable.n)

            }
            'm'->{
                holder.imagen.setImageResource(R.drawable.m)

            }
            'o'->{
                holder.imagen.setImageResource(R.drawable.o)


            }
            'p'-> {
                holder.imagen.setImageResource(R.drawable.p)
            }
            'q'->{

                holder.imagen.setImageResource(R.drawable.q)

            }
            'r'->{
                holder.imagen.setImageResource(R.drawable.r)

            }
            // 'rr'->imagen.setImageResource(R.drawable.rr)
            's'->{
                holder.imagen.setImageResource(R.drawable.s)

            }
            't'->{
                holder.imagen.setImageResource(R.drawable.t)

            }
            'u'-> {
                holder.imagen.setImageResource(R.drawable.u)

            }
            'v'->{
                holder.imagen.setImageResource(R.drawable.v)

            }

            'w'->{
                holder.imagen.setImageResource(R.drawable.w)

            }
            'x'->{
                holder.imagen.setImageResource(R.drawable.x)

            }
            'y'->{
                holder.imagen.setImageResource(R.drawable.y)

            }
            'z'->{
                holder.imagen.setImageResource(R.drawable.z)


            }
            ' '->{
            holder.imagen.setImageResource(R.drawable.ic_launcher_background)
            }


        }
    }

}