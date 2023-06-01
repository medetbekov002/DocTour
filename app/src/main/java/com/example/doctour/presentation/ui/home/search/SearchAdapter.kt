package com.example.doctour.presentation.ui.home.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctour.data.core.extension.glide
import com.example.doctour.data.model.BermetModel
import com.example.doctour.databinding.ItemFoundVecanciesBinding

class SearchAdapter (private var userList: ArrayList<BermetModel>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    private var list :ArrayList<BermetModel> =ArrayList()

    fun filterList(filteredNames: ArrayList<BermetModel>) {
        this.userList = filteredNames
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemFoundVecanciesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class SearchViewHolder(private var binding:ItemFoundVecanciesBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(bermetModel: BermetModel) {
            binding.image.glide(bermetModel.image)
            binding.name.text = bermetModel.name
            binding.tvExperience.text = bermetModel.experience
            binding.profession.text=bermetModel.profession
            binding.tvClinic.text=bermetModel.clinic
            binding.rating.text =bermetModel.rating
            binding.recommendation.text =bermetModel.recomendation
            binding.tvPrice.text=bermetModel.price
        }

    }

}