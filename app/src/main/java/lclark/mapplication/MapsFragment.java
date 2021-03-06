package lclark.mapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback, OnMapClickListener, AddPinDialogFragment.PinCreatedListener {

    private GoogleMap mMap;

    public static final String ARG_MAPS = "MapsFragment.User";

    @Bind(R.id.mapView)
    MapView mMapView;

    private User mUser;


    public static Fragment newInstance(User user) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MAPS, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, parent, false);
        ButterKnife.bind(this, rootView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        mUser = getArguments().getParcelable(ARG_MAPS);
        return rootView;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        setPins();
    }

    @Override
    public void onMapClick(LatLng point) {
        AddPinDialogFragment fragment = AddPinDialogFragment.newInstance(this, point);
        fragment.setTargetFragment(this, 0);
        fragment.show(getFragmentManager(), "dialog");
    }

    public void setPins() {

        ArrayList<Pin> pins = SQLiteHelper.getInstance(getContext()).getPinsForUser(mUser.getIdNumber());
        for (Pin pin : pins) {
            setPin(pin);
        }
    }

    public void setPin(Pin pin) {

        Bitmap b = ((BitmapDrawable) ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.barry_glass_head)).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 130, false);

        mMap.addMarker(new MarkerOptions()
                .position(pin.getLatLng())
                .icon(BitmapDescriptorFactory.fromBitmap(bitmapResized))
                .title(pin.getmTitle())
                .snippet(pin.getmSnippet())
                .draggable(true));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pin.getLatLng(), 3));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onPinCreated(Pin pin) {
        setPin(pin);
    }
}
