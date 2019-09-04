package com.mvvmdaggerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmdaggerapp.model.Point
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Inject

class PointRepository @Inject constructor(){
    private val liveData = MutableLiveData<List<Point>>()

    companion object {
        private var instance: PointRepository? = null
        fun getInstance(): PointRepository? {
            if (instance == null) {
                synchronized(PointRepository::class.java) {
                    if (instance == null) {
                        instance = PointRepository()
                    }
                }
            }
            return instance
        }
    }

    fun getPointLiveData(): LiveData<List<Point>> {
        loadData()
        return liveData
    }

    fun loadData() {
        run {
            val gson = Gson()
            val jsonObject = gson.fromJson(getData(), JsonObject::class.java)
            val array = jsonObject.get("data").asJsonArray
            val list = ArrayList<Point>()
            for (data in array) {
                list.add(Point(data as JsonObject))
            }
            liveData.postValue(list)
        }
    }

    fun getData(): String {
        val data = "{\"data\":[{\"id\":\"130\",\"type\":\"gam\",\"attributes\":{\"points\":15,\"description\":\"Delivered\",\"self_selected\":false,\"num_awarded\":1,\"enabled\":true,\"allow_multiple\":false,\"type\":\"CompleteVisit\"}},{\"id\":\"288\",\"type\":\"gam\",\"attributes\":{\"points\":15,\"description\":\"Completed\",\"self_selected\":true,\"num_awarded\":1,\"enabled\":true,\"allow_multiple\":false,\"type\":\"ActivateSetupData\"}},{\"id\":\"290\",\"type\":\"gam\",\"attributes\":{\"points\":15,\"description\":\"Product\",\"self_selected\":true,\"num_awarded\":1,\"enabled\":true,\"allow_multiple\":false,\"type\":\"ProductTraining\"}},{\"id\":\"292\",\"type\":\"gam\",\"attributes\":{\"points\":15,\"description\":\"Demonstrated\",\"self_selected\":true,\"num_awarded\":1,\"enabled\":true,\"allow_multiple\":false,\"type\":\"DemoGoogleAssist\"}},{\"id\":\"294\",\"type\":\"gam\",\"attributes\":{\"points\":250,\"description\":\"Converted\",\"self_selected\":true,\"num_awarded\":0,\"enabled\":true,\"allow_multiple\":true,\"type\":\"ConvertToFi\"}},{\"id\":\"400\",\"type\":\"gam\",\"attributes\":{\"points\":25,\"description\":\"Added\",\"self_selected\":true,\"num_awarded\":0,\"enabled\":true,\"allow_multiple\":true,\"type\":\"ProvidedHeroAccessory\"}},{\"id\":\"401\",\"type\":\"gam\",\"attributes\":{\"points\":50,\"description\":\"Added\",\"self_selected\":true,\"num_awarded\":0,\"enabled\":true,\"allow_multiple\":true,\"type\":\"ProvidedHomeEcosystemProduct\"}},{\"id\":\"601\",\"type\":\"gamif\",\"attributes\":{\"points\":200,\"description\":\"Added\",\"self_selected\":true,\"num_awarded\":0,\"enabled\":true,\"allow_multiple\":true,\"type\":\"AddedPixel\"}}]}"
        return data
    }
}