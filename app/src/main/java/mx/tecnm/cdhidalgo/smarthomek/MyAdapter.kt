package mx.tecnm.cdhidalgo.smarthomek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val datos: Array<Array<String?>>, private val myListener: MyListener):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder (itemSensor: View): RecyclerView.ViewHolder(itemSensor) {
        lateinit var tvId: TextView
        lateinit var tvSensor: TextView
        lateinit var tvValor: TextView
        lateinit var tvFecha: TextView

        lateinit var bEdit: ImageView
        lateinit var bDel: ImageView

        init {
            tvId = itemSensor.findViewById(R.id.tvId)
            tvSensor = itemSensor.findViewById(R.id.tvSensor)
            tvValor = itemSensor.findViewById(R.id.tvValor)
            tvFecha = itemSensor.findViewById(R.id.tvFecha)
            bEdit = itemSensor.findViewById(R.id.bEdit)
            bDel = itemSensor.findViewById(R.id.bDel)
            bEdit.setOnClickListener{myListener.onClickEdit(adapterPosition)}
            bDel.setOnClickListener{myListener.onClickDel(adapterPosition)}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemSensor: View = LayoutInflater.from(parent.context).
            inflate(R.layout.item_sensor, parent, false)
        return ViewHolder(itemSensor)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvId.text = datos[position][0]
        holder.tvSensor.text = datos[position][1]
        holder.tvValor.text = datos[position][2]
        holder.tvFecha.text = datos[position][3]
    }


}