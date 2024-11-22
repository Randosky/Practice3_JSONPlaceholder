package com.ovinkin.practice3_jsonplaceholder.storage.cache.badgeCache

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BadgeCache {
    private val _badgeFilterCount = MutableStateFlow(0)
    val badgeFilterCount: StateFlow<Int> get() = _badgeFilterCount

    fun updateBadgeFilterCount(usernameFilter: String?, postContentFilter: String?) {
        var count = 0
        if (!usernameFilter.isNullOrEmpty()) count++
        if (!postContentFilter.isNullOrEmpty()) count++
        _badgeFilterCount.value = count
    }
}
