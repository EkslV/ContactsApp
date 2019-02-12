package com.android.test.contactapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.test.contactapp.R;
import com.android.test.contactapp.models.Contact;

import java.util.List;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ItemHolder> {

    List<Contact> contacts;

    public RecyclerContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        Contact contact = contacts.get(position);
        itemHolder.contactNameTxt.setText(contact.getName());
        itemHolder.contactPhoneTxt.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    class ItemHolder extends RecyclerView.ViewHolder {

        TextView contactNameTxt, contactPhoneTxt;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            contactNameTxt = itemView.findViewById(R.id.contactNameTxt);
            contactPhoneTxt = itemView.findViewById(R.id.contactPhoneTxt);
        }
    }
}
