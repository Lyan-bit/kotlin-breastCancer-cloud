package com.example.breastcancer

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TABTITLES = arrayOf("+BreastCancer", "ListBreastCancer", "ClassifyBreastCancer")
    }

    override fun getItem(position: Int): Fragment {
        // instantiate a fragment for the page.
            return when (position) {
                0 -> { 
                    CreateBreastCancerFragment.newInstance(mContext) 
                }            1 -> { 
                    ListBreastCancerFragment.newInstance(mContext) 
                }            2 -> { 
                    ClassifyBreastCancerFragment.newInstance(mContext) 
                }
                else -> CreateBreastCancerFragment.newInstance(mContext) 
             }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TABTITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}
