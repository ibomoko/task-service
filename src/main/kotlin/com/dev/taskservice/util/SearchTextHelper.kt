package com.dev.taskservice.util

class SearchTextHelper {

   companion object {
       fun prepareSearchText(searchText: String): String {
           return if (searchText.isEmpty()) "%" else "%${searchText.trim().replace(" ", "%").lowercase()}%"
       }
   }
}