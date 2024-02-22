package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responseTopUpList.LsttopupreportItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopUpListAdopter extends RecyclerView.Adapter<TopUpListAdopter.ViewHolder> {
    private List<LsttopupreportItem> models;
    private Context context;

    public TopUpListAdopter(List<LsttopupreportItem> productList, Context context) {
        this.models = productList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_topup, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvName.setText(models.get(i).getName());
        viewHolder.tvSiteName.setText(models.get(i).getSiteName());
        viewHolder.tvSectorName.setText(models.get(i).getSectorName());
        viewHolder.tvUpgradationDate.setText(models.get(i).getUpgradtionDate());
        viewHolder.tvProductName.setText(models.get(i).getProductName());
        viewHolder.tvAmount.setText("₹ " + models.get(i).getAmount());
        viewHolder.tvPlotNumber.setText(models.get(i).getPlotNumber());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_site_name)
        TextView tvSiteName;
        @BindView(R.id.tv_sector_name)
        TextView tvSectorName;
        @BindView(R.id.tv_upgradation_date)
        TextView tvUpgradationDate;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_action)
        TextView tvAction;
        @BindView(R.id.tv_plot_Number)
        TextView tvPlotNumber;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}