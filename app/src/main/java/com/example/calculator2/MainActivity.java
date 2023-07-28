package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import kotlin.collections.MapsKt;

public class MainActivity extends AppCompatActivity {
    private Button zero,one,two,three,four,five,six,seven,eight,nine;
    private FloatingActionButton back,plus,minus,multiply,divide,dot,equal;
    private TextView text1;
    private String a="";
    private String b,c,d;
    private float j=0;
    private float i=0;
    private float result;
    private boolean add=false;
    private boolean sub=false;
    private boolean multi=false;
    private boolean div=false;
    private Map<Button,Integer> map=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crate();
        map.put(zero,0);
        map.put(one,1);
        map.put(two,2);
        map.put(three,3);
        map.put(four,4);
        map.put(five,5);
        map.put(six,6);
        map.put(seven,7);
        map.put(eight,8);
        map.put(nine,9);
        dash(zero);dash(one);dash(two);dash(three);dash(four);dash(five);dash(six);dash(seven);
        dash(eight);dash(nine);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check()) {
                    matrix();
                    add = true;
                    a = a + "+";
                    text1.setText(a);
                    a = "";
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){
                    matrix();
                    sub=true;
                    a=a+"-";
                    text1.setText(a);
                    a="";
                }
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){
                    matrix();
                    multi=true;
                    a=a+"*";
                    text1.setText(a);
                    a="";
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){
                    matrix();
                    div=true;
                    a=a+"/";
                    text1.setText(a);
                    a="";
                }
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!a.isEmpty()){
                    a=a.substring(0,a.length()-1);
                    if(!a.isEmpty()) {
                        j = Float.parseFloat(a);
                    }else{
                        j=0;
                    }
                    text1.setText(a);
                }
            }
        });
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a="";i=j=0;add=sub=multi=div=false;
                text1.setText(a);
                return true;
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){
                    a=a+".";
                    d=a+"0";
                    j=Float.parseFloat(d);
                    text1.setText(a);
                }
            }
        });
    }
    private void crate(){
        zero=findViewById(R.id.zero);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        back=findViewById(R.id.backspace);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        multiply=findViewById(R.id.multiply);
        divide=findViewById(R.id.divide);
        text1=findViewById(R.id.text1);
        equal=findViewById(R.id.equals);
        dot=findViewById(R.id.dot);
    }
    private void dash(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  a=a+map.get(button).toString();
                  text1.setText(a);
                  j=Float.parseFloat(a);
            }
        });
    }
    private boolean check(){
        int n=a.length()-1;
        String k=a.substring(n,n+1);
        return k.equals("+")||k.equals("-")||k.equals("/")||k.equals("*");
    }
    private void result(){
        matrix();
        a=String.valueOf(i);
        text1.setText(a);

    }
    private void matrix(){
        if(add==false &&sub==false&&multi==false&&div==false){
            i = Float.parseFloat(a);
        }
        else if(add==true){
            i=i+j;
            add=false;}
        else if(sub==true){
            i=i-j;
            sub=false;}
        else if(multi==true){
            i=i*j;
            multi=false;}
        else if(div==true){
            try {
                i=i/j;
                div=false;
            }catch (ArithmeticException e){
                text1.setText("Cant divide by  zero");
            }
        }
    }


}