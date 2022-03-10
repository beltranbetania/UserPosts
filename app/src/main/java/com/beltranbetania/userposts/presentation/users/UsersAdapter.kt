package com.beltranbetania.userposts.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beltranbetania.userposts.databinding.ItemUserBinding
import com.beltranbetania.userposts.domain.model.User

class UsersAdapter(val listener: onItemClickListener) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var data = mutableListOf<User>()
    fun setUserList(movies: List<User>) {
        this.data = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item: User = data[position]
        holder.bind(item,   listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder (val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(item: User, listener: onItemClickListener){
            //binding.UserTitleTV.text=item.title
            binding.root.setOnClickListener{
                listener.itemClick(item)

            }
        }
    }

    interface onItemClickListener {
        fun itemClick(User: User?)
    }

    companion object{

    }

}