package android.challenge.view.adapter


import android.challenge.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class DaysAdapter(private val daysOfWeek: List<String>, private val onItemClick: (Int) -> Unit = {}): RecyclerView.Adapter<DaysAdapter.ViewHolder>() {
    private var itemClick =  (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)  +5) %7;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daysOfWeek.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDay.text = daysOfWeek[position]
        holder.bind( position == itemClick)
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtDay : TextView = itemView.findViewById(R.id.txtDay)
        val imgDayStatus : ImageView = itemView.findViewById(R.id.imgDayStatus)
        val underLine : View = itemView.findViewById(R.id.underLine)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    itemClick = position
                    onItemClick(position)
                    notifyDataSetChanged()
                }
            }
        }

        fun bind(isSelected: Boolean){
            if(isSelected) {
                txtDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.purple))
                imgDayStatus.setImageResource(R.drawable.circle_filled)
                underLine.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.purple))
                underLine.layoutParams.height = 3
            } else {
                txtDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey))
                imgDayStatus.setImageResource(R.drawable.circle)
                underLine.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.grey))
                underLine.layoutParams.height = 1
            }
        }
    }
}

