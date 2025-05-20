package com.example.datetime_mvp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.datetime_mvp.R
import com.example.datetime_mvp.ui.mvp.MainContract
import com.example.datetime_mvp.ui.mvp.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var btnFetchDate: Button
    private lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDate = findViewById(R.id.textViewDate)
        btnFetchDate = findViewById(R.id.buttonGetDate)
        presenter = MainPresenter(this)
        btnFetchDate.setOnClickListener{
            presenter.onGetDateClicked()
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun showDateTime(date: String) {
        runOnUiThread {
            tvDate.text = date
        }
    }

    override fun showError(errorMessage: String) {
        tvDate.text = errorMessage
    }

    override fun showLoading() {
       runOnUiThread{
           findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
       }
    }

    override fun hideLoading() {
        runOnUiThread{
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}