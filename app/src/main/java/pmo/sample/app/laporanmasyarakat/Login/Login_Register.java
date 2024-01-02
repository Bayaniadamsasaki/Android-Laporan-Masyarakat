package pmo.sample.app.laporanmasyarakat.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pmo.sample.app.laporanmasyarakat.R;

public class Login_Register extends AppCompatActivity {

    private Button button_masuk, button_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        button_masuk = findViewById(R.id.button_masuk);
        button_daftar = findViewById(R.id.button_daftar);

        button_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Register.this, Login.class));
            }
        });

        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Register.this, Register.class));
            }
        });
    }
}