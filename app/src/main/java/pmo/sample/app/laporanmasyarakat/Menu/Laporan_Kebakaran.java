package pmo.sample.app.laporanmasyarakat.Menu;


import static android.text.TextUtils.isEmpty;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import pmo.sample.app.laporanmasyarakat.List_Data;
import pmo.sample.app.laporanmasyarakat.MainActivity;
import pmo.sample.app.laporanmasyarakat.R;

public class Laporan_Kebakaran extends Activity {

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    private EditText jenisLaporan, pelapor, inputNomor, inputKejadian, tanggal_kejadian, txtMultiLine;
    private View vectorback;
    private String getjenisLaporan, getpelapor, getinputNomor, getinputKejadian, gettanggal_kejadian, getimage, gettxtMultiLine;
    private Button button_forgot;
    private ImageView imageView;
    public Uri imageUrl,uri;
    private StorageReference reference;
    private static final int PICK_IMAGE_REQUEST = 1;

    DatabaseReference getReference;
    FirebaseStorage getstorage;
    DatabaseReference getdatabase;
    StorageReference getstorageReference;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_kebakaran);

        vectorback = findViewById(R.id.vectorback);
        button_forgot = findViewById(R.id.button_forgot);
        jenisLaporan = findViewById(R.id.jenisLaporan);
        pelapor = findViewById(R.id.pelapor);
        inputNomor = findViewById(R.id.inputNomor);
        inputKejadian = findViewById(R.id.inputKejadian);
        imageView = findViewById(R.id.imageView);


        vectorback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Laporan_Kebakaran.this, MainActivity.class));
            }
        });


        tanggal_kejadian = findViewById(R.id.tanggal_kejadian);
        dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        tanggal_kejadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        button_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getjenisLaporan = jenisLaporan.getText().toString();
                getpelapor = pelapor.getText().toString();
                getinputNomor = inputNomor.getText().toString();
                getinputKejadian = inputKejadian.getText().toString();
                gettanggal_kejadian = tanggal_kejadian.getText().toString();

                // Membuat Intent untuk membuka aktivitas List_Data
                Intent intent = new Intent(Laporan_Kebakaran.this, List_Data.class);

                // Menyertakan data yang dibutuhkan ke aktivitas List_Data
                intent.putExtra("jenisLaporan", getjenisLaporan);
                intent.putExtra("pelapor", getpelapor);
                intent.putExtra("inputNomor", getinputNomor);
                intent.putExtra("inputKejadian", getinputKejadian);
                intent.putExtra("tanggal_kejadian", gettanggal_kejadian);

                // Meluncurkan aktivitas List_Data
                startActivity(intent);
            }
        });

        //Mendapatkan Refrensi dari Database
        reference = FirebaseStorage.getInstance().getReference();

        //Mendaptkan Instance dari Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Mendaptkan Refrensi dari Database
        getReference = database.getReference();

        button_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getimage = imageView.toString().trim();
                getjenisLaporan = jenisLaporan.getText().toString();
                getpelapor = pelapor.getText().toString();
                getinputNomor = inputNomor.getText().toString();
                getinputKejadian = inputKejadian.getText().toString();
                gettanggal_kejadian = tanggal_kejadian.getText().toString();

                gettxtMultiLine = txtMultiLine.getText().toString();

                checkUser();
            }
        });

    }



    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tanggal_kejadian.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void checkUser() {
        //Mengecek apakah ada data yang kosong
        if (isEmpty(getjenisLaporan) || isEmpty(getpelapor) || isEmpty(getinputNomor) || isEmpty(getinputKejadian) || isEmpty(gettanggal_kejadian) || isEmpty(getimage) || isEmpty(gettxtMultiLine) || uri == null) {
            //Jika ada, maka akan menampilkan pesan singkat
            Toast.makeText(Laporan_Kebakaran.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            //Mendapatkan data dari ImageView sebagai bytes
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            //Mengkompres Bitmap menjadi JPG dengan kualitas gambar 100%
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] bytes = stream.toByteArray();

            //Lokasi lengkap dimana gambar akan disimpan
            String namaFile = UUID.randomUUID()+".jpg";
            final String pathImage = "gambar/"+namaFile;
            UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            getReference.child("Admin").child("Mahasiswa").push().setValue(new Data_Kebakaran(getimage, getjenisLaporan, getpelapor, getinputNomor, getinputKejadian, gettanggal_kejadian, gettxtMultiLine, uri.toString().trim())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    jenisLaporan.setText("");
                                    pelapor.setText("");
                                    inputKejadian.setText("");
                                    inputNomor.setText("");
                                    tanggal_kejadian.setText("");
                                    txtMultiLine.setText("");
                                    Toast.makeText(Laporan_Kebakaran.this, "Data Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Laporan_Kebakaran.this, List_Data.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Mengambil URI gambar dari hasil pemilihan galeri
            Uri imageUri = data.getData();

            try {
                // Mengubah URI menjadi bitmap dan menampilkannya di ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
