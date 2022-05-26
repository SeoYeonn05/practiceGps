package kr.ac.tukorea.practicegps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource


class MainActivity : AppCompatActivity(), com.naver.maps.map.OnMapReadyCallback{

    private lateinit var fusedLocationClient:FusedLocationProviderClient
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setFrag()
    }

    private fun setFrag() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
            ?:MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(naverMap: NaverMap){

        this.naverMap = naverMap
        val uiSettings = naverMap.uiSettings
        // 맵 첫 시작 카메라 위치
        val latLng =com.naver.maps.geometry.LatLng(37.349741467772, 126.76182486561)

        // 맵 타입
        naverMap.mapType = NaverMap.MapType.Navi
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)

        // 맵 시작 위치와 줌 설정
        val cameraUpdate = CameraUpdate.scrollAndZoomTo(latLng, 11.0)
            .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)

        //대여소 위치 추가
        addMarkers(naverMap)

        // 내 위치 받기
        uiSettings.isLocationButtonEnabled = true

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            this.naverMap = naverMap

            locationSource = FusedLocationSource(this ,
                LOCATION_PERMISSION_REQUEST_CODE
            )
            naverMap.locationSource = locationSource
            naverMap.locationTrackingMode = LocationTrackingMode.Face
        } else{
            Toast.makeText(this, "권한을 설정하세요", Toast.LENGTH_SHORT).show()
        }
    }

    private val places: List<LocationXY> by lazy{
        ReadLocationXY(this).read()
    }

    private fun addMarkers(naverMap: NaverMap){
        places.forEach{ place ->
            var marker = Marker()
            marker.position = com.naver.maps.geometry.LatLng(
                place.location.latitude,
                place.location.longitude)
            //marker.icon = OverlayImage.fromResource(R.drawable.))
            marker.map=naverMap

        }
    }
    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}