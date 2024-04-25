package android.challenge.view.adapter

import android.challenge.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class DaysAdapter(private val daysOfWeek: List<String>): RecyclerView.Adapter<DaysAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daysOfWeek.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDay.text = daysOfWeek[position]


        val isCurrentDay = position == (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)  +5) %7

        if(isCurrentDay){
            holder.txtDay.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.purple))
            holder.imgDayStatus.setImageResource(  R.drawable.circle_filled)
            holder.underLine.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.purple))
            holder.underLine.layoutParams.height = 3
        }else{
            holder.txtDay.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.grey))
            holder.imgDayStatus.setImageResource(  R.drawable.circle)
            holder.underLine.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.grey))
            holder.underLine.layoutParams.height = 1
        }
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtDay : TextView = itemView.findViewById(R.id.txtDay)
        val imgDayStatus : ImageView = itemView.findViewById(R.id.imgDayStatus)
        val underLine : View = itemView.findViewById(R.id.underLine)
    }


}