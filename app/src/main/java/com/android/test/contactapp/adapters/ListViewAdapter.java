package com.android.test.contactapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.test.contactapp.R;
import com.android.test.contactapp.models.Contact;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Contact> contacts;

    public ListViewAdapter(Context mContext, ArrayList<Contact> contacts) {
        this.mContext = mContext;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View previousView, ViewGroup viewGroup) {
        Contact contact = contacts.get(position);
        View view;

        if (previousView != null) {
            view = previousView;
        } else {
            view = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.list_item, viewGroup, false);
        }

        TextView contactNameTxt = view.findViewById(R.id.contactNameTxt);
        contactNameTxt.setText(String.valueOf(contact.getName()));
        TextView contactPhoneTxt = view.findViewById(R.id.contactPhoneTxt);
        contactPhoneTxt.setText(String.valueOf(contact.getPhone()));

        return view;
    }
}
