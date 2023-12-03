package com.example.foofa_app

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class LocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = com.google.android.gms.location.LocationRequest.create()?.apply {
            interval = 1000
            priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {Log.d("locset_success", "location client setting success!")}
        task.addOnFailureListener { Log.d("locset_fail", "location client setting failure")}



        val btnGetLocation: Button = findViewById(R.id.btnGetLocation)

        /*tedpermission으로 사용자 위치 권한 확인*/
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                Toast.makeText(applicationContext, "권한이 허용됨", Toast.LENGTH_SHORT).show()
                btnGetLocation.setOnClickListener {
                    requestLocation()
                }
            }
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(applicationContext, "권한이 거부됨", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setRationaleMessage("위치 사용 권한을 허용해주세요! :D")
            .setDeniedMessage("위치 사용 권한을 거부하셨습니다. 어플 사용을 위해 위치 사용 권한이 필요합니다!")
            .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            .check()
    }

    private fun requestLocation() {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        Toast.makeText(this, "위도 : $latitude, 경도 : $longitude", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "위치정보가 올바르지 않습니다(null).",Toast.LENGTH_SHORT).show()
                        Log.d("loc", "$location")
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "위치정보 로딩실패 : $e",Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(this, "위치 사용 권한이 필요합니다!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
    }
}