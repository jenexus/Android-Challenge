package android.challenge.view.adapter

import android.challenge.R
import android.challenge.model.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ActivitiesAdapter(private val activities: List<Activity>?) :
    RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHoler>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHoler {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        return ActivityViewHoler(itemView)
    }

    override fun getItemCount(): Int {
        return activities?.size ?: 0
    }

    override fun onBindViewHolder(holder: ActivityViewHoler, position: Int) {
        val currentActivity = activities?.get(position)
        holder.txtActivityTitle.text = currentActivity?.title

        when (currentActivity?.type) {
            "PRACTICE" -> {
                holder.imgActivity.setImageResource(R.drawable.practice)
            }
            "COMMIT" -> {
                holder.imgActivity.setImageResource(R.drawable.commit)
            }
            "RECAP" -> {
                holder.imgActivity.setImageResource(R.drawable.recap)
            }
        }

        when(currentActivity?.state){
            "NOT_SET" -> holder.imgCheckMark.visibility = View.INVISIBLE

            else -> holder.imgCheckMark.visibility = View.VISIBLE
        }

    }

    class ActivityViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtActivityTitle: TextView = itemView.findViewById(R.id.txtActivityTitle)
        val imgActivity: ImageView = itemView.findViewById(R.id.imgActivity)
        val imgCheckMark : ImageView = itemView.findViewById(R.id.imgCheckMark)
    }
}