package com.hmgreencity.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responseDownline.LstdirectDownline;
import com.hmgreencity.model.response.responseDownlineSearch.Lstdirectdownline;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownlineSearchAdapter extends RecyclerView.Adapter<DownlineSearchAdapter.ViewHolder> {
    private List<Lstdirectdownline> models;
    private Context context;

    public DownlineSearchAdapter(List<Lstdirectdownline> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public DownlineSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_downline, viewGroup, false);
        return new DownlineSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownlineSearchAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tvLoginId.setText(models.get(i).getLoginId());
        viewHolder.tvName.setText(models.get(i).getName());
        viewHolder.tvPackage.setText(models.get(i).getPackage());
        viewHolder.tvJoiningDate.setText(models.get(i).getJoiningDate());
        viewHolder.tvActivationDate.setText(models.get(i).getPermanentDate());
        viewHolder.tvLeg.setText(models.get(i).getLeg());
        viewHolder.tvMobileNumber.setText(models.get(i).getMobile());
        viewHolder.tvStatus.setText(models.get(i).getStatus());
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
        @BindView(R.id.tv_leg)
        TextView tvLeg;
        @BindView(R.id.tv_joining_date)
        TextView tvJoiningDate;
        @BindView(R.id.tv_package)
        TextView tvPackage;
        @BindView(R.id.tv_activation_date)
        TextView tvActivationDate;
        @BindView(R.id.tv_mobile_number)
        TextView tvMobileNumber;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
