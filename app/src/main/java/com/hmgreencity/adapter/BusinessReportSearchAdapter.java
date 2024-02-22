package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responseBusinessReport.Lstassociate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessReportSearchAdapter extends RecyclerView.Adapter<BusinessReportSearchAdapter.ViewHolder> {
    private List<Lstassociate> models;
    private Context context;

    public BusinessReportSearchAdapter(List<Lstassociate> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_business_report, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvLoginId.setText(models.get(i).getLoginId());
        viewHolder.tvName.setText(models.get(i).getDisplayName());
        viewHolder.tvAmount.setText(models.get(i).getNetAmount());
        viewHolder.tvBv.setText(models.get(i).getLeadershipBonus());
        viewHolder.tvClosingDate.setText(models.get(i).getClosingDate());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_loginId)
        TextView tvLoginId;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_closing_date)
        TextView tvClosingDate;

        @BindView(R.id.tv_bv)
        TextView tvBv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
