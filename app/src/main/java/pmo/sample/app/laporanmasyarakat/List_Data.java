package pmo.sample.app.laporanmasyarakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import pmo.sample.app.laporanmasyarakat.Menu.Data_Kebakaran;
import pmo.sample.app.laporanmasyarakat.Menu.Recycle_Kebakaran;
import pmo.sample.app.laporanmasyarakat.R;

public class List_Data extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Recycle_Kebakaran adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference;
    private ArrayList<Data_Kebakaran> dataKebakaranList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        recyclerView = findViewById(R.id.recyclerView);

        // Menerima data dari Intent
        Intent intent = getIntent();
        String jenisLaporan = intent.getStringExtra("jenisLaporan");
        String pelapor = intent.getStringExtra("pelapor");
        String inputNomor = intent.getStringExtra("inputNomor");
        String inputKejadian = intent.getStringExtra("inputKejadian");
        String tanggal_kejadian = intent.getStringExtra("tanggal_kejadian");

        // Lakukan sesuatu dengan data yang diterima, misalnya tampilkan di log atau tambahkan ke RecyclerView
        Log.d("List_Data", "Data diterima: " + jenisLaporan + ", " + pelapor + ", " + inputNomor + ", " + inputKejadian + ", " + tanggal_kejadian);

        // Selanjutnya, Anda dapat menambahkan data ke RecyclerView seperti yang Anda lakukan sebelumnya
        // getData("");

        setupRecyclerView();
    }


    private void getData(String data) {
        reference = FirebaseDatabase.getInstance().getReference().child("Kebakaran");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataKebakaranList = new ArrayList<>();
                dataKebakaranList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Data_Kebakaran kebakaran = snapshot.getValue(Data_Kebakaran.class);
                    kebakaran.setKey(snapshot.getKey());
                    dataKebakaranList.add(kebakaran);
                }
                adapter = new Recycle_Kebakaran(dataKebakaranList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Data Gagal Dimuat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back));
        recyclerView.addItemDecoration(itemDecoration);
    }
}
