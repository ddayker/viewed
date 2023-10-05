package com.dayker.viewed.details.presentation.aboutapp

sealed class AboutAppEvent {
    data class ClickedTag(val intentUrl: String) : AboutAppEvent()
    data class ClickedBugReport(val mailtoUrl: String) : AboutAppEvent()
}