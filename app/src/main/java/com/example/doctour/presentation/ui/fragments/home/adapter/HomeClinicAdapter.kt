package com.example.doctour.presentation.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.doctour.databinding.ItemClinicBinding
import com.example.doctour.presentation.extensions.loadImage
import com.example.doctour.presentation.model.ClinicUI

class HomeClinicAdapter(
    private val onCLick: () -> Unit
) : PagingDataAdapter<ClinicUI, HomeClinicAdapter.ViewHolderHomeClinic>(
    DFUtilHomeClinicUI()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHomeClinic {
        return ViewHolderHomeClinic(
            ItemClinicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderHomeClinic, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
    inner class ViewHolderHomeClinic(private val binding: ItemClinicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clinics: ClinicUI) {
            clinics.photo?.let { binding.imgClinicAvatar.loadImage(it) }
            binding.tvClinicName.text=clinics.title

            itemView.setOnClickListener {
                onCLick()
            }
        }

    }
}
private class DFUtilHomeClinicUI : DiffUtil.ItemCallback<ClinicUI>() {
    override fun areItemsTheSame(oldItem: ClinicUI, newItem: ClinicUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ClinicUI, newItem: ClinicUI): Boolean {
        return oldItem == newItem
    }
}