package com.tugas.NongkiKuy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.tugas.NongkiKuy.R;
import com.tugas.NongkiKuy.Model.ModelCafe;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.ViewHolder> {

    private List<ModelCafe> items;
    private CafeAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelCafe modelCafe);
    }

    public CafeAdapter(Context context, List<ModelCafe> items, CafeAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cafe, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelCafe data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarCafe())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgCafe);

        holder.tvNamaCafe.setText(data.getTxtNamaCafe());
        holder.rlListCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaCafe;
        public RelativeLayout rlListCafe;
        public ImageView imgCafe;

        public ViewHolder(View itemView) {
            super(itemView);
            rlListCafe = itemView.findViewById(R.id.rlListCafe);
            tvNamaCafe = itemView.findViewById(R.id.tvNamaCafe);
            imgCafe = itemView.findViewById(R.id.imgCafe);
        }
    }
}
