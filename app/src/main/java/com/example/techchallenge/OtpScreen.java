package com.example.techchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techchallenge.model.VerifyOtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpScreen extends AppCompatActivity {

    EditText edt_otp;
    TextView txt_continue;
    String country = "+91";
    String otp, number, token;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        edt_otp = findViewById(R.id.edt_otp);
        txt_continue = findViewById(R.id.txt_continue);
        otp = String.valueOf(edt_otp.getText());
        pref = getSharedPreferences("MyPref", MODE_PRIVATE);

        number = country+pref.getString("number","");

        txt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp();
            }
        });

    }

    private void verifyOtp() {

        ApiInterface apiInterface = InterfaceGenerator.createService().create(ApiInterface.class);
        Call<VerifyOtpResponse> call = apiInterface.getVerifyOtp(number,otp);
        call.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
//                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    if(response.code()==200){

                        token = response.body().getToken();

                        SharedPreferences.Editor editor =getSharedPreferences("MyPref",MODE_PRIVATE).edit();
                        editor.putString("token",token);
                        editor.commit();

                        Intent i = new Intent(OtpScreen.this, NotesScreen.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}