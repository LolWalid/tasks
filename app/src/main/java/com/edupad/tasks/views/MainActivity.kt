package com.edupad.tasks.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.edupad.tasks.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(MainActivity@this, MainActivity::class.java)
            intent.putExtra("test", "1")
            startActivity(intent)
        }
    }
}
