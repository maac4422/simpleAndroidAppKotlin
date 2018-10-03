package co.com.ceiba.libraryexample.Models

import java.util.*

data class Events(var id: Int?){
    var author: String = ""
    var baseTitle: String = ""
    var eventDate: Date? = null
    var title: String = ""
    var arena: String = ""
    var location: String = ""
    var image: String = ""
}