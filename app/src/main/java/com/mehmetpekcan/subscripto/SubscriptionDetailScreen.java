package com.mehmetpekcan.subscripto;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;

public class SubscriptionDetailScreen extends Fragment {
 public ArrayList<Subscription> subscriptions = MainScreen.subscriptions;
 public TextView twDescription, twCost, twDate;
 public ImageView iwLogo;
 Button buttonPaid, buttonEdit;
 Integer isPaid, subscriptionId;

 public SubscriptionDetailScreen() { }

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
  buttonPaid = getActivity().findViewById(R.id.buttonPaid);
  buttonEdit = getActivity().findViewById(R.id.buttonEdit);


  /*
  * API call will be implement here to take subs. detail
  * */
  if (getArguments() != null) {
   subscriptionId =  SubscriptionDetailScreenArgs.fromBundle(getArguments()).getSubscriptionId();

   twCost.setText(subscriptions.get(subscriptionId).getPrice());
   twDate.setText(subscriptions.get(subscriptionId).getDate());
   twDescription.setText(subscriptions.get(subscriptionId).getDescription());
   iwLogo.setImageResource(subscriptions.get(subscriptionId).getLogo());
  }

  buttonPaid.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    isPaid = subscriptions.get(subscriptionId).getIsPaid();

    if (isPaid == 1) {
     subscriptions.get(subscriptionId).setIsPaid(0);
     buttonPaid.setBackgroundResource(R.color.RED);
     buttonPaid.setText("Unpaid");
    } else {
     subscriptions.get(subscriptionId).setIsPaid(1);
     buttonPaid.setBackgroundResource(R.color.PRIMARY_COLOR);
     buttonPaid.setText("Paid");
    }

    Log.d("Bu", "deger" + subscriptions.get(subscriptionId).getIsPaid());
   }
  });

  buttonEdit.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    Navigation.findNavController(view).navigate(SubscriptionDetailScreenDirections.actionSubscriptionDetailScreenToSubscriptionEdit(subscriptionId));
   }
  });

 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  return inflater.inflate(R.layout.fragment_subscription_detail_screen, container, false);
 }
}