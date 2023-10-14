package com.dayker.viewed.details.presentation.aboutapp

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AboutAppViewModel() : ViewModel() {

    private val _eventFlow = MutableSharedFlow<Intent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AboutAppEvent) {
        when (event) {
            is AboutAppEvent.ClickedBugReport -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse(event.mailtoUrl)
                viewModelScope.launch {
                    _eventFlow.emit(intent)
                }
            }

            is AboutAppEvent.ClickedTag -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(event.intentUrl)
                viewModelScope.launch {
                    _eventFlow.emit(intent)
                }
            }
        }
    }
}