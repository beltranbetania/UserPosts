package com.beltranbetania.userposts.presentation.users
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beltranbetania.userposts.databinding.FragmentUsersBinding

import com.beltranbetania.userposts.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.onItemClickListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    var mAdapter : UsersAdapter = UsersAdapter (this)
    private val postViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemsContainerRV.setHasFixedSize(true)
        binding.itemsContainerRV.layoutManager = LinearLayoutManager(activity)
        binding.itemsContainerRV.adapter = mAdapter

        postViewModel.userModel.observe(viewLifecycleOwner, Observer {
            mAdapter.setUserList(it)
        })
        postViewModel.isLoading.observe(viewLifecycleOwner, Observer {
             binding.swipeContainer.isRefreshing = it
        })

        postViewModel.loadPosts()

        binding.swipeContainer.setOnRefreshListener { postViewModel.loadPosts()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClick(user: User?) {
        val action = UsersFragmentDirections.actionPostsFragmentToPostDetailFragment(user!!.id)
        NavHostFragment.findNavController(this)
            .navigate(action)

    }

}