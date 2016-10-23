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

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Provides UI for the view with Tile.
 */
public class TileContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    
    public TileContentFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TileContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TileContentFragment newInstance(String param1, String param2) {
        TileContentFragment fragment = new TileContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        /*
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        EditText firstName = (EditText)getActivity().findViewById(R.id.first_name);
        EditText lastName = (EditText)getActivity().findViewById(R.id.last_name);
        EditText email = (EditText)getActivity().findViewById(R.id.email);
        EditText password = (EditText)getActivity().findViewById(R.id.password);

        firstName.setText(sharedPref.getString("register_first_name",""));
        lastName.setText(sharedPref.getString("register_last_name",""));
        email.setText(sharedPref.getString("register_email",""));
        password.setText(sharedPref.getString("register_password",""));
        */
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.weather, container, false);
    }
    
    // TODO: Rename method, update argument and hook method into UI event

    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
       

        /*

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        EditText firstName = (EditText)getActivity().findViewById(R.id.first_name);
        EditText lastName = (EditText)getActivity().findViewById(R.id.last_name);
        EditText email = (EditText)getActivity().findViewById(R.id.email);
        EditText password = (EditText)getActivity().findViewById(R.id.password);

        editor.putString("register_first_name", firstName.getText().toString());
        editor.putString("register_last_name", lastName.getText().toString());
        editor.putString("register_email", email.getText().toString());
        editor.putString("register_password", password.getText().toString());
        editor.commit();
        */
    }
    
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    }
     */
}