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

import butterknife.Bind;
import butterknife.ButterKnife;


public class MapsFragment extends Fragment implements OnMapReadyCallback, OnMapClickListener {

    private GoogleMap mMap;

    @Bind(R.id.mapView)
    MapView mMapView;

    private DialogFragment mDialogFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_maps, parent, false);
        ButterKnife.bind(this, rootView);
        mMapView.onCreate(savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
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
        LatLng point = new LatLng(44, 143);
        mMap.addMarker(new MarkerOptions()
                .position(point));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 3));
    }

    @Override
    public void onMapClick(LatLng point) {
        // TODO: launch dialog fragment, get title and description, store in SQL

//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.show(mDialogFragment);
//        transaction.commit();
        //make the dialogFragment centered at point
        // Pin pin = new Pin(point, title, description);

        Bitmap b = ((BitmapDrawable) ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.barry_glass_head)).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 130, false);
        //Bitmap barryBitmap = BitmapFactory.barryHead;

        mMap.addMarker(new MarkerOptions()
                .position(point)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmapResized))
                        //.title()
                        //.snippet()
                .draggable(true));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 3));
    }

}
