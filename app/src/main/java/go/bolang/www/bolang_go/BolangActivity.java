package go.bolang.www.bolang_go;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Challenge;
import model.Constant;
import model.DataManager;
import model.GameInfo;
import model.Player;
import model.Position;

public class BolangActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    private List<Marker> challengesMarkers;
    private DatabaseReference mDatabase;
    private DatabaseReference mChallenge;
    private List<Challenge> challenges;
    private Challenge currentChallenge;
    private Player player;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GameInfo gameInfo;
    private Double radius = 20.0;// radius 10 meters
    private LocationManager locationManager;
    private boolean sedangChallange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sedangChallange = false;
        setContentView(R.layout.activity_bolang);

        // set game info
        GameInfo gi = DataManager.loadGameInfo(Constant.FILENAME_GAME_INFO, this.getApplicationContext());
        if(gi != null){
            gameInfo = gi;
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Maaf terjadi kesahalan")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        //Check it's cleared game
        Log.d("DEBUG COY", gameInfo.isCleared()+ " blablabla");
        if(gameInfo.isCleared()){
            finishGame();
        }

        //save game name
        final Bundle extras = getIntent().getExtras();
        gameInfo.setGameName(extras.getString("code"));
        DataManager.saveGameInfo(gameInfo, Constant.FILENAME_GAME_INFO, getApplicationContext());

        player = new Player();

        challenges = new ArrayList<Challenge>();
        challengesMarkers = new ArrayList<Marker>();



        // Firebase Database
        DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference("Game");
        mRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GameInfo gi = DataManager.loadGameInfo(Constant.FILENAME_GAME_INFO, getApplicationContext());

                if(!dataSnapshot.hasChild(gi.getGameName())){
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = mRoot.child(gi.getGameName());
        mChallenge = mDatabase.child("challenges");

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) addPlayer(user.getUid(), user.getDisplayName());


        mChallenge.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                challenges.clear();
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    Challenge challenge = snap.getValue(Challenge.class);
                    challenges.add(challenge);
                }
                if(gameInfo.getChallenges().isEmpty()){
                    gameInfo.setChallenges(challenges);
                    DataManager.saveGameInfo(gameInfo,Constant.FILENAME_GAME_INFO, getApplicationContext());
                    Log.d(this.getClass().getName(), "add " + gameInfo.getChallenges().size() +  " challenge");
                    checkCurrentChallenge();
                }
                if(challenges.isEmpty()){
                    ErrorDialogue("Maaf Game Room tidak tersedia");
                }
                addMarkerChallenge();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //check permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkLocationPersmission();
        }else {
            Log.d(this.getClass().getName(),"Udah di install permissionya");
        }
        // add to support fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void ErrorDialogue(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sedangChallange = false;
        gameInfo = DataManager.loadGameInfo(Constant.FILENAME_GAME_INFO,getApplicationContext());
        addMarkerChallenge();

        //check cleared game
        if(!challenges.isEmpty()){
            if(challenges.size() <= gameInfo.getPlayer().getIndexChallenge()){
                gameInfo.setCleared(true);
            }
        }

        if(!challenges.isEmpty() && gameInfo.isCleared()){
            finishGame();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case Constant.REQUEST_LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(client == null){
                            buildClientApi();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                return;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            mMap.setMyLocationEnabled(true);
            Location location = getLastKnownLocation();
            if (location != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
            }
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildClientApi();
            mMap.setMyLocationEnabled(true);
            System.out.println("Liat last location nya null engga " + (lastLocation == null));
        }

    }

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            try {
                Location location = locationManager.getLastKnownLocation(provider);
                if (location == null) {
                    continue;
                }
                if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = location;
                }
            } catch (SecurityException ignored) {
            }
        }
        return bestLocation;
    }

    public void addMarkerChallenge(){
        for(Marker m: challengesMarkers) {
            m.remove();
        }

        challengesMarkers.clear();
        int index = gameInfo.getPlayer().getIndexChallenge().intValue();
        challenges = gameInfo.getChallenges();

        Log.d("debug index", index + "");
        for(int i = 0; i < challenges.size(); i++){
            Challenge challenge = challenges.get(i);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(challenge.getPosition().latitude, challenge.getPosition().longitude));
            markerOptions.title(challenge.getType());

            if(challenge.isCleared) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }
            else {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }
