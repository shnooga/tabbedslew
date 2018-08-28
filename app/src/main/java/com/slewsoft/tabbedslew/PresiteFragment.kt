package com.slewsoft.tabbedslew

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PresiteFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var mView: View? = null

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        val linkedIn = LatLng(37.4233438, -122.0728817)
        mMap.addMarker(MarkerOptions()
                .position(linkedIn)
                .title("Linked In"))
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        val faceBook= LatLng(37.4629101,-122.2449094)
        mMap.addMarker(MarkerOptions()
                .position(faceBook)
                .title("Facebook")
                .snippet("Facebook HQ Menlo Park")
                .draggable(true))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.4233438, -122.0728817), 10f))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_presite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager!!.findFragmentById(R.id.map1) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}
