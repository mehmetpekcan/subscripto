package com.mehmetpekcan.subscripto;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.VISIBLE;

public class MainScreen extends Fragment {
 static ArrayList<Subscription> subscriptions;
 RecyclerView subscriptionRecycler;
 static SubscriptionAdapter subscriptionAdapter;
 LinearLayout noDataLayout;
 TextView userInfoField;
 Button noDataAddButton;
 private FirebaseFirestore firebaseFirestore;

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
  userInfoField = getActivity().findViewById(R.id.userInfoField);
  noDataAddButton = getActivity().findViewById(R.id.noDataAddButton);

  firebaseFirestore = FirebaseFirestore.getInstance();

  noDataAddButton.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    Navigation.findNavController(view).navigate(MainScreenDirections.actionMainScreenToNewSubscriptionScreen());
   }
  });

  SharedPreferences clientEditor = getActivity().getSharedPreferences("clientPref", MODE_PRIVATE);
  String name = clientEditor.getString("name", "");
  String surname = clientEditor.getString("surname", "");
  userInfoField.setText(name + " " + surname);

  final ArrayList<Subscription> list = new ArrayList<>();

  CollectionReference colRef = firebaseFirestore.collection("subscriptions");
  colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
   @Override
   public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
    if (value != null ) {
     for (DocumentSnapshot snapshot: value.getDocuments()) {
      Map<String, Object> data = snapshot.getData();

      assert data != null;
      Subscription newSubs = new Subscription(
              Integer.parseInt(String.valueOf(Objects.requireNonNull(data.get("image")))),
              (String) data.get("date"),
              (String) data.get("name"),
              (String) data.get("price"),
              (String) data.get("description"),
              Integer.parseInt(String.valueOf(data.get("isPaid"))),
              snapshot.getId()
              );

      list.add(newSubs);
     }
     setSubscriptions(list);
     setRecyclerViewSettings();
    }
   }
  });

 }

 public void setSubscriptions(ArrayList<Subscription> list) {
  subscriptions = new ArrayList<>();

  if (list != null) {
   for (int i = 0; i < list.size(); i++) {
    subscriptions.add(list.get(i));
   }
  }

  subscriptionAdapter = new SubscriptionAdapter(subscriptions);
  subscriptionRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
  subscriptionRecycler.setAdapter(subscriptionAdapter);
 }

 public void setRecyclerViewSettings() {
  if (subscriptions.isEmpty()) {
   noDataLayout.setVisibility(VISIBLE);
  } else {
   subscriptionRecycler.setVisibility(VISIBLE);
   subscriptionAdapter.notifyDataSetChanged();
  }
 }
}