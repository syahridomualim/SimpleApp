package com.mualim.githubusers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_PROFILE = "extra_profile"
        const val STATE_RESULT = "state_result"
    }

    private lateinit var imgProfile: CircleImageView
    private lateinit var tvUserName: TextView
    private lateinit var tvName: TextView
    private lateinit var tvFollowers: TextView
    private lateinit var tvFollowing: TextView
    private lateinit var tvRepository: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvCompany: TextView
    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imgProfile = findViewById(R.id.img_profile)
        tvUserName = findViewById(R.id.tv_user_name)
        tvName = findViewById(R.id.tv_name)
        tvFollowers = findViewById(R.id.tv_followers)
        tvFollowing = findViewById(R.id.tv_following)
        tvRepository = findViewById(R.id.tv_repository)
        tvLocation = findViewById(R.id.tv_location)
        tvCompany = findViewById(R.id.tv_company)
        btnShare = findViewById(R.id.btn_share)

        val profile = intent.getParcelableExtra(EXTRA_PROFILE) as User

        imgProfile.setImageResource(profile.avatar!!)
        tvUserName.text = profile.userName
        tvName.text = profile.name
        tvFollowers.text = profile.followers.toString().trim()
        tvFollowing.text = profile.following.toString().trim()
        tvRepository.text = profile.repository.toString().trim()
        tvLocation.text = profile.location
        tvCompany.text = profile.company

        btnShare.setOnClickListener {
            openWeb(profile.githubLink)
        }

        if (savedInstanceState != null){
            val company = savedInstanceState.getString(STATE_RESULT)
            tvCompany.text = company
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvCompany.text.toString())
    }

    private fun openWeb(url: String){
        val webPage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        startActivity(intent)
    }
}