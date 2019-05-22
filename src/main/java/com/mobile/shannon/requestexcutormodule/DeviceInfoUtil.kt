package com.mobile.shannon.requestexcutormodule

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import com.google.gson.Gson

object DeviceInfoUtil {
    @SuppressLint("HardwareIds", "MissingPermission")
    fun getPhoneStatus(application: Application?): String {
        if (application == null) return Gson().toJson(DeviceInfo())
        try {
            val tm =
                application?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return Gson().toJson(
                DeviceInfo(
                    tm.deviceId,
                    tm.deviceSoftwareVersion,
                    tm.line1Number,
                    tm.networkCountryIso,
                    tm.networkOperator,
                    tm.networkType.toString(),
                    tm.phoneType.toString(),
                    tm.simCountryIso,
                    tm.simOperator,
                    tm.simSerialNumber,
                    tm.simState.toString(),
                    tm.subscriberId,
                    tm.voiceMailNumber
                )
            )
        } catch (e: Throwable) {
            return Gson().toJson(DeviceInfo())
        }
    }
}