package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class splash_screen extends Fragment {
 int SPLASH_TIME = 1000;

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState) {
  // Inflate the layout for this fragment
  return inflater.inflate(R.layout.fragment_splash_screen, container, false);
 }

 @Override
 public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);

  new Handler().postDelayed(new Runnable() {
   @Override
   public void run() {
    Navigation.findNavController(view).navigate(splash_screenDirections.actionSplashScreenToLoginScreen());
   }
  }, SPLASH_TIME);
 }
}