package kr.ac.tukorea.practicegps

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng

class ReadLocationXY(private val context: Context) {
    fun read(): List<LocationXY> {
        return listOf(
            LocationXY("정왕 자전거 대여소", LatLng(37.343991285297, 126.74729588817)),
            LocationXY("월곶 자전거 대여소", LatLng(37.3917953, 126.742692))
        )
    }
}