package com.example.countdays.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countdays.model.DateModel

import java.util.*

class MainViewModel : ViewModel() {

    private val _dateModel = MutableLiveData(DateModel())
    val dateModel: LiveData<DateModel> = _dateModel

    private val _newDate = MutableLiveData<Calendar>()
    val newDate: LiveData<Calendar> = _newDate

    fun setSelectedDate(date: Calendar) {
        _dateModel.value?.selectedDate = date
        _dateModel.postValue(_dateModel.value)
        calculateNewDate()
    }

    fun setDaysToAdd(days: Int) {
        _dateModel.value?.daysToAdd = days
        _dateModel.postValue(_dateModel.value)
        calculateNewDate()
    }

    private fun calculateNewDate() {
        val calendar = _dateModel.value?.selectedDate?.clone() as Calendar
        calendar.add(Calendar.DAY_OF_MONTH, _dateModel.value?.daysToAdd ?: 0)
        _newDate.postValue(calendar)
    }
}
