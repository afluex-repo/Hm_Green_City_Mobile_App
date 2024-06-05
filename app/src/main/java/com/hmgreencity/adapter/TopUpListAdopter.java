package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

    private String type;

    public TopUpListAdopter(List<LsttopupreportItem> models, Context context, String type) {
        this.models = models;
        this.context = context;
        this.type = type;
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

        if(type.equals("New")){
            viewHolder.ll_block.setVisibility(View.VISIBLE);
            viewHolder.tv_block_name.setText(models.get(i).getBlockName());
        }else{
            viewHolder.ll_block.setVisibility(View.GONE);
        }
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

        TextView tvPlotNumber; @BindView(R.id.tv_block_name)
        TextView tv_block_name;
        @BindView(R.id.ll_block)
        LinearLayout ll_block;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}