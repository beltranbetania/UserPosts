package com.beltranbetania.userposts.presentation.userDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.beltranbetania.userposts.R
import com.beltranbetania.userposts.databinding.FragmentUserDetailBinding
import com.google.android.material.snackbar.Snackbar

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val userDetailViewModel: UserDetailViewModel by viewModels()
    var mAdapter : PostAdapter = PostAdapter ()
    val args: UserDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var  userId=args.user
        binding.itemsContainerRV.setHasFixedSize(true)
        binding.itemsContainerRV.layoutManager = LinearLayoutManager(activity)
        binding.itemsContainerRV.adapter = mAdapter

        userDetailViewModel.userModel.observe(viewLifecycleOwner, Observer {
            binding.nameTV.text=it.name
            binding.emailTV.text=it.email
            binding.phoneTV.text=it.phone

        })

        userDetailViewModel.postModel.observe(viewLifecycleOwner, Observer {
            mAdapter.setPostList(it)
        })

        userDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.swipeContainer.isRefreshing = it
        })

        binding.swipeContainer.setOnRefreshListener {
            userDetailViewModel.getDetailPost(userId!!)
        }
        userDetailViewModel.getDetailPost(userId!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}