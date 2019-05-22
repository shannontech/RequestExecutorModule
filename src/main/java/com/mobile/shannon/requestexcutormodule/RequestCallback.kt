package com.mobile.shannon.requestexcutormodule

interface RequestCallback {
    fun onResponse(vararg args: Any?)
    fun onFailure(vararg args: Any?)
}