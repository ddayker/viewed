package com.dayker.viewed.presentation.details.aboutapp

sealed class AboutAppEvent {
    data class ClickedTag(val intentUrl: String) : AboutAppEvent()
    data class ClickedBugReport(val mailtoUrl: String) : AboutAppEvent()
}