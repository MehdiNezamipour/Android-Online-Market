package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.FragmentMapBinding;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapBinding mBinding;
    private GoogleMap mMap;
    private LatLng mLatLng;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);

        mBinding.mapView.onCreate(savedInstanceState);
        mBinding.mapView.getMapAsync(this);

        return mBinding.getRoot();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        Toast.makeText(getContext(), getResources().getString(R.string.long_click_for_add_map_marker), Toast.LENGTH_LONG).show();

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(35.6, 51.3), 10);
        mMap.animateCamera(cameraUpdate);

        mMap.setOnMapLongClickListener(latLng -> {
            mLatLng = latLng;
            CameraUpdate newCameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            mMap.clear();
            mMap.animateCamera(newCameraUpdate);
            mMap.addMarker(markerOptions);
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.addLocation.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(MapFragment.this);
            navController.getPreviousBackStackEntry().getSavedStateHandle().set(ShippingFragment.KEY, mLatLng);
            getActivity().onBackPressed();
        });
    }

    @Override
    public void onResume() {
        mBinding.mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mBinding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mBinding.mapView.onLowMemory();
    }
}