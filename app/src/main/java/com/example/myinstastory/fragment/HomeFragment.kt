package com.example.myinstastory.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstastory.R
import com.google.gson.Gson


import com.example.myinstastory.adapter.StatusAdapter
import com.example.myinstastory.data.InstaStatus
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    var imagesList: List<String>? = null
    var storyIdsList: List<String>? = null
    var counter = 0
    val statusList = ArrayList<InstaStatus>()

    //    var instaStausList = RecyclerView<>
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, parent, false)

        val activity = activity as Context

        val instaStausList = view.findViewById<RecyclerView>(R.id.insta_status_list)
//        val postViewList = view.findViewById<RecyclerView>(R.id.post_list)

        instaStausList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        postViewList.layoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val statusJSON: String =
            activity.assets.open("status.json").bufferedReader().use { it.readText() }
//        val postJSON: String =
//            activity.assets.open("post.json").bufferedReader().use { it.readText() }

        val status = Gson().fromJson(statusJSON, Array<InstaStatus>::class.java)
//        val post = Gson().fromJson(postJSON, Array<Post>::class.java)

//        val statusList = ArrayList<InstaStatus>()
//        val postList = ArrayList<Post>()

//        for (i in 0 until status.size)
//            statusList.add(InstaStatus(status[i].id, status[i].name, status[i].imageUrl))

//        for (j in 0 until post.size)
//            postList.add(
//                Post(
//                    post[j].id,
//                    post[j].name,
//                    post[j].logo,
//                    post[j].photo,
//                    post[j].likes,
//                    post[j].description
//                )
//            )




//        val postAdapter = PostAdapter(activity, postList)
//        postViewList.adapter = postAdapter

        FirebaseApp.initializeApp(activity)

//        val redef = FirebaseDatabase.getInstance().reference
//        Log.e("TAG_firebase_redef", "getStories: $redef" )

        getStories(instaStausList)
        return view
    }

    private fun getStories(instaStausList: RecyclerView) {

//        imagesList = ArrayList()
//        storyIdsList = ArrayList()

        val ref = FirebaseDatabase.getInstance().reference.child("Story")
            .child("OZyYudLKnoVYY2ecb8HowqlHEUD3")
        val refStories = FirebaseDatabase.getInstance().reference.child("Stories")
            .child("OZyYudLKnoVYY2ecb8HowqlHEUD3")
//        Log.e("TAG_firebase", "getStories: $ref" )
        Log.e("TAG_firebase", "getStories: $refStories")
        refStories.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                Log.e("TAG", "onDataChange: $datasnapshot")
                statusList.clear()
//                (storyIdsList as ArrayList<String>).clear()

                for (snapshot in datasnapshot.children) {
                    Log.e("TAG", "onDataChange:snapshot $snapshot")
                    val story = snapshot.getValue(InstaStatus::class.java)
                    Log.e("TAG", "onDataChange:story $story")
                    statusList.add(story!!)
//                    val timeCurrent = System.currentTimeMillis()


//                    snapshot to InstaStatus::class
//                    if (timeCurrent > story!!.getTimeStart() && timeCurrent < story.getTimeEnd()) {
//                        (imagesList as ArrayList<String>).add(story.getImageUrl())
////                        (storyIdsList as ArrayList<String>).add(story.getStoryId())
//                    }

                }
               statusList.sortByDescending { statusList.size }
                val statusAdapter = StatusAdapter(activity!!, statusList)
                 instaStausList.adapter = statusAdapter
//                statusList.add(InstaStatus(status[i].id, status[i].name, status[i].picture))
                Log.e("TAG", "onDataChange:imagesList ${statusList.size}")
//                Log.e("TAG", "onDataChange:storyIdsList ${storyIdsList?.size}" )
//                storiesProgressView!!.setStoriesCount((imagesList as ArrayList<String>).size)
//                storiesProgressView!!.setStoryDuration(7000L)
//                storiesProgressView!!.setStoriesListener(this@HomeFragment)
//                storiesProgressView!!.startStories(counter)
//                Picasso.get().load(imagesList!![counter]).placeholder(R.drawable.progress_animation)
//                    .into(image_story)


            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "onCancelled: $error")
            }
        })


    }
}