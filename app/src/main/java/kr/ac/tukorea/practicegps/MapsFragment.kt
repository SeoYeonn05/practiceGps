package kr.ac.tukorea.practicegps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class MapsFragment : Fragment(), OnMapReadyCallback {   //  OnMapReadyCallback 상속
    private lateinit var mView: MapView
    lateinit var name:String
    lateinit var latLng: LatLng


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mapView = inflater.inflate(R.layout.fragment_maps, null)

        //  try catch로 맵이 null이 아닌지 확인 필요

        mView=mapView.findViewById(R.id.map)
        mView.onCreate(savedInstanceState)
        mView.getMapAsync { this } // 구글맵을 불러오는 함수, this->MapsFragment->OnMapReadyCallback->onMapready()로 들어감
        return mapView
    }

    override fun onMapReady(googleMap: GoogleMap) {  // onMapReady override, map에 위치 연동
        latLng= LatLng(37.3156, 126.804)
        val location: LatLng =LatLng(latLng.latitude, latLng.longitude ) // 기본 위치 시흥 지정

        googleMap.mapType=GoogleMap.MAP_TYPE_NORMAL //  지도의 종류 설정

        // 마커 출력 (자신의 위치: circle, 스팟의 위치: marker로 표시 필요)
//        var markerCircle: CircleOptions = CircleOptions()   // circle 생성
//                                            .center(location) //  circle의 중심

        googleMap.addMarker(MarkerOptions()
            .position(location)
            .title("first location"))    //  지도에 Marker 표시
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f)) // 좌표변수와 줌의 정도 지정
    }
}