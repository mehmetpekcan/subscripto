package com.mehmetpekcan.subscripto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionHolder> {
 public ArrayList<Subscription> subscriptions;

 public SubscriptionAdapter(ArrayList<Subscription> subscriptions) {
  this.subscriptions = subscriptions;
 }

 @NonNull
 @Override
 public SubscriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
  View subscriptionRow = LayoutInflater
          .from(parent.getContext())
          .inflate(R.layout.subscription_row, parent, false);

  return new SubscriptionHolder(subscriptionRow);
 }

 @Override
 public void onBindViewHolder(@NonNull SubscriptionHolder holder, int position) {
  holder.brandTitle.setText(subscriptions.get(position).getBrand());
  holder.subscriptionDate.setText(subscriptions.get(position).getDate());
  holder.subscriptionPrice.setText(subscriptions.get(position).getPrice());
  holder.brandLogo.setImageResource(subscriptions.get(position).getLogo());
 }

 @Override
 public int getItemCount() {
  return subscriptions.size();
 }

 public class SubscriptionHolder extends RecyclerView.ViewHolder {
  TextView brandTitle, subscriptionPrice, subscriptionDate;
  ImageView brandLogo;

  public SubscriptionHolder(@NonNull View itemView) {
   super(itemView);

   brandTitle = itemView.findViewById(R.id.brandTitle);
   subscriptionDate = itemView.findViewById(R.id.subscriptionDate);
   subscriptionPrice = itemView.findViewById(R.id.subscriptionPrice);
   brandLogo = itemView.findViewById(R.id.brandLogo);
  }
 }
}
