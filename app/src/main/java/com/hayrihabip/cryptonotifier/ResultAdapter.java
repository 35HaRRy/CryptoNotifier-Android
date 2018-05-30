package com.hayrihabip.cryptonotifier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultAdapter extends ArrayAdapter<Cryptos> {

    public ResultAdapter(Context context, ArrayList<Cryptos> cryptos) {
        super(context, 0, cryptos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cryptos crypto = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.crypto, parent, false);

        ((TextView)convertView.findViewById(R.id.txtSource)).setText(crypto.SourcePlatforms);
        ((TextView)convertView.findViewById(R.id.txtCurrency)).setText(crypto.Currency);
        ((TextView)convertView.findViewById(R.id.txtAmount)).setText(crypto.Amount.toString());
        ((TextView)convertView.findViewById(R.id.txtCurrentUnitPrice)).setText(crypto.CurrentUnitPrice.toString());
        ((TextView)convertView.findViewById(R.id.txtCurrentValue)).setText(crypto.CurrentValue.toString());

        ((GridView)convertView.findViewById(R.id.gvPurchased)).setAdapter(new PurchasedAdapter(getContext(), crypto.Purchaseds));

        return convertView;
    }
}
