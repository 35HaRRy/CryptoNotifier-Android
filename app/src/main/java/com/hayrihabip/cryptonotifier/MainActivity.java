package com.hayrihabip.cryptonotifier;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Remove title bar

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        List(getString(R.string.APIRequest));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemList:
                CreateRequestDialog();
                break;
            case R.id.itemRefresh:
                List(getString(R.string.APIRequest));
                break;
            default:
                break;
        }
        return true;
    }

    public void CreateRequestDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.request, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setView(dialogView)
                .setPositiveButton(R.string.Btn_List, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText txtRequest = dialogView.findViewById(R.id.txtRequest);
                        List(txtRequest.getEditableText().toString());
                    }
                })
                .setNegativeButton(R.string.Btn_Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }

    public void List(String request) {
        GridView gvrResult = findViewById(R.id.gvrResult);

        String resultMessage = "Başarılı";
        try {
            ArrayList<Cryptos> cryptos = API.GetBalance(request);
            gvrResult.setAdapter(new ResultAdapter(this, cryptos));
        } catch (Exception e) {
            resultMessage = e.getMessage();
        }

        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG);
    }
}
