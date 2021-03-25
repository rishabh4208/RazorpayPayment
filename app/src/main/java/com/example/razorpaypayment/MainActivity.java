package com.example.razorpaypayment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPay = findViewById(R.id.bt_pay);

        String sAmount = "100";

        int amount = Math.round(Float.parseFloat(sAmount) * 100);

        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Checkout checkout = new Checkout();

                checkout.setKeyID("KEY_ID");

                checkout.setImage(R.drawable.rzp_logo);

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("name" , "NAME");
                    jsonObject.put("description" , "Test Payment");
                    jsonObject.put("theme.color" , "#0093DD");
                    jsonObject.put("currency" , "INR");
                    jsonObject.put("amount" , amount);
                    jsonObject.put("prefill.contact" , "NUMBER");
                    jsonObject.put("prefill.email" , "E-MAIL");

                    checkout.open(MainActivity.this, jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment ID");

        builder.setMessage(s);

        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}