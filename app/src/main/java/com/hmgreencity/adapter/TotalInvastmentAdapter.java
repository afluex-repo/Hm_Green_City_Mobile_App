package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responseTotalInvastment.Lstinvestment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalInvastmentAdapter extends RecyclerView.Adapter<TotalInvastmentAdapter.ViewHolder> {
    private List<Lstinvestment> models;
    private Context context;

    public TotalInvastmentAdapter(List<Lstinvestment> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adopter_total_invastament, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvProductName.setText(models.get(i).getProductName());
        viewHolder.tvAmount.setText(models.get(i).getAmount());
        viewHolder.tvStatus.setText(models.get(i).getStatus());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
