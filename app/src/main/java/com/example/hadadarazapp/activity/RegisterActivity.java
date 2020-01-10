package com.example.hadadarazapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hadadarazapp.R;
import com.example.hadadarazapp.modal.UsersModal;
import com.example.hadadarazapp.retrofit_class.ApiInterface;
import com.example.hadadarazapp.retrofit_class.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerme();
            }
        });
    }

    private void registerme(){
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();

        UsersModal usersModal=new UsersModal(email, password);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Client.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Void> voidCall=apiInterface.registerUsers(usersModal);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });





    }
}
