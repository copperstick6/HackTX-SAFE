/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hacktxsafe;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Provides UI for the view with List.
 */
public class ContactListContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        ContentAdapter adapter;
        if (getActivity() instanceof MainActivity) {
            adapter = new ContentAdapter(recyclerView.getContext(), ((MainActivity)getActivity()).getPhoneContacts());
        } else {
            adapter = new ContentAdapter(recyclerView.getContext(), ((RegisterActivity)getActivity()).getPhoneContacts());
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public Button addButton;
        public Button inviteButton;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_contact_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            addButton = (Button) itemView.findViewById(R.id.add_contact);
            inviteButton = (Button) itemView.findViewById(R.id.invite_contact);
            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            */
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;

        private final ArrayList<PhoneContact> mPhoneContacts;

        public ContentAdapter(Context context, ArrayList<PhoneContact> phoneContacts) {
            Resources resources = context.getResources();

            // Limit results to 6
            if (context instanceof MainActivity) {

            }

            mPhoneContacts = phoneContacts;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Bitmap b = BitmapFactory.decodeStream(openPhoto(Integer.parseInt(mPhoneContacts.get(position).getContactId())));
            if(b != null) {
                b.setDensity(Bitmap.DENSITY_NONE);
                Drawable d = new BitmapDrawable(b);
                holder.avator.setImageDrawable(d);
            } else{
                String uri = "@drawable/ic_account_circle_black_24dp";  // where myresource (without the extension) is the file
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.avator.setImageDrawable(res);
            }
            holder.name.setText(mPhoneContacts.get(position).getDisplayName());
            holder.description.setText(mPhoneContacts.get(position).getPhoneNumber());
            if(position > 10) {
                holder.inviteButton.setVisibility(View.VISIBLE);

                holder.inviteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button) v.findViewById(R.id.invite_contact);
                        if(btn.getText().toString().compareToIgnoreCase("INVITE") == 0){
                            btn.setText("INVITED");
                            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_remove_circle_black_24dp, 0, 0, 0);
                        } else{
                            btn.setText("INVITE");
                            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_circle_outline_black_24dp, 0, 0, 0);
                        }
                    }
                });
            }else{
                holder.addButton.setVisibility(View.VISIBLE);

                holder.addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button) v.findViewById(R.id.add_contact);
                        if(btn.getText().toString().compareToIgnoreCase("ADD") == 0){
                            btn.setText("ADDED");
                            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_remove_circle_black_24dp, 0, 0, 0);
                        } else{
                            btn.setText("ADD");
                            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_circle_outline_black_24dp, 0, 0, 0);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mPhoneContacts.size();
        }

        public InputStream openPhoto(long contactId) {
            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
            Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            Cursor cursor = context.getContentResolver().query(photoUri,
                    new String[] {ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
            if (cursor == null) {
                return null;
            }
            try {
                if (cursor.moveToFirst()) {
                    byte[] data = cursor.getBlob(0);
                    if (data != null) {
                        return new ByteArrayInputStream(data);
                    }
                }
            } finally {
                cursor.close();
            }
            return null;
        }
    }

}
