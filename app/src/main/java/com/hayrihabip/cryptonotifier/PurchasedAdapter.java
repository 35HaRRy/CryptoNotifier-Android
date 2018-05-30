package com.hayrihabip.cryptonotifier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class PurchasedAdapter extends ArrayAdapter<PurchasedCryptos> {

    public PurchasedAdapter(Context context, ArrayList<PurchasedCryptos> purchaseds) {
        super(context, 0, purchaseds);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PurchasedCryptos purchased = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.purchased, parent, false);

        ((TextView)convertView.findViewById(R.id.txtPurchasedUnitPrice)).setText(purchased.PurchasedUnitPrice.toString());
        ((TextView)convertView.findViewById(R.id.txtSpentValue)).setText(purchased.SpentValue.toString());
        ((TextView)convertView.findViewById(R.id.txtProfitPercentage)).setText(purchased.ProfitPercentage.toString());
        ((TextView)convertView.findViewById(R.id.txtProfitValue)).setText(purchased.ProfitValue.toString());

        return convertView;
    }
}
