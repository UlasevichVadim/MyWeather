package com.vadimulasevich.myweather.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

class PermissionChecker(private val applicationContext: Context) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasWriteExternalStoragePermission() =
        applicationContext.checkSelfPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
}