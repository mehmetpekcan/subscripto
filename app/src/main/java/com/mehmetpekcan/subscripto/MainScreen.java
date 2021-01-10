package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class MainScreen extends Fragment {
 static ArrayList<Subscription> subscriptions;
 RecyclerView subscriptionRecycler;
 static SubscriptionAdapter subscriptionAdapter;
 LinearLayout noDataLayout;
 String lorem = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English";

 public MainScreen() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

  return view;
 }

 public void onViewCreated (View view, Bundle savedInstanceState) {
  noDataLayout = getActivity().findViewById(R.id.noDataLayout);
  subscriptionRecycler = getActivity().findViewById(R.id.rwSubscription);

  setSubscriptions();
  setRecyclerViewSettings();
 }

 public void setSubscriptions() {
  subscriptions = new ArrayList<>();
  subscriptionAdapter = new SubscriptionAdapter(subscriptions);
  subscriptionRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
  subscriptionRecycler.setAdapter(subscriptionAdapter);
 }

 public void setRecyclerViewSettings() {
  subscriptions.add(new Subscription(R.drawable.youtube, "31 June", "Youtube", "8₺", lorem));
  subscriptions.add(new Subscription(R.drawable.netflix, "28 June", "Netflix", "23₺", lorem));

  if (subscriptions.isEmpty()) {
   noDataLayout.setVisibility(VISIBLE);
  } else {
   subscriptionRecycler.setVisibility(VISIBLE);
   subscriptionAdapter.notifyDataSetChanged();
  }
 }
}