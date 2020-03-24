package com.angel.stocksearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.stocksearch.rest.client.ApiClient
import com.angel.stocksearch.rest.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    var friends: MutableLiveData<Users> = MutableLiveData()

    fun fetchFriends() {
        viewModelScope.launch {
            friends.value = withContext(Dispatchers.IO) {
                try {
                    ApiClient.getApiService().getUsers().execute().body()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }
    }
}