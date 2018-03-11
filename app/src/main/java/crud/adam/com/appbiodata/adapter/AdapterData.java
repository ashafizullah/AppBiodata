package crud.adam.com.appbiodata.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import crud.adam.com.appbiodata.R;
import crud.adam.com.appbiodata.model.DataModel;

/**
 * Created by adams on 03/11/2018.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<DataModel> mList;
    private Context mContext;

    public AdapterData(Context mContext, List<DataModel> mList){
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.nama.setText(dm.getNama());
        holder.usia.setText(dm.getUsia());
        holder.domisili.setText(dm.getDomisili());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama, domisili, usia;
        public HolderData(View v){
            super(v);

            nama = (TextView) v.findViewById(R.id.tvNama);
            usia = (TextView) v.findViewById(R.id.tvUsia);
            domisili = (TextView) v.findViewById(R.id.tvDomisili);
        }
    }
}