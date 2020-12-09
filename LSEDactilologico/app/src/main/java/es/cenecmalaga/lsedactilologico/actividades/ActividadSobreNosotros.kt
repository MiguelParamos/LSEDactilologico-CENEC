package es.cenecmalaga.lsedactilologico.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import es.cenecmalaga.lsedactilologico.adapters_holders.RecyclerViewSobreNostrosAdapter

class ActividadSobreNosotros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_sobre_nosotros)

        val nombres:ArrayList<String> = ArrayList<String>()

        nombres.add("Nicolas Fernandez Heredia")
        nombres.add("Kevin Raak")
        nombres.add("Rafa Carrión Ponce")
        val imagenesContribuidores:ArrayList<String> = ArrayList<String>()
        imagenesContribuidores.add("nico")
        imagenesContribuidores.add("kevin")
        imagenesContribuidores.add("rafa")

        val contenedorRecycler: RecyclerView = findViewById(R.id.listaContribuidores)

        val adapter:RecyclerViewSobreNostrosAdapter= RecyclerViewSobreNostrosAdapter(this,nombres,imagenesContribuidores)

        contenedorRecycler.adapter=adapter

        contenedorRecycler.layoutManager=LinearLayoutManager(this)
    }
}