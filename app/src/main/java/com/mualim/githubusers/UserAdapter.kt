package com.mualim.githubusers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter internal constructor(private val context: Context): BaseAdapter() {
    internal var users = arrayListOf<User>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val user = getItem(position) as User
        viewHolder.bind(user)
        return itemView
    }

    override fun getItem(position: Int): Any {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return users.size
    }

    private inner class ViewHolder internal constructor(private val view: View){
        private val imgUser: CircleImageView = view.findViewById(R.id.img_user)
        private val txtUserName: TextView = view.findViewById(R.id.txt_user_name)
        private val txtName: TextView = view.findViewById(R.id.txt_name)
        private val txtRepository: TextView = view.findViewById(R.id.txt_repository)
        private val txtLocation: TextView = view.findViewById(R.id.txt_location)

        internal fun bind(user: User){
            imgUser.setImageResource(user.avatar!!)
            txtUserName.text = user.userName
            txtName.text = user.name
            txtRepository.text = user.repository.toString().trim()
            txtLocation.text = user.location
        }
    }
}