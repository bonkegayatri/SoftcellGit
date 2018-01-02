package com.example.gayatri.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gayatri on 2/1/18.
 */
public class Fragment extends android.support.v4.app.Fragment {


    RecyclerView recyclerView;
    ArrayList<PojoClass> mList;
    AdapterPage adapterPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.lay_fragment,null);

        setHasOptionsMenu( true );

        recyclerView = view.findViewById( R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext(),LinearLayoutManager.VERTICAL,false));


        mList = new ArrayList<>();
        adapterPage = new AdapterPage( mList );
        recyclerView.setAdapter( adapterPage );


        String url = "https://reqres.in/api/users?page=2";
        RequestQueue requestQueue = Volley.newRequestQueue( getContext() );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("tag", "onResponse: "+ response );
                        try {
                            JSONArray jsonArray = response.getJSONArray( "data" );

                            for ( int i =0;i< jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject( i );

                                PojoClass p = new PojoClass();

                                p.id = jsonObject.getString("id");
                                p.first_name = jsonObject.getString("first_name");
                                p.last_name = jsonObject.getString("last_name");
                                p.avatar = jsonObject.getString("avatar");

                               // PojoClass pogoClass = new PojoClass(avtar,id,first_name,last_name);

                                mList.add( p );

                            }

                            adapterPage.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("tag",error+ " ");
                    }
                });

        requestQueue.add( jsonObjectRequest );
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate( R.menu.add_menu,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.additem)
        {
            Toast.makeText( getContext(), "SoftCell Technologies", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
