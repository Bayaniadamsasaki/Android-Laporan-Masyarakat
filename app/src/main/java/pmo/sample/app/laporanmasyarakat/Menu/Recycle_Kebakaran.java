package pmo.sample.app.laporanmasyarakat.Menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pmo.sample.app.laporanmasyarakat.R;

public class Recycle_Kebakaran extends RecyclerView.Adapter<Recycle_Kebakaran.ViewHolder> {

    private List<Data_Kebakaran> kebakaranList;

    // Konstruktor untuk menerima daftar data
    public Recycle_Kebakaran(List<Data_Kebakaran> kebakaranList) {
        this.kebakaranList = kebakaranList;
    }

    // Kelas ViewHolder untuk menyimpan tampilan setiap item
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLaporan;
        TextView textViewTanggal;
        TextView textViewAlamat;
        TextView textViewDiterima;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLaporan = itemView.findViewById(R.id.textViewLaporan);
            textViewTanggal = itemView.findViewById(R.id.textViewTanggal);
            textViewAlamat = itemView.findViewById(R.id.textViewAlamat);
            textViewDiterima = itemView.findViewById(R.id.textViewDiterima);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kebakaran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data_Kebakaran kebakaran = kebakaranList.get(position);

        holder.textViewLaporan.setText(kebakaran.getLaporanKebakaran());
        holder.textViewTanggal.setText(kebakaran.getTanggal());
        holder.textViewAlamat.setText(kebakaran.getAlamat());
        holder.textViewDiterima.setText(kebakaran.getDiterima());
    }

    @Override
    public int getItemCount() {
        return kebakaranList.size();
    }
}
