package com.example.countdays.model

import java.util.*

data class DateModel(
    var selectedDate: Calendar = Calendar.getInstance(),
    var daysToAdd: Int = 0
)