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
import android.content.Intent;
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
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), ((RegisterActivity)getActivity()).getPhoneContacts());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        //private static final int LENGTH = 18;

        //private final String[] mPlaces;
        //private final String[] mPlaceDesc;
        //private final Drawable[] mPlaceAvators;
        private Context context;

        private final ArrayList<PhoneContact> mPhoneContacts;

        public ContentAdapter(Context context, ArrayList<PhoneContact> phoneContacts) {
            Resources resources = context.getResources();
            mPhoneContacts = phoneContacts;
            this.context = context;
            //mPlaces = resources.getStringArray(R.array.places);
            //mPlaceDesc = resources.getStringArray(R.array.place_desc);
            //TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            //mPlaceAvators = new Drawable[a.length()];
            //for (int i = 0; i < mPlaceAvators.length; i++) {
            //    mPlaceAvators[i] = a.getDrawable(i);
            //}
            //a.recycle();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
            //holder.name.setText(mPlaces[position % mPlaces.length]);
            //holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
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
