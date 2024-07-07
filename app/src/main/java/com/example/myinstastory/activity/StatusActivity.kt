package com.example.myinstastory.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstastory.R
import com.example.myinstastory.adapter.StatusAdapter
import com.example.myinstastory.data.InstaStatus
import com.example.myinstastory.utils.Stories
import com.example.myinstastory.utils.data.StoryItem
import com.example.myinstastory.utils.listener.StoriesCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StatusActivity : AppCompatActivity(), StoriesCallback {

    val statusList = ArrayList<StoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_status)
        supportActionBar?.hide()

        getStories()
    }

    private fun getStories() {

        val refStories = FirebaseDatabase.getInstance().reference.child("Stories")
            .child("OZyYudLKnoVYY2ecb8HowqlHEUD3")

        refStories.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                statusList.clear()

                for (snapshot in datasnapshot.children) {
                    val story = snapshot.getValue(StoryItem::class.java)
                    statusList.add(story!!)

                }
                statusList.sortByDescending { statusList.size }

                val storiesView = findViewById<Stories>(R.id.stories)
                storiesView.setStoriesList(statusList)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "onCancelled: $error")
            }
        })


    }

    override fun onStoriesEnd() {
//        TODO("Not yet implemented")
        finish()
    }
}