package android.challenge.repository
import android.challenge.utilities.GLOBAL_TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.InputStream

class LevelRepository() {
    fun getLevels(fileName: String, context:Context) : String?{
        var input: InputStream? = null
        var jsonString: String
        try{
            input = context.assets.open(fileName)
            val size = input.available()
            val buffer= ByteArray(size)
            input.read(buffer)
            jsonString = String(buffer)
            return jsonString
        }
        catch (e: Exception){
            Log.e(GLOBAL_TAG,"Error: ${e.message}")
        }finally {
            input?.close()
        }
        return null
    }
}
