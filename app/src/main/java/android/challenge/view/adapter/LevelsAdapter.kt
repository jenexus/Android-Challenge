package android.challenge.view.adapter


import android.challenge.R
import android.challenge.model.LevelModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LevelsAdapter(private val levels: LevelModel) :
    RecyclerView.Adapter<LevelsAdapter.LevelViewHolder>() {
    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtLevel: TextView = itemView.findViewById(R.id.txtLevel)
        val imgLevel: ImageView = itemView.findViewById(R.id.imgLevel)
        val cardViewLevel: CardView = itemView.findViewById(R.id.cardViewLevel)


        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val recyclerViewActivities: RecyclerView =
            itemView.findViewById(R.id.recyclerViewActivities)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val availableLevel = levels?.levels?.count { it.state == "AVAILABLE" }

        if (availableLevel == levels.levels?.size) {
            return levels.levels?.size!!
        }
        if (availableLevel != null) {
            return availableLevel + 1
        }
        return 1
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val currentLevel = levels.levels?.get(position)

        if (currentLevel?.state == "AVAILABLE") {
            holder.txtTitle.text = currentLevel?.title
            holder.txtDescription.text = currentLevel?.description
            holder.txtLevel.text = "Level ${currentLevel?.level}"

            holder.recyclerViewActivities.layoutManager =
                GridLayoutManager(holder.recyclerViewActivities.context, 2)

            val layoutManager = holder.recyclerViewActivities.layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {

                    return if (position == layoutManager.itemCount - 1 && layoutManager.itemCount % 2 != 0) {
                        2 // Last item in an odd-sized list, span across both columns
                    } else {
                        1 // Normal behavior, span only one column
                    }
                }
            }
            holder.recyclerViewActivities.layoutManager = layoutManager
            val activitiesAdapter = ActivitiesAdapter(currentLevel?.activities)
            holder.recyclerViewActivities.adapter = activitiesAdapter
        } else {
            holder.txtDescription.visibility = View.INVISIBLE
            holder.cardViewLevel.visibility = View.INVISIBLE
            holder.txtTitle.text = "Technique surprise"
            holder.imgLevel.setImageResource(R.drawable.level_lock)
        }

    }
}