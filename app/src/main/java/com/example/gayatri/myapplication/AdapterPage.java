package com.example.gayatri.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gayatri on 2/1/18.
 */
public class AdapterPage extends RecyclerView.Adapter<AdapterPage.HolderPage> {

    ArrayList<PojoClass> mArraylist;


    public AdapterPage(ArrayList<PojoClass> mArraylist) {
        this.mArraylist = mArraylist;
    }

    @Override
    public HolderPage onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.lay_adapter,null);
        return new HolderPage( view );
    }

    @Override
    public void onBindViewHolder(HolderPage holder, int position) {

        PojoClass pojoClass = mArraylist.get( position );

        holder.txtId.setText("Id :"+ pojoClass.getId());
        holder.txtFirstName.setText("First Name :"+ pojoClass.getFirst_name());
        holder.txtLastName.setText("Last Name :"+ pojoClass.getLast_name());


        Picasso.with( holder.itemView.getContext()).load( pojoClass.avatar).into( holder.img);


    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    public class HolderPage extends RecyclerView.ViewHolder {

        TextView txtId , txtFirstName,txtLastName;
        ImageView img;

        public HolderPage(View itemView) {
            super(itemView);

            txtId = itemView.findViewById( R.id.txtId);
            txtFirstName = itemView.findViewById( R.id.txtFirstName);
            txtLastName = itemView.findViewById( R.id.txtLastName );
            img = itemView.findViewById( R.id.avatarImg);

        }
    }
}
