package android.challenge

import android.challenge.databinding.ActivityMainBinding
import android.challenge.model.Activity
import android.challenge.model.Level
import android.challenge.model.LevelModel
import android.challenge.view.adapter.DaysAdapter
import android.challenge.view.adapter.LevelsAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var daysAdapter: DaysAdapter

    private val daysOfWeek: List<String> = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        daysAdapter = DaysAdapter(daysOfWeek)

        binding.recyclerViewDays.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewDays.adapter = daysAdapter
        val gson = Gson()
        val levelType = object : TypeToken<LevelModel>() {}.type
        val value = gson.fromJson<LevelModel>(loadJson(this), levelType)
        val levelsAdapter = LevelsAdapter(value)
        binding.recyclerViewLevels.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewLevels.adapter = levelsAdapter
    }
    private fun loadJson(context : Context) : String? {

        var input: InputStream? = null
        var jsonString: String
        try{
            input = context.assets.open("dummy_response.json")
            val size = input.available()
            val buffer= ByteArray(size)
            input.read(buffer)
            jsonString = String(buffer)
            return jsonString
        }
        catch (e: Exception){
            Log.e("TAG","Error: ${e.message}")
        }finally {
            input?.close()
        }
        return null
    }
}