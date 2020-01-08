package com.howtodoinjava.reqresIn;



public class UserApiResponse {
  private User data;

  public void setData(User data){
    this.data = data;
  }

  public User getData(){
    return data;
  }

  public String toString() {
    return "UserApiResponse [data=" + data + "]";
  }

}
