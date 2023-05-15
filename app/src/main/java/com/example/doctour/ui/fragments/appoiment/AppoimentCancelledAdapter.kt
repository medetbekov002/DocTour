package com.example.doctour.ui.fragments.appoiment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AppoimentCancelledAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = AppoimentCancelledObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt("key", position + 1)
        }
        return fragment
    }
}
