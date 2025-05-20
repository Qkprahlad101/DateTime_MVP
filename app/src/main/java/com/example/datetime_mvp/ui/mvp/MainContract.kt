package com.example.datetime_mvp.ui.mvp

interface MainContract {

    interface View {
        fun showDateTime(date: String)
        fun showError(errorMessage: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onGetDateClicked()
        fun onDestroy()
    }
}