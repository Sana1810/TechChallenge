package com.example.techchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techchallenge.model.GetOTPResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txt_continue;
    EditText edt_ph_num;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_ph_num = findViewById(R.id.edt_ph_num);
        txt_continue = findViewById(R.id.txt_continue);
        number = String.valueOf(edt_ph_num.getText());

        txt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumberLogin();

            }
        });

    }

    private void phoneNumberLogin() {
        ApiInterface apiInterface = InterfaceGenerator.createService().create(ApiInterface.class);
        Call<GetOTPResponse> call = apiInterface.getOTP(number);
        call.enqueue(new Callback<GetOTPResponse>() {
            @Override
            public void onResponse(Call<GetOTPResponse> call, Response<GetOTPResponse> response) {
//                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    if(response.code()==200){

                        SharedPreferences.Editor editor =getSharedPreferences("MyPref",MODE_PRIVATE).edit();
                        editor.putString("number",number);
                        editor.commit();

                        Intent i = new Intent(MainActivity.this, OtpScreen.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOTPResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}