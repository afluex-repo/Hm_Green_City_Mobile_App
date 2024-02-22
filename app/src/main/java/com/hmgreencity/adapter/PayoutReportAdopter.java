package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responsePayoutReport.lstPayoutDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayoutReportAdopter extends RecyclerView.Adapter<PayoutReportAdopter.ViewHolder> {
    private List<lstPayoutDetail> models;
    private Context context;

    public PayoutReportAdopter(List<lstPayoutDetail> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payout_report_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvLoginId.setText(models.get(i).getLoginId());
        viewHolder.tvFirstName.setText(models.get(i).getDisplayName());
        // viewHolder.tvPayoutNumber.setText(", Sector " + models.get(i).getSectorName());
        viewHolder.tvPayoutNumber.setText(models.get(i).getPayoutNo());
        viewHolder.tvDirectIncome.setText(models.get(i).getDirectIncome());
        viewHolder.tvBinaryIncome.setText(models.get(i).getBinaryIncome());
        viewHolder.tvGrossAmount.setText(models.get(i).getGrossAmount());
        viewHolder.tvTdsAmount.setText(models.get(i).getTDSAmount());
        viewHolder.tvProcessingFee.setText(models.get(i).getProcessingFee());
        viewHolder.tvNetPayableAmount.setText(models.get(i).getNetAmount());
        viewHolder.tvClosingDate.setText(models.get(i).getClosingDate());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_loginId)
        TextView tvLoginId;
        @BindView(R.id.tv_first_name)
        TextView tvFirstName;
        @BindView(R.id.tv_payout_number)
        TextView tvPayoutNumber;
        @BindView(R.id.tv_direct_income)
        TextView tvDirectIncome;
        @BindView(R.id.tv_binary_income)
        TextView tvBinaryIncome;
        @BindView(R.id.tv_gross_amount)
        TextView tvGrossAmount;
        @BindView(R.id.tv_tds_amount)
        TextView tvTdsAmount;
        @BindView(R.id.tv_processing_fee)
        TextView tvProcessingFee;
        @BindView(R.id.tv_net_payable_amount)
        TextView tvNetPayableAmount;
        @BindView(R.id.tv_closing_date)
        TextView tvClosingDate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
