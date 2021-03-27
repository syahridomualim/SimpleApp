package com.mualim.githubusers

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var listView: ListView
    private lateinit var dataUserName: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataRepository: TypedArray
    private lateinit var dataLocation: Array<String>
    private lateinit var dataImage: TypedArray
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollowers: TypedArray
    private lateinit var dataFollowing: TypedArray
    private lateinit var dataGithubLink: Array<String>
    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv_users)
        adapter = UserAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_PROFILE, users[position])
            startActivity(intent)
        }
    }

    private fun prepare() {
        dataUserName = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataRepository = resources.obtainTypedArray(R.array.repository)
        dataLocation = resources.getStringArray(R.array.location)
        dataImage = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.obtainTypedArray(R.array.followers)
        dataFollowing = resources.obtainTypedArray(R.array.following)
        dataGithubLink = resources.getStringArray(R.array.links)
    }

    private fun addItem() {
        for (position in dataUserName.indices){
            val user = User(
                dataUserName[position],
                dataName[position],
                dataLocation[position],
                dataRepository.getIndex(position),
                dataImage.getResourceId(position, -1),
                dataCompany[position],
                dataFollowers.getInt(position, 1),
                dataFollowing.getInt(position, 1),
                dataGithubLink[position]
            )
            users.add(user)
        }
        adapter.users = users
    }
}