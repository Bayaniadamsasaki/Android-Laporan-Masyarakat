package pmo.sample.app.laporanmasyarakat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pmo.sample.app.laporanmasyarakat.Menu.Laporan_Kebakaran;

public class MainActivity extends Activity {

    private View kebakaran, medis, bencana, riwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kebakaran = findViewById(R.id.kebakaran);
        medis = findViewById(R.id.medis);
        bencana = findViewById(R.id.bencana);
        riwayat = findViewById(R.id.riwayat);

        kebakaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Laporan_Kebakaran.class));
            }
        });

    }
}
