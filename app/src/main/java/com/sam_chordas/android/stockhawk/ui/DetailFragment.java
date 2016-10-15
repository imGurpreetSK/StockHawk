package com.sam_chordas.android.stockhawk.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;

public class DetailFragment extends Fragment {

    Bundle data;
    String bidPrice, change, companySymbol, percentChange;
    int currentValue, isUp;
    TextView temp;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);
//        temp = (TextView) v.findViewById(R.id.detail_textview);
//        currentValue = data.getInt(QuoteColumns.ISCURRENT);
        isUp = data.getInt(QuoteColumns.ISUP);
        bidPrice = data.getString(QuoteColumns.BIDPRICE);
        change = data.getString(QuoteColumns.CHANGE);
        companySymbol = data.getString(QuoteColumns.SYMBOL);
        percentChange = data.getString(QuoteColumns.PERCENT_CHANGE);

//        temp.setText(companySymbol);

        return v;

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
