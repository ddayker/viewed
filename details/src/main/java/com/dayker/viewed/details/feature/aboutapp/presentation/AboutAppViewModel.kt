package com.dayker.viewed.details.feature.aboutapp.presentation

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class AboutAppViewModel() : ViewModel() {

    private val _intentFlow = MutableSharedFlow<Intent>()
    val intentFlow = _intentFlow.asSharedFlow()

    fun onEvent(event: AboutAppEvent) {
        when (event) {
            is AboutAppEvent.ClickedBugReport -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse(event.mailtoUrl)
                viewModelScope.launch {
                    _intentFlow.emit(intent)
                }
            }

            is AboutAppEvent.ClickedTag -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(event.intentUrl)
                viewModelScope.launch {
                    _intentFlow.emit(intent)
                }
            }
        }
    }
}