package com.example.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jsondemo.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = MainActivity::class.java.simpleName
    var userList: ArrayList<User> = ArrayList()
    lateinit var userArrayJson: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = assets.open("users.json")
        val jsonString = inputStream.bufferedReader().use { bufferedReader ->
            bufferedReader.readText()
        }
        Log.d(TAG, "JsonString is: $jsonString")
        val rootObject = JSONObject(jsonString)
        if (rootObject.has(USERS)) {
            userArrayJson = rootObject.getJSONArray(USERS)
        }else{
          userArrayJson = JSONArray()
        }
        for (index in 0 until userArrayJson.length()) {
            var name: String? = null
            var job: String? = null
            var age: Int? = null
            var city: String? = null
            var hobbys: ArrayList<String?> = ArrayList()
            var phonePrivate: String? = null
            var phoneOffice: String? = null
            var phoneMobile: String? = null

            val userJson = userArrayJson.getJSONObject(index)
            if (userJson.has(NAME))
                name = userJson.getString(NAME)
            if (userJson.has(JOB))
                job = userJson.getString(JOB)
            if (userJson.has(AGE))
                age = userJson.getInt(AGE)
            if (userJson.has(CITY))
                city = userJson.getString(CITY)
            if (userJson.has(HOBBYS)) {
                val hobbyArray = userJson.getJSONArray(HOBBYS)
                for (index_ in 0 until hobbyArray.length()) {
                    hobbys.add(hobbyArray.getString(index_))
                }

            }
            if(userJson.has(PHONE)){
                val phoneJson = userJson.getJSONObject(PHONE)

                if (phoneJson.has(PHONEMOBILE))
                    phoneMobile = phoneJson.getString(PHONEMOBILE)
                if (phoneJson.has(PHONEPRIVATE))
                    phonePrivate = phoneJson.getString(PHONEPRIVATE)
                if (phoneJson.has(PHONEOFFICE))
                    phoneOffice = phoneJson.getString(PHONEOFFICE)

            }

            val user = User(name,job,age,city,hobbys, Phone(phonePrivate,phoneOffice,phoneMobile))

            userList.add(user)
        }

        userList.forEach {
            binding.tvOutput.append(it.toString() + "\n")
        }

    }

    companion object {
        val USERS = "users"
        val NAME = "name"
        val AGE = "age"
        val JOB = "job"
        val CITY = "city"
        val HOBBYS = "hobbys"
        val PHONE = "phone"
        val PHONEPRIVATE = "private"
        val PHONEOFFICE = "office"
        val PHONEMOBILE = "mobile"
    }
}