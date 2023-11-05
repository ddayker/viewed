package com.dayker.viewed.details.feature.aboutapp.presentation

sealed class AboutAppEvent {
    data class ClickedTag(val intentUrl: String) : AboutAppEvent()
    data class ClickedBugReport(val mailtoUrl: String) : AboutAppEvent()
}