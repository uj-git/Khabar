package com.umang.newsapp.domain.usecases.appentry

import com.umang.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }

}