package com.mehmetpekcan.subscripto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SubscriptionDetailScreen extends Fragment {
 public ArrayList<Subscription> subscriptions = MainScreen.subscriptions;
 public TextView twDescription, twCost, twDate;
 public ImageView iwLogo;

 public SubscriptionDetailScreen() { }

 public static SubscriptionDetailScreen newInstance() {
  SubscriptionDetailScreen fragment = new SubscriptionDetailScreen();
  return fragment;
 }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

 }

 public void onViewCreated(View view, Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);

  twDescription = getActivity().findViewById(R.id.twDescription);
  twCost = getActivity().findViewById(R.id.twCost);
  twDate = getActivity().findViewById(R.id.twDate);
  iwLogo = getActivity().findViewById(R.id.iwLogo);

  /*
  * API call will be implement here to take subs. detail
  * */
  if (getArguments() != null) {
   int subscriptionId =  SubscriptionDetailScreenArgs.fromBundle(getArguments()).getSubscriptionId();

   twCost.setText(subscriptions.get(subscriptionId).getPrice());
   twDate.setText(subscriptions.get(subscriptionId).getDate());
   twDescription.setText(subscriptions.get(subscriptionId).getDescription());
   iwLogo.setImageResource(subscriptions.get(subscriptionId).getLogo());
  }
 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  return inflater.inflate(R.layout.fragment_subscription_detail_screen, container, false);
 }
}