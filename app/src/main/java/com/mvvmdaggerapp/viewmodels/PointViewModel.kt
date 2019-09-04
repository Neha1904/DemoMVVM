package com.mvvmdaggerapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mvvmdaggerapp.model.Point
import com.mvvmdaggerapp.repository.PointRepository
import javax.inject.Inject

class PointViewModel @Inject constructor(pointRepository: PointRepository):ViewModel(){
    private var pointLiveData: LiveData<List<Point>> = pointRepository.getPointLiveData()

    fun getLiveData() : LiveData<List<Point>> {
        return pointLiveData
    }
}