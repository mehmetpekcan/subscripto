package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainScreen extends Fragment {
 private ArrayList<Subscription> subscriptions;
 private RecyclerView subscriptionRecycler;
 private SubscriptionAdapter subscriptionAdapter;

 public MainScreen() {
  // Required empty public constructor
 }

 public static MainScreen newInstance(String param1, String param2) {
  MainScreen fragment = new MainScreen();
  return fragment;
 }

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
  setSubscriptions();
  setRecyclerViewSettings();
 }

 public void setSubscriptions() {
  subscriptionRecycler = getActivity().findViewById(R.id.rwSubscription);
  subscriptions = new ArrayList<>();
  subscriptionAdapter = new SubscriptionAdapter(subscriptions);
  subscriptionRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
  subscriptionRecycler.setAdapter(subscriptionAdapter);
 }

 public void setRecyclerViewSettings() {
  subscriptions.add(new Subscription(R.drawable.youtube, "31 June", "Youtube", "8₺"));
  subscriptions.add(new Subscription(R.drawable.netflix, "28 June", "Netflix", "23₺"));
  subscriptions.add(new Subscription(R.drawable.youtube, "31 June", "Youtube", "8₺"));
  subscriptions.add(new Subscription(R.drawable.netflix, "28 June", "Netflix", "23₺"));
  subscriptionAdapter.notifyDataSetChanged();
 }
}