package pmo.sample.app.laporanmasyarakat.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import pmo.sample.app.laporanmasyarakat.R;

public class Forgot_Password extends AppCompatActivity {

    private EditText forgotemail;
    private Button button_forgot;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotemail = findViewById(R.id.forgotemail);
        button_forgot = findViewById(R.id.button_forgot);
        auth = FirebaseAuth.getInstance();

        button_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = forgotemail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Forgot_Password.this, "Masukan email Anda terlebih dahulu.", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgot_Password.this, "Instruksi pengaturan ulang kata sandi telah dikirim ke email Anda.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Forgot_Password.this, "Gagal mengirim instruksi pengaturan ulang kata sandi. Periksa kembali email Anda.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
