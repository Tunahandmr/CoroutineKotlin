package com.tunahan.coroutineexception

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exception:${throwable.localizedMessage}")
        }

        lifecycleScope.launch(handler) {

            throw Exception("error")
        }



        lifecycleScope.launch(handler) {
            supervisorScope {
                launch {
                    throw Exception("error")
                }

                launch {
                    delay(500)
                    println("println")
                }
            }

        }

        CoroutineScope(Dispatchers.Main+handler).launch {
            launch {
                throw Exception("error corotuine scope")
            }
        }
    }
}