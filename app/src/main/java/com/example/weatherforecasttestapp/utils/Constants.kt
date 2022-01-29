package com.example.weatherforecasttestapp.utils

object Constants {
    //To receive an Api key visit https://www.weatherapi.com/
    const val apiKey = ""
    //codes received from api correspond to images
    val codesToImagesMap = mapOf(
        "1000" to "113",
        "1003" to "116",
        "1006" to "119",
        "1009" to "122",
        "1030" to "143",
        "1063" to "176",
        "1066" to "179",
        "1069" to "182",
        "1072" to "185",
        "1087" to "200",
        "1114" to "227",
        "1117" to "230",
        "1135" to "248",
        "1147" to "260",
        "1150" to "263",
        "1153" to "266",
        "1168" to "281",
        "1171" to "284",
        "1180" to "293",
        "1183" to "296",
        "1186" to "299",
        "1189" to "302",
        "1192" to "305",
        "1195" to "308",
        "1198" to "311",
        "1201" to "314",
        "1204" to "317",
        "1207" to "320",
        "1210" to "323",
        "1213" to "326",
        "1216" to "329",
        "1219" to "332",
        "1222" to "335",
        "1225" to "338",
        "1237" to "350",
        "1240" to "353",
        "1243" to "356",
        "1246" to "359",
        "1249" to "362",
        "1252" to "365",
        "1255" to "368",
        "1258" to "371",
        "1261" to "374",
        "1264" to "377",
        "1273" to "386",
        "1276" to "389",
        "1279" to "392",
        "1282" to "395",
    )

    val monthsTextFormat = mapOf(
        "01" to "January",
        "02" to "February",
        "03" to "March",
        "04" to "April",
        "05" to "May",
        "06" to "June",
        "07" to "July",
        "08" to "August",
        "09" to "September",
        "10" to "October",
        "11" to "November",
        "12" to "December"
    )

    val monthsTextFormatShort = mapOf(
        "01" to "Jan,",
        "02" to "Feb,",
        "03" to "Mar,",
        "04" to "Apr,",
        "05" to "May,",
        "06" to "Jun,",
        "07" to "Jul,",
        "08" to "Aug,",
        "09" to "Sep,",
        "10" to "Oct,",
        "11" to "Nov,",
        "12" to "Dec,"
    )

    val numberOfDaysForForecast = "10"
}
