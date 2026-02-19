package com.example.countdays

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.countdays.databinding.ActivityMainBinding
import com.example.countdays.utils.dayOfWeekName
import com.example.countdays.utils.formatToDDMMYYYY
import com.example.countdays.viewmodel.MainViewModel
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val today = Calendar.getInstance()
        binding.etDate.setText(today.formatToDDMMYYYY())
        viewModel.setSelectedDate(today)

        binding.ivCalendar.setOnClickListener {
            val calendar = viewModel.dateModel.value?.selectedDate ?: Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selected = Calendar.getInstance()
                    selected.set(year, month, dayOfMonth)
                    binding.etDate.setText(selected.formatToDDMMYYYY())
                    viewModel.setSelectedDate(selected)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.etDays.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val days = s.toString().toIntOrNull() ?: 0
                viewModel.setDaysToAdd(days)
            }
        })

        viewModel.newDate.observe(this) { newDate ->
            binding.tvNewDate.text = newDate.formatToDDMMYYYY()
            binding.tvDayOfWeek.text = newDate.dayOfWeekName()
        }
    }
}