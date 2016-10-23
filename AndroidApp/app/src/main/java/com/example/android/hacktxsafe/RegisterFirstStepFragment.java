package com.example.android.hacktxsafe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFirstStepFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFirstStepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFirstStepFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterFirstStepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFirstStepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFirstStepFragment newInstance(String param1, String param2) {
        RegisterFirstStepFragment fragment = new RegisterFirstStepFragment();
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
        return inflater.inflate(R.layout.fragment_register_first_step, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

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
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
