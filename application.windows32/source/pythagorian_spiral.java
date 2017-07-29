import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pythagorian_spiral extends PApplet {

//Author: Joshua Nelson 
//Purpose: To create a pythagorean spiral, which is a series of right angle triangles spiraling outward.
final int size_x=1000;
final int size_y=700;
float first_x=size_x/2;
float first_y=size_y/2;
float slope;
float per_slope;
float length_slope;
//slopes can be viewed as changes in x and y. The unit_step_x is the distance a slope travels in the x direction in order to satisfy a size specified in the size_tri variable.
float unit_step_x;
float last_x=first_x+50;
float last_y=first_y-50;
float temp_x;
float temp_y;
int limit=1000;
// This program uses arrays rather than linked lists. 
//My reasoning is that although linked lists don't have to have an upper limit
//they do however take a longer time to add numbers to the list especially if that list is long
//and this program tends to slow down with more elements.
float[] list_tri = new float[limit];
float[] list_tri_x =new float[limit];
float[] list_tri_y=new float[limit];
float[] list_tri_x_1=new float[limit];
float[] list_tri_y_1= new float[limit];
float[] list_tri_color_1= new float[limit];
float[] list_tri_color_2= new float[limit];
float[] list_tri_color_3= new float[limit];
float[] list_tri_color_4= new float[limit];
int counter=0;
// adjust size
int size_tri=25;
public void setup() 
{
 
 
 background(10,50,150);
 //The reason the first triangle is so that the last_x and last_y variables can be filled.
 triangle(first_x, first_y, first_x+size_tri, first_y, first_x+size_tri, first_y-size_tri);
 last_x=first_x+size_tri;
 last_y=first_y-size_tri;
}
public void draw()

{
  // keeps  the number of triangles from exceeding the limit that is placed on it
  if (counter!=limit){ 
    //calulates slope, perpendicular slope, and length
    slope=(first_y-last_y)/(first_x-last_x);
    per_slope=-1/slope;
    length_slope=sqrt(pow(per_slope,2.0f)+1);
    // these if and else statements are meant to guide the direction that the hypotenuse of the triangle is directed at
    if(last_y<first_y){
        //This scales the unit_step(see variables at the top) to the the right size using porportions
        unit_step_x=-size_tri/length_slope;
    }
    else{
        //This scales the unit_step(see variables at the top) to the the right size using porportions
        unit_step_x=size_tri/length_slope;
    }
    // this keeps the computer from calculating these variables twice.
    // since unit_step is the amount of units traveled in the x direction so a specified size it is used to calculate were a triangles x value should be
    temp_x=last_x+unit_step_x;
    //unit_step_x*per_slope calculates the amount of distance needed to travel in the y direction 
    //since slope is dy/dx multiplying by a change in x gives you a change in y.
    temp_y=last_y+unit_step_x*per_slope;
    //This stores all the data regarding a triangle 
    list_tri_x[counter]=last_x;
    list_tri_y[counter]=last_y;
    list_tri_x_1[counter]=temp_x;
    list_tri_y_1[counter]=temp_y;
    list_tri_color_1[counter]=random(0,20); 
    list_tri_color_2[counter]=random(0,200);
    list_tri_color_3[counter]=random(0,20);
   // list_tri_color_4[counter]=random(255);
  
    counter=counter+1;
    //stores the variables
    last_x=temp_x;
    last_y=temp_y;
  }
  else {
    delay(10);
  }
  // This displays all the triangles   
  for (int i = counter-1;i!=-1;i=i-1)
    {
      if (i==0){
        triangle(first_x, first_y, first_x+size_tri, first_y, first_x+size_tri, first_y-size_tri);
      }
      beginShape();
      triangle(first_x, first_y,list_tri_x[i],list_tri_y[i],list_tri_x_1[i],list_tri_y_1[i]);
      fill(list_tri_color_1[i],list_tri_color_2[i],list_tri_color_3[i]);//,list_tri_color_4[i]);
      endShape();  
  }
}
  public void settings() {  size(1000,700);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pythagorian_spiral" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
