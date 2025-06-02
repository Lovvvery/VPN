package com.example.apitestapps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.VpnService
import de.blinkt.openvpn.VpnProfile
import de.blinkt.openvpn.core.ProfileManager
import de.blinkt.openvpn.core.VpnStatus


// Загрузка конфигурации из assets/myvpn.ovpn
fun loadOvpnConfig(context: Context): String =
    context.assets.open("myvpn.ovpn").bufferedReader().use { it.readText() }

// Запрос разрешения на запуск VPN
fun prepareVpn(activity: Activity, onReady: () -> Unit) {
    val intent = VpnService.prepare(activity)
    if (intent != null) {
        activity.startActivityForResult(intent, 1337)
    } else {
        onReady()
    }
}

// Подключение VPN
fun connectVpn(context: Context) {
    val config = loadOvpnConfig(context)

    // Получаем менеджер профилей
    val pm = ProfileManager.getInstance(context)

    // Загружаем конфигурацию в профиль
    val profile = VpnProfile("myvpn")
    profile.mName = "MyVPN"
    profile.loadConfig(config, false)

    // Добавляем профиль в менеджер
    pm.addProfile(profile)
    pm.saveProfile(context, profile)

    // Устанавливаем этот профиль как активный
    pm.setConnectedVPNProfile(profile)

    // Запускаем VPN сервис
    profile.startVPN(context)
}

// Отключение VPN
fun disconnectVpn() {
    VpnStatus.stopVPN()
}