package es.cenecmalaga.lsedactilologico.actividades

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import es.cenecmalaga.lsedactilologico.adapters_holders.AdapterSobreNosotros
import es.cenecmalaga.lsedactilologico.clases.ActividadBase
import es.cenecmalaga.lsedactilologico.clases.Contributor
import kotlin.collections.ArrayList

/**
 * Clase que muestra la actividad Sobre Nosotros
 * @author Miguel Páramos
 * @author Nicolás Fernandez Heredia
 */
class ActividadSobreNosotros : ActividadBase(R.layout.actividad_sobre_nosotros) {

    /**
     * Función onCreate, que rellena e inicializa el RecyclerView donde se muestran los contribuidores.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contribuidores: ArrayList<Contributor> = ArrayList<Contributor>();

        var contributor1:Contributor= Contributor("Nicolás Fernandez Heredia","Alumno","Diseño inicial, y consistencia de datos al girar la app","nico")
        var contributor2:Contributor= Contributor("Kevin Rääk","Alumno","Grabación de voz para la actividad \"Ayúdanos a mejorar\"","kevin")
        var contributor3:Contributor= Contributor("Rafa Carrión Ponce","Alumno","Botón para limpiar traducción","rafa")
        var contributor4:Contributor= Contributor("Francisco España Quintana","Alumno","Puesta al día de llamadas a funciones que se quedaron obsoletas","francisco")
        var contributor5:Contributor= Contributor("Miguel Páramos","Profesor","Mejoras en eficiencia, notación y aspecto","miguel")


        contribuidores.add(contributor4)
        contribuidores.add(contributor2)
        contribuidores.add(contributor1)
        contribuidores.add(contributor3)
        contribuidores.add(contributor5)

        val contenedorRecycler: RecyclerView = findViewById(R.id.listaContribuidores)
        val adapter:AdapterSobreNosotros= AdapterSobreNosotros(this,contribuidores)

        contenedorRecycler.adapter=adapter

        contenedorRecycler.layoutManager=LinearLayoutManager(this)
    }
}