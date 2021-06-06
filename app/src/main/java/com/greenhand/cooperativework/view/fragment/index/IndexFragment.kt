package com.greenhand.cooperativework.view.fragment.index

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.greenhand.cooperativework.R
import com.greenhand.cooperativework.base.BaseOnlyBindingFragment
import com.greenhand.cooperativework.databinding.FragmentIndexBinding
import com.ndhzs.slideshow.viewpager2.transformer.RotateYTransformer

class IndexFragment : BaseOnlyBindingFragment<FragmentIndexBinding>(R.layout.fragment_index) {

    private var mFragments = ArrayList<Fragment>()

    override fun init() {
        mFragments.add(IndexDiscoverFragment())
        mFragments.add(IndexRecommendFragment())
        mFragments.add(IndexDailyFragment())

        val tabs = listOf("发现", "推荐", "日报")

        val tabLayout = mBinding.tlIndex
        val slideShow = mBinding.shIndex

        slideShow
            .setAdapter(mFragments, requireActivity())
            .setTransformer(RotateYTransformer(10F))
            .setOffscreenPageLimit(1)
            .setOpenNestedScroll(true)

        TabLayoutMediator(
            tabLayout, slideShow.getViewPager2()
        ) { tab, position -> tab.text = tabs[position] }.attach()
    }
}