//            switch (i){
//                case 0:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
//                    break;
//                case 1:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//                    break;
//                case 2:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
//                    break;
//                case 3:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                    break;
//                case 4:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//                    break;
//                case 5:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//                    break;
//                case 6:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                    break;
//                case 7:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
//                    break;
//                default:
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
//                    break;
//            }
            if(gameInfo.getPlayer() == null){
                Log.d(getClass().getName(), "player null");
            }else {
                Log.d(getClass().getName(), "player not null ");
            }
            if(index >= i && mMap != null)
                challengesMarkers.add(mMap.addMarker(markerOptions));
        }
        if(mMap != null){
            mMap.setOnMarkerClickListener(this);
        }
    }

    protected synchronized void buildClientApi() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        client.connect();
    }

    public void addPlayer(String id, String namePlayer){
        player = new Player();
        player.setId(id);
        player.setDisplayName(namePlayer);
        mDatabase.child(Constant.DB_PLAYERS).child(id).setValue(player);
        if(gameInfo.getPlayer() == null){
            gameInfo.setPlayer(player);
            gameInfo.getPlayer().setIndexChallenge(0.0);
            DataManager.saveGameInfo(gameInfo, Constant.FILENAME_GAME_INFO, this.getApplicationContext());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }


    }

    public boolean checkLocationPersmission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constant.REQUEST_LOCATION_CODE);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constant.REQUEST_LOCATION_CODE);
            }
            return false;
        }else
            return true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("debug ah", "claclacla locationChanged bray");
        lastLocation = location;
        if(currentLocationMarker != null){
            currentLocationMarker.remove();
        }

        if(gameInfo != null && gameInfo.isCleared()){
            finishGame();
        }

        LatLng latlang = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlang));
//        mMap.animateCamera(CameraUpdateFactory.zoomBy(1));
            mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(15);
        // set player position
        Position pos = new Position(latlang.latitude, latlang.longitude);
        player.setPosition(pos);
        gameInfo.getPlayer().setPosition(pos);

        if(!sedangChallange) {
            DataManager.saveGameInfo(gameInfo, Constant.FILENAME_GAME_INFO, getApplicationContext());
        }

        //Firebase Database update position player
        mDatabase.child(Constant.DB_PLAYERS).child(mAuth.getCurrentUser().getUid()).child(Constant.DB_LATITUDE).setValue(location.getLatitude());
        mDatabase.child(Constant.DB_PLAYERS).child(mAuth.getCurrentUser().getUid()).child(Constant.DB_LONGITUDE).setValue(location.getLongitude());

        checkCurrentChallenge();

//        if(client != null){
//            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
//        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public void checkCurrentChallenge(){
        if(gameInfo == null) {
            Log.d("DEBUG COY", "blablabla gameinfo nya null");
            return;
        }
        currentChallenge = gameInfo.getCurrentChallenge();
        // Debug nearest challange and get distance to player
        Log.d("DEBUG COY", "blablabla currentChallange " + currentChallenge);

        if(currentChallenge != null){
            // Check if distance uncleared challenge less then radius
            Log.d("DEBUG COY", "blablabla masuk currentChallange distance" + currentChallenge.getDistance(lastLocation));
            if(currentChallenge.getDistance(lastLocation) <= radius){
                Log.d("DEBUG COY", "blablabla radius masuk " + radius);
                Log.d(this.getClass().getName(), "open challenge");
                openChallenge(currentChallenge);
            }
        }
    }

    public Challenge getNearestChallenge(){
        if(lastLocation == null) return  null;
        Float nearest = Float.MAX_VALUE;
        Challenge nearChallange = null;
        Log.d("GameInfo", "Size of game info challenge is " + gameInfo.getChallenges().size());
        for(int i = 0; i < gameInfo.getChallenges().size(); i++){
            Challenge challenge =  gameInfo.getChallenges().get(i);
            Location challengeLocation = new Location("Challenge-" + i);
            challengeLocation.setLatitude(challenge.getPosition().getLatitude());
            challengeLocation.setLongitude(challenge.getPosition().getLongitude());

            Float distance = lastLocation.distanceTo(challengeLocation);
            if(distance <= nearest && !challenge.isCleared()){
                nearChallange = challenge;
                nearest = distance;
            }
            Log.d(getClass().getName(), "distance sekarang adalah "+ distance);
        }
        return  nearChallange;
    }

    public void openChallenge(Challenge challenge){

        if(!sedangChallange) {
            sedangChallange = true;
            Log.d(this.getClass().getName(), "challenge tipe " + challenge.getType());

            // nanti pake bundle extra aja....
            if (challenge.getType().equals(Constant.QUIZ_CHALLENGE)) {
//            Intent intent = new Intent(BolangActivity.this, QuizActivity.class);
//            startActivity(intent);
                Log.i(getClass().getName(), "masuk ke quiz");

                if (challenge.getTypeQuiz().equals(Constant.QUIZ_SCIENCE)) {
                    Intent intent = new Intent(BolangActivity.this, Quiz2Activity.class);
                    startActivity(intent);
                } else if (challenge.getTypeQuiz().equals(Constant.QUIZ_SOCIAL)) {
                    Intent intent = new Intent(BolangActivity.this, QuizActivity.class);
                    startActivity(intent);
                }
            } else if (challenge.getType().equals(Constant.TREASURE_CHALLENGE)) {
                Intent intent = new Intent(BolangActivity.this, ShakeActivity.class);
                startActivity(intent);
            }
        }
    }

    public void finishGame(){
//        this.finish();
        Intent intent = new Intent(BolangActivity.this, ResultActivity.class);
        startActivity(intent);
    }
}
