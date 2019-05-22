package com.mobile.shannon.requestexcutormodule

data class DeviceInfo(
    var IMEI: String? = "", //imei
    var DSV: String? = "", //deviceSoftwareVersion
    var L1N: String? = "", //line1Number
    var NCI: String? = "", //networkCountryIso
    var NO: String? = "", //networkOperator
    var NT: String? = "", //networkType
    var PT: String? = "", //phoneType
    var SCI: String? = "", //simCountryIso
    var SO: String? = "", //simOperator
    var SSN: String? = "", //simSerialNumber
    var SS: String? = "", //simState
    var SI: String? = "", //subscriberId
    var VMN: String? = "" //voiceMailNumber
)