package com.sam_chordas.android.stockhawk.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sam_chordas.android.stockhawk.R;

public class DetailFragment extends Fragment {

    TextView TVlastPrice, TVchange, TVpercentChange, TVname, TVopenValue, TVhighValue, TVlowValue;
    private static final String TAG = "DetailFragment";

    public DetailFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        getHandles(v);
        Bundle data = getArguments();
        setValues(data);
        return v;

    }

    private void setValues(Bundle data) {

        try {
            String[] name = data.getString(getString(R.string.key_name)).split(" ");
            TVname.setText(name[0]);
        }catch (Exception e){
            e.printStackTrace();
            TVname.setText(data.getString(getString(R.string.key_name)));
        }
        TVchange.setText(data.getString(getString(R.string.key_change)));
        TVpercentChange.setText(data.getString(getString(R.string.key_change_percent)));
        TVhighValue.setText(data.getString(getString(R.string.key_high)));
        TVlowValue.setText(data.getString(getString(R.string.key_low)));
        TVopenValue.setText(data.getString(getString(R.string.key_open)));
        TVlastPrice.setText(data.getString(getString(R.string.key_last_price)));

    }

    public void getHandles(View v) {
        TVname = (TextView) v.findViewById(R.id.name_value_textview);
        TVchange = (TextView) v.findViewById(R.id.change_textview);
        TVpercentChange = (TextView) v.findViewById(R.id.percent_change_textview);
        TVopenValue = (TextView) v.findViewById(R.id.open_value_textview);
        TVlowValue = (TextView) v.findViewById(R.id.low_value_textview);
        TVlastPrice = (TextView) v.findViewById(R.id.last_price_textview);
        TVhighValue = (TextView) v.findViewById(R.id.high_value_textview);
    }



    /*
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
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    */

}
