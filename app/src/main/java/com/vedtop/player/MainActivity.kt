package com.vedtop.player

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vedtop.player.databinding.ActivityMainBinding
import com.vedtop.player.service.PlaybackService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        setupPlayerControls()
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

        // تبويبات التطبيق: الرئيسية - تصفح - المفضلة - المكتبة - الإعدادات
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_browse,
                R.id.navigation_favorites,
                R.id.navigation_library,
                R.id.navigation_settings
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupPlayerControls() {
        // زر تصغير الفيديو (Picture in Picture)
        binding.btnPip.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                enterPictureInPictureMode()
            } else {
                Toast.makeText(this, "هذه الميزة غير مدعومة على إصدارك", Toast.LENGTH_SHORT).show()
            }
        }

        // زر تشغيل الصوت في الخلفية
        binding.btnBackground.setOnClickListener {
            PlaybackService.start(this)
            Toast.makeText(this, "جارٍ التشغيل في الخلفية", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_theme -> {
                showThemeDialog()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showThemeDialog() {
        val themeManager = com.vedtop.player.theme.ThemeManager(this)
        val themeNames = arrayOf("افتراضي", "محيطي", "غروب", "غابة")
        val themeKeys = arrayOf(
            com.vedtop.player.theme.ThemeManager.THEME_DEFAULT,
            com.vedtop.player.theme.ThemeManager.THEME_OCEAN,
            com.vedtop.player.theme.ThemeManager.THEME_SUNSET,
            com.vedtop.player.theme.ThemeManager.THEME_FOREST
        )

        com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
            .setTitle("اختر مظهر التطبيق")
            .setItems(themeNames) { _, which ->
                themeManager.setTheme(themeKeys[which])
                Toast.makeText(this, "تم تفعيل المظهر: ${themeNames[which]}", Toast.LENGTH_SHORT).show()
                recreate()
            }
            .show()
    }

    private fun showAboutDialog() {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
            .setTitle("حول Vedtop")
            .setMessage("مشغل فيديو شخصي لعرض ملفاتك ومحتواك المفضل.")
            .setPositiveButton("حسناً", null)
            .show()
    }
}
