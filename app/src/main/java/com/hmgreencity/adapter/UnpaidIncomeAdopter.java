package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.response.responseUnpaidIncome.lstunpaidincomes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnpaidIncomeAdopter extends RecyclerView.Adapter<UnpaidIncomeAdopter.ViewHolder> {
    private List<lstunpaidincomes> models;
    private Context context;

    public UnpaidIncomeAdopter(List<lstunpaidincomes> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adopter_unpaid_incomes, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvLoginId.setText(models.get(i).getFromLoginId());
        viewHolder.tvName.setText(models.get(i).getFromUserName());
        viewHolder.tvIncomeType.setText(models.get(i).getIncomeType());
        viewHolder.tvDate.setText(models.get(i).getDate());
        viewHolder.tvAmount.setText(models.get(i).getAmount());
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
        @BindView(R.id.tv_income_type)
        TextView tvIncomeType;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
