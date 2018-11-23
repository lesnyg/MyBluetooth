package com.example.edu.mybluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.UUID;

public class BluetoothClientActivity extends AppCompatActivity implements AdapterView.OnClickListener {
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    String address;
    BluetoothSocket bluetoothSocket;
    private BluetoothAdapter bluetoothAdapter = null;
    Button buttonUp,buttonDown,buttonLeft,buttonRight,buttonCenter,buttonA,buttonB,buttonC,buttonD,buttonE;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_client);

        progressBar = findViewById(R.id.progressBar);

        Intent newint = getIntent();
        address = newint.getStringExtra("device_address");
        new ConnectBluetoothTask().execute();

        buttonUp = findViewById(R.id.buttonUp);
        buttonUp.setOnClickListener(this);
        buttonDown = findViewById(R.id.buttonDown);
        buttonDown.setOnClickListener(this);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(this);
        buttonRight = findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(this);
        buttonCenter = findViewById(R.id.buttonCenter);
        buttonCenter.setOnClickListener(this);
        buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(this);
        buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(this);
        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        buttonD = findViewById(R.id.buttonD);
        buttonD.setOnClickListener(this);
        buttonE = findViewById(R.id.buttonE);
        buttonE.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String message = "";
        switch (v.getId()) {
            case R.id.buttonUp:
                message = "U";
                break;
            case R.id.buttonDown:
                message = "D";
                break;
            case R.id.buttonLeft:
                message = "L";
                break;
            case R.id.buttonRight:
                message = "R";
                break;
            case R.id.buttonA:
                message = "a";
                break;
            case R.id.buttonB:
                message = "b";
                break;
            case R.id.buttonC:
                message = "c";
                break;
            case R.id.buttonD:
                message = "d";
                break;
            case R.id.buttonE:
                message = "e";
                break;
            case R.id.buttonCenter:
                message = "C";
                break;
        }
        try {
            bluetoothSocket.getOutputStream().write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ConnectBluetoothTask extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Void... devices) {
            if (bluetoothSocket == null) {
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
                try {
                    bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    bluetoothSocket.connect();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

