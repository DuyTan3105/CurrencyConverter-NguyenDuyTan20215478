package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.data.ExchangeRates
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Thiết lập các loại tiền tệ
        val currencies = listOf("USD", "VND", "EUR", "JPY", "GBP")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        // Lắng nghe sự kiện thay đổi trong EditText
        binding.amountInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertCurrencyFromInput() // Gọi hàm chuyển đổi khi giá trị thay đổi
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Lắng nghe sự kiện thay đổi trong spinnerFrom
        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                convertCurrencyFromInput() // Cập nhật giá trị khi loại tiền tệ 'from' thay đổi
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Lắng nghe sự kiện thay đổi trong spinnerTo
        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                convertCurrencyFromInput() // Cập nhật giá trị khi loại tiền tệ 'to' thay đổi
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Cập nhật thời gian và tỷ giá
        binding.updateRatesButton.setOnClickListener {
            updateExchangeRateDisplay()
        }

        updateExchangeRateDisplay()
    }

    private fun convertCurrencyFromInput() {
        val from = binding.spinnerFrom.selectedItem.toString()
        val to = binding.spinnerTo.selectedItem.toString()
        val amountText = binding.amountInput.text.toString()

        // Kiểm tra nếu không có giá trị trong amountInput
        if (amountText.isEmpty()) {
            binding.resultText.text = "0.00" // Hiển thị 0.00
            binding.exchangeRateText.text = "" // Xóa tỷ giá
            return // Trở về để không thực hiện chuyển đổi
        }

        val amount = amountText.toDoubleOrNull() ?: return // Kiểm tra giá trị null
        if (amount > 0) { // Chỉ thực hiện chuyển đổi nếu amount > 0
            convertCurrency(from, to, amount)
        } else {
            binding.resultText.text = "0.00" // Nếu amount <= 0, hiển thị 0.00
            binding.exchangeRateText.text = "" // Xóa tỷ giá
        }
    }

    private fun convertCurrency(from: String, to: String, amount: Double) {
        val key = "${from}_$to"
        val rate = ExchangeRates.rates[key] ?: 1.00 // Nếu không tìm thấy tỷ giá, sử dụng 1.00

        // Tính toán số tiền đã chuyển đổi
        val convertedAmount = amount * rate

        // Cập nhật giao diện người dùng
        binding.resultText.text = String.format("%.f", convertedAmount)
        binding.exchangeRateText.text = "1 $from = $rate $to" // Cập nhật tỷ giá tương ứng
    }

    private fun updateExchangeRateDisplay() {
        val currentDate = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).format(Date())
        binding.lastUpdatedText.text = "Updated $currentDate"
    }
}
