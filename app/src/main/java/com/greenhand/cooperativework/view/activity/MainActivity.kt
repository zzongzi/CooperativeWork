package com.greenhand.cooperativework.view.activity

import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.greenhand.cooperativework.R
import com.greenhand.cooperativework.base.BaseOnlyBindingActivity
import com.greenhand.cooperativework.databinding.ActivityMainBinding
import com.greenhand.cooperativework.view.fragment.community.CommunityFragment
import com.greenhand.cooperativework.view.fragment.index.IndexFragment
import com.greenhand.cooperativework.view.fragment.notice.NoticeFragment

class MainActivity : BaseOnlyBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {
        initNavigation()
    }

    private fun initNavigation() {
        val fragments = ArrayList<Fragment>(3)
        fragments.add(IndexFragment())
        fragments.add(CommunityFragment())
        fragments.add(NoticeFragment())

        val navView = mBinding.mainNavView
        val slideShow = mBinding.mainSlideShow
        slideShow
            .setOffscreenPageLimit(1)
            .setOpenNestedScroll(true)
            .setPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    navView.menu.getItem(position).isChecked = true
                }
            })
            .setAdapter(this, fragments)

        navView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.indexFragment -> {
                    slideShow.setCurrentItem(0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.communityFragment -> {
                    slideShow.setCurrentItem(1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.noticeFragment -> {
                    slideShow.setCurrentItem(2)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    /**
     * 用来连点两下退出应用
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                mExitTime = System.currentTimeMillis()
            }else {
                finish()
            }
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
    private var mExitTime: Long = 0
}