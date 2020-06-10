package cn.trans88.kurotool.util

object DistanceUtil {
    val EARTH_RADIUS = 6378.137

    private fun rad(d: Double): Double {
        return d * Math.PI / 180.0
    }

    /**
     * 计算两个经纬度之间的距离，单位米
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    open fun getDistance(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Double {
        val radLat1 = rad(lat1)
        val radLat2 = rad(lat2)
        val a = radLat1 - radLat2
        val b = rad(lng1) - rad(lng2)
        var s = 2 * Math.asin(
            Math.sqrt(
                Math.pow(Math.sin(a / 2), 2.0)
                        + (Math.cos(radLat1) * Math.cos(radLat2)
                        * Math.pow(Math.sin(b / 2), 2.0))
            )
        )
        s = s * EARTH_RADIUS
        s = Math.round(s * 10000.0) / 10000.0
        s = s * 1000
        return s
    }

    /**
     * 判断是否在半径内
     */
    fun inRadius(
        fenceLatitude: Double,
        fenceLongitude: Double,
        curLatitude: Double,
        curLongitude: Double,
        radius: Int
    ): Boolean {
        return getDistance(fenceLatitude, fenceLongitude, curLatitude, curLongitude) >= radius
    }
}