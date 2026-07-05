package com.vedtop.player.shorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.vedtop.player.R

/**
 * شاشة "شورتس": تشغيل فيديوهات عمودية قصيرة بالتمرير للأعلى/الأسفل،
 * تعمل على ملفات محلية أو روابط يوفرها المستخدم (وليس محتوى مستخرجًا من منصات أخرى).
 */
class ShortsFragment : Fragment(R.layout.fragment_shorts) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.shortsViewPager)
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // مثال: قائمة فارغة يملؤها المستخدم بفيديوهاته الخاصة أو روابطه
        val shorts = loadUserShorts()
        viewPager.adapter = ShortsAdapter(shorts)
    }

    private fun loadUserShorts(): List<ShortVideo> {
        // TODO: اربطها لاحقًا بمصدر بيانات حقيقي (ملفات الجهاز أو قاعدة بيانات خاصة بالمستخدم)
        return emptyList()
    }
}
