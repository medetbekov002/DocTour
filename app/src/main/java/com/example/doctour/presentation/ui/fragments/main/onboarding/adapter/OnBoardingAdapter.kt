package com.example.doctour.presentation.ui.fragments.main.onboarding.adapterimport android.view.LayoutInflaterimport android.view.ViewGroupimport androidx.recyclerview.widget.RecyclerView.Adapterimport androidx.recyclerview.widget.RecyclerView.ViewHolderimport com.example.doctour.Rimport com.example.doctour.databinding.ItemOnboardingBindingimport com.example.doctour.presentation.extensions.loadImageimport com.example.doctour.presentation.ui.fragments.main.onboarding.model.OnBoardclass OnBoardingAdapter : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {    private val data = arrayListOf(        OnBoard(            "Найдите себе лучших врачей в вашем городе",            "С помощью нашего мобильного приложение, Вы можете с легкостью найти лучших врачей",            R.drawable.istock_1,            false        ),        OnBoard(            "Записывайтесь на прием \n" +                    "к опытным врачам",            "Найдите опытных врачей специалистов и запишитесь на прием\n",            R.drawable.happy_doctors1,            false        ),        OnBoard(            "Найдите себе лучших врачей в вашем городе",            "С помощью нашего мобильного приложение, Вы можете с легкостью найти лучших врачей",            R.drawable.nurse1        )    )    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {        return OnBoardingViewHolder(            ItemOnboardingBinding.inflate(                LayoutInflater.from(parent.context),                parent,                false            )        )    }    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {        holder.bind(data[position])    }    override fun getItemCount(): Int {        return data.size    }    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :        ViewHolder(binding.root) {        fun bind(onBoard: OnBoard) {            binding.text1.text = onBoard.title            binding.text2.text = onBoard.desc            binding.imageView.loadImage(onBoard.image.toString())        }    }}