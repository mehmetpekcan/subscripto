package com.mehmetpekcan.subscripto;

public class Subscription {
 int logo;
 String date, brand, price;

 public Subscription(int logo, String date, String brand, String price) {
  this.logo = logo;
  this.date = date;
  this.brand = brand;
  this.price = price;
 }

 public int getLogo() {
  return logo;
 }

 public void setLogo(int logo) {
  this.logo = logo;
 }

 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }

 public String getBrand() {
  return brand;
 }

 public void setBrand(String brand) {
  this.brand = brand;
 }

 public String getPrice() {
  return price;
 }

 public void setPrice(String price) {
  this.price = price;
 }
}
