package pmo.sample.app.laporanmasyarakat.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pmo.sample.app.laporanmasyarakat.MainActivity;
import pmo.sample.app.laporanmasyarakat.R;

public class Login extends AppCompatActivity {

    private TextView daftar_link, lupasandi;
    private EditText inputEmail, inputPassword;
    private Button button_masuk;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        daftar_link = findViewById(R.id.daftar_link);
        lupasandi = findViewById(R.id.lupasandi);
        button_masuk = findViewById(R.id.button_masuk);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        daftar_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        lupasandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Forgot_Password.class));
            }
        });

        button_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (isValidInput(email, password)) {
                    if (isUserRegistered(email, password)) {
                        saveLoggedInUser(email);
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Pengguna belum terdaftar. Silakan daftar terlebih dahulu.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Email atau Kata Sandi tidak valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveLoggedInUser(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loggedInEmail", email);
        editor.apply();
    }
    private boolean isUserRegistered(String enteredEmail, String enteredPassword) {
        String registeredEmail = sharedPreferences.getString("email", "");
        return enteredEmail.equals(registeredEmail);
    }
    private boolean isValidInput(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Kata Sandi harus diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}