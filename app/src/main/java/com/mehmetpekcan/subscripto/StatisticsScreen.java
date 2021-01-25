package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Objects;

public class StatisticsScreen extends Fragment {
 TextView totalChargeInput;
 private FirebaseFirestore firebaseFirestore;
 Integer totalCharge = 0;

 public StatisticsScreen() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

 }


 public void onViewCreated (View view, Bundle savedInstanceState) {
  totalChargeInput = getActivity().findViewById(R.id.totalChargeInput);
  firebaseFirestore = FirebaseFirestore.getInstance();


  CollectionReference colRef = firebaseFirestore.collection("subscriptions");
  colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
   @Override
   public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
    if (value != null ) {

     for (DocumentSnapshot snapshot: value.getDocuments()) {
      Map<String, Object> data = snapshot.getData();
      totalCharge += Integer.parseInt(String.valueOf(Objects.requireNonNull(data.get("price"))));
     }
     totalChargeInput.setText(totalCharge.toString());
    }
   }
  });

 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState) {
  return inflater.inflate(R.layout.fragment_statistics_screen, container, false);
 }
}