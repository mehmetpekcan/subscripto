package com.mehmetpekcan.subscripto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  /*
  * Bottom navigation menu
  */
  BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
  NavController navController = Navigation.findNavController(this,  R.id.fragment);
  NavigationUI.setupWithNavController(bottomNavigationView, navController);
 }
}