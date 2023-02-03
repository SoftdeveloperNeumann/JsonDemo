package com.example.jsondemo




data class Liste(
    val desription:String?,
    val isUser:Boolean?,
    val empty: Any?,
    val since:Int?,
    val version:Double?,
    val users: ArrayList<User>
)

data class User(
    val name:String?,
    val job:String?,
    val age : Int?,
    val city: String?,
    val hobbys : ArrayList<String?>,
    val phone: Phone?
)

data class Phone(
    val private:String?,
    val office:String?,
    val mobile:String?
)