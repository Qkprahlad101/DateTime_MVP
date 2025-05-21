package com.example.datetime_mvp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.datetime_mvp.R
import com.example.datetime_mvp.ui.mvp.MainContract
import com.example.datetime_mvp.ui.mvp.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter
    private var currentDate by mutableStateOf("1 Jan 1")
    private var isLoading by mutableStateOf(false)
    private var error by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)

        setContent {
            MaterialTheme {
                MainScreen(
                    currentDate = currentDate,
                    isLoading = isLoading,
                    error = error,
                    onGetDateClick = { presenter.onGetDateClicked() }
                )
            }
        }
    }

    override fun showDateTime(date: String) {
        runOnUiThread {
            currentDate = date
            isLoading = false
            error = ""
            Log.d(TAG, "showDateTime: $date")
        }
    }

    override fun showError(errorMessage: String) {
        runOnUiThread {
            error = errorMessage
            isLoading = false
            Log.d(TAG, "showError: $errorMessage")
        }
    }

    override fun showLoading() {
        runOnUiThread {
            isLoading = true
        }
    }

    override fun hideLoading() {
        runOnUiThread {
            isLoading = false
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        val TAG = "PRAHLAD"
    }
}