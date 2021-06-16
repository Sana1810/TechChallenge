package com.example.techchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.techchallenge.model.notes_list.ProfileListResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesScreen extends AppCompatActivity {

    SharedPreferences pref;
    String token;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        token = pref.getString("token","");
        Log.d("token",token);

        profileList();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_discover:
                        Toast.makeText(NotesScreen.this, "Discover", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_matches:
                        Toast.makeText(NotesScreen.this, "Matches", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_notes:
                        Toast.makeText(NotesScreen.this, "Notes", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(NotesScreen.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }

    private void profileList() {

        ApiInterface apiInterface = InterfaceGenerator.createService().create(ApiInterface.class);
        Call<ProfileListResponse> call = apiInterface.getProfileList(token);
        call.enqueue(new Callback<ProfileListResponse>() {
            @Override
            public void onResponse(Call<ProfileListResponse> call, Response<ProfileListResponse> response) {

                if(response.isSuccessful()){
                    if(response.code()==200){
                        String strJson = new Gson().toJson(response.body());
                        Log.d("response_body", strJson);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }
}