package kr.ac.tukorea.practicegps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kr.ac.tukorea.practicegps.MapsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient // 위치값 사용
    //FusedLocationProviderClient API 사용시 GPS 신호 및 와이파이와 통신사 네트워크 위치를 결합해 최소한의 배터리 사용량으로 빠르고 정확한 위치 검색
    private lateinit var locationCallback: LocationCallback //  위치값 요청에 대한 갱신 정보를 받아옴

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentMap: MapsFragment
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

//    private fun setFragment(){
//        fragmentManager=supportFragmentManager
//        transaction=fragmentManager.beginTransaction()
//
//        transaction.replace(R.id.main_view, fragmentMap).commit()
//
//    }


}