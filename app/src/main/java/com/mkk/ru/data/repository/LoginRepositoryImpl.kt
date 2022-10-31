package com.mkk.ru.data.repository

import com.mkk.ru.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun login() {
        delay(2000)
    }
}
