package com.example.datetime_mvp.ui.mvp

import com.example.datetime_mvp.ui.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val api: ApiService = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    override fun onGetDateClicked() {
        coroutineScope.launch {
            try {
                view?.showLoading()
                val response = api.getCurrentDateTime()
                view?.showDateTime(response.currentDateTime)
            } catch (e: Exception) {
                view?.showError("Error: ${e.message}")
            } finally {
                view?.hideLoading()
            }
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        view = null
    }
}