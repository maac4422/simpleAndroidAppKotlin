package co.com.ceiba.libraryexample.Models

data class Response(var isSuccess: Boolean){
    var mesage: String = ""
    var result: Any? = null
}