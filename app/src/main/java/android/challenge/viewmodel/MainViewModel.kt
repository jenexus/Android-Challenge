package android.challenge.viewmodel

import android.challenge.model.LevelModel
import android.content.Context
import android.challenge.repository.LevelRepository
import android.challenge.utilities.GLOBAL_TAG
import android.challenge.utilities.LEVEL_DATA
import android.challenge.utilities.SHARED_PREF
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(private val repository: LevelRepository, private val gson: Gson) : ViewModel() {
    fun getItems(fileName: String, context: Context): LevelModel? {
        val levelType = object : TypeToken<LevelModel>() {}.type
        saveData(context,LEVEL_DATA,repository.getLevels(fileName, context)?:"")
        Log.e(GLOBAL_TAG,"Save Data: ${getData(context,LEVEL_DATA)}")
        return gson.fromJson(repository.getLevels(fileName, context), levelType)
    }

    fun saveData(context: Context, key: String, value: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(context: Context, key: String): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPref.getString(key, null)
    }
}