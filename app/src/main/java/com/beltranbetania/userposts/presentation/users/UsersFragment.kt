package com.beltranbetania.userposts.presentation.users

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beltranbetania.userposts.databinding.FragmentUsersBinding
import com.beltranbetania.userposts.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.onItemClickListener, TextWatcher {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    var mAdapter : UsersAdapter = UsersAdapter (this)
    private val postViewModel: UsersViewModel by viewModels()
    var users: List<User> = ArrayList()


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
        binding.searchET.addTextChangedListener(this);
        postViewModel.userModel.observe(viewLifecycleOwner, Observer {
            users=it
            mAdapter.setUserList(users)
        })
        postViewModel.isLoading.observe(viewLifecycleOwner, Observer {
             binding.swipeContainer.isRefreshing = it
            binding.searchET.text.clear()
        })

        postViewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            setMessageVisivility(it)
        })

        postViewModel.loadUsers()

        binding.swipeContainer.setOnRefreshListener { postViewModel.loadUsers()}
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

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        var searchText: String = p0.toString()
        filterUsers(searchText)
    }

    private fun filterUsers(string: String ) {
        val usersFiltered: MutableList<User> = ArrayList()
        for (user in users) {
            if (user.name.lowercase().contains(string.lowercase())) {
                usersFiltered.add(user)
            }
        }
        setMessageVisivility(usersFiltered.isEmpty())
        mAdapter.setUserList(usersFiltered)
    }

    private fun setMessageVisivility(flag:Boolean){
        binding.swipeTv.visibility  =  when(flag) {
            true -> TextView.VISIBLE
            false -> TextView.INVISIBLE
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

}