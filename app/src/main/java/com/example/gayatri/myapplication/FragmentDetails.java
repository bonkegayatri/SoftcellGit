package com.example.gayatri.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mahesh on 2/1/18.
 */

public class FragmentDetails extends android.support.v4.app.Fragment {


    private ImageView mImageView;
    private TextView mtxt_First_Name,mtxt_Last_Name;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.lay_fragment_details,null);
        mImageView=view.findViewById(R.id.detailsImageView);
        mtxt_First_Name=view.findViewById(R.id.txt_first_name);
        mtxt_Last_Name=view.findViewById(R.id.txt_last_name);

        Bundle bundle=getArguments();
        PojoClass pojoClass= (PojoClass) bundle.getSerializable("Data");


        mtxt_Last_Name.setText(pojoClass.getLast_name());
        mtxt_First_Name.setText(pojoClass.getFirst_name());
        Picasso.with(getActivity()).load(pojoClass.getAvatar()).into(mImageView);


        return view;
    }
}
