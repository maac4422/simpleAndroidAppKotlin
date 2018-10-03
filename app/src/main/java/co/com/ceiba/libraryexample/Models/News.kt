package co.com.ceiba.libraryexample.Models

import java.util.*

data class News(var id: Int){
    var author: String = ""
    var articleDate: Date? = null
    var title: String = ""
    var image: String = ""
}