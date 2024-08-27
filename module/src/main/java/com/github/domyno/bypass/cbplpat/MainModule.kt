package com.github.domyno.bypass.cbplpat

import android.content.Intent
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam?.packageName == "com.konylabs.cbplpat") {
            XposedBridge.log("Loaded app :: " + lpparam.packageName)
            runHooks(lpparam)
        }
    }

    private fun runHooks(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedHelpers.findAndHookMethod(
            "com.citi.mobile.pt3.starter.appdome.AppDomeEvenReceiver",
            lpparam.classLoader,
            "a",
            Intent::class.java,
            XC_MethodReplacement.DO_NOTHING
        )
    }
}