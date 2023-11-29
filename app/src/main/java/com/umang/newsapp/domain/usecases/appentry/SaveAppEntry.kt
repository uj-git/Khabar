package com.umang.newsapp.domain.usecases.appentry

import com.umang.newsapp.data.manager.LocalUserManagerImpl
import com.umang.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}