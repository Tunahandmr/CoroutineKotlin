package com.tunahan.coroutineskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //run blocking-> aradaki kodlar bitene kadar sonraki kodlar bloklanır

        println("run block start")
       runBlocking {
           launch {
               delay(5000)
               println("run block")
           }
       }
        println("run block end")



        //Global Scope

        println("global scope start")
        GlobalScope.launch {
            delay(5000)
            println("global scope")
        }
        println("global scope end")



        //Coroutine Scope

        println("coroutine scope start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("coroutine scope")
        }
        println("coroutine scope end")



        corotuineScp()
        coroDispatchers()

      runBlocking {
            delay(2000)
            println("run block")
            suspendFun()
        }



        myAsync()
        myJob()

        withContextCorotuines()


    }

    fun corotuineScp(){

        runBlocking {
            launch {
                delay(5000)
                println("run block")
            }

            coroutineScope {
                delay(3000)
                println("coroutine scope")
            }
        }
    }

    fun coroDispatchers(){

        runBlocking {
            launch(Dispatchers.Main) {
                println("Main Thread: +${Thread.currentThread().name}")
            }

            launch(Dispatchers.IO) {
                println("IO Thread: +${Thread.currentThread().name}")
            }

            launch(Dispatchers.Default) {
                println("Default Thread: +${Thread.currentThread().name}")
            }

            launch(Dispatchers.Unconfined) {
                println("Unconfined Thread: +${Thread.currentThread().name}")
            }
        }
    }

    suspend fun suspendFun(){
        coroutineScope {
            delay(5000)
            println("suspend fun")
        }
    }

    suspend fun downName():String{

        delay(2000)
        val userName = "Tunahan:"
        println("Username Download")
        return userName

    }

    suspend fun downAge():Int{

        delay(4000)
        val userAge = 19
        println("Userage Download")
        return userAge

    }

    fun myAsync(){
        //async

        var userName:String?=null
        var userAge:Int?=null
        runBlocking {
            val downloadName = async {
                downName()
            }

            val downloadAge = async {
                downAge()
            }

            userName = downloadName.await()
            userAge = downloadAge.await()

            println("${userName} ${userAge}")
        }
    }

    private fun myJob(){

        runBlocking {
            val myName = launch {
                delay(2000)
                println("job")
                val secondName = launch {
                    println("job second")
                }
            }

            myName.invokeOnCompletion {
                println("my job end")
            }

           myName.cancel()
        }


    }

    fun withContextCorotuines(){

        runBlocking {
            launch(Dispatchers.IO) {
                println("Context: ${coroutineContext}")
                withContext(Dispatchers.Main){
                    println("Context: ${coroutineContext}")
                }
            }
        }

    }
}