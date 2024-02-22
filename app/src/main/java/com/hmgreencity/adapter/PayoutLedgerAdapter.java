package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responsePayoutLedger.lstpayoutledger;
import com.hmgreencity.model.response.responsePayoutReport.lstPayoutDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayoutLedgerAdapter extends RecyclerView.Adapter<PayoutLedgerAdapter.ViewHolder> {
    private List<lstpayoutledger> models;
    private Context context;

    public PayoutLedgerAdapter(List<lstpayoutledger> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adopter_payout_ledger, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvNarration.setText(models.get(i).getNarration());
        viewHolder.crAmount.setText(models.get(i).getCrAmount());
        // viewHolder.tvPayoutNumber.setText(", Sector " + models.get(i).getSectorName());
        viewHolder.drAmount.setText(models.get(i).getDrAmount());
        viewHolder.tvBalance.setText(models.get(i).getPayoutBalance());
        viewHolder.tvDate.setText(models.get(i).getAddedOn());
        viewHolder.text_transaction_number.setText(models.get(i).getTransactionNo());
        viewHolder.tv_tds.setText(models.get(i).getTDSCharge());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_narration)
        TextView tvNarration;
        @BindView(R.id.cr_amount)
        TextView crAmount;
        @BindView(R.id.dr_amount)
        TextView drAmount;
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.tv_date)
        TextView tvDate;
 @BindView(R.id.tv_transaction_no)
        TextView text_transaction_number;

 @BindView(R.id.tv_tds)
        TextView tv_tds;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
