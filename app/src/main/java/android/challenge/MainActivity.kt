package android.challenge

import android.challenge.databinding.ActivityMainBinding
import android.challenge.utilities.JSON_FILE_NAME
import android.challenge.view.adapter.DaysAdapter
import android.challenge.view.adapter.LevelsAdapter
import android.challenge.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var daysAdapter: DaysAdapter

    private val daysOfWeek: List<String> = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        daysAdapter = DaysAdapter(daysOfWeek)
        binding.recyclerViewDays.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewDays.adapter = daysAdapter

        val items = mainViewModel.getItems(JSON_FILE_NAME,this)
        val levelsAdapter = items?.let { LevelsAdapter(it) }
        binding.recyclerViewLevels.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewLevels.adapter = levelsAdapter
    }
}