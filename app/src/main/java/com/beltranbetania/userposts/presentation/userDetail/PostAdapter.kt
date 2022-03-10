package com.beltranbetania.userposts.presentation.userDetail
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beltranbetania.userposts.databinding.ItemPostBinding
import com.beltranbetania.userposts.domain.model.Post

class PostAdapter() :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var data = mutableListOf<Post>()
    fun setPostList(movies: List<Post>) {
        this.data = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item:Post= data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder (val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(item:Post){
            binding.postTitleTV.text=item.title

        }
    }


    companion object{

    }

}