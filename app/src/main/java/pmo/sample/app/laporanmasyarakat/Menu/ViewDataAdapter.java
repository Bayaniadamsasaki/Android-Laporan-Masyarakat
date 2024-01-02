package pmo.sample.app.laporanmasyarakat.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pmo.sample.app.laporanmasyarakat.R;

public class ViewDataAdapter extends BaseAdapter {

    private Context context;
    private List<Data_Kebakaran> dataList;

    public ViewDataAdapter(Context context, List<Data_Kebakaran> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.view_data, parent, false);
        }

        // Mendapatkan data laporan pada posisi tertentu
        Data_Kebakaran data = (Data_Kebakaran) getItem(position);

        // Inisialisasi elemen tampilan pada item
        TextView jenisTextView = convertView.findViewById(R.id.txtKebakaran);
        TextView tanggalTextView = convertView.findViewById(R.id.tanggal);
        TextView alamatTextView = convertView.findViewById(R.id.alamat);
        TextView diterimaTextView = convertView.findViewById(R.id.diterima);

        // Menetapkan nilai teks pada elemen tampilan
        jenisTextView.setText(data.getLaporanKebakaran());
        tanggalTextView.setText("Tanggal: " + data.getTanggal());
        alamatTextView.setText("Alamat: " + data.getAlamat());
        diterimaTextView.setText("Status: " + data.getDiterima());

        // Menambahkan onClickListener untuk membuka detail laporan
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan kode yang diperlukan untuk membuka detail laporan
                // Anda dapat menggunakan Intent untuk membuka activity detail atau tindakan lainnya
                // Sesuaikan dengan kebutuhan aplikasi Anda
            }
        });

        return convertView;
    }
}
