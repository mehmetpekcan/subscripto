package com.mehmetpekcan.subscripto;

public class Subscription {
 int logo;
 int isPaid = 0;
 String date, brand, price, description;

 public Subscription(int logo, String date, String brand, String price, String description, int isPaid) {
  this.logo = logo;
  this.date = date;
  this.brand = brand;
  this.price = price;
  this.isPaid = isPaid;
  this.description = description;
 }

 public int getIsPaid() {
  return isPaid;
 }

 public void setIsPaid(int isPaid) {
  this.isPaid = isPaid;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
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
