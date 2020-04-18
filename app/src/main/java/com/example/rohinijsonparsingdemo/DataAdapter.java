package com.example.rohinijsonparsingdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android;

    public DataAdapter(ArrayList<AndroidVersion> android) {
        this.android = android;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder, int i) {
        final AndroidVersion myListData = android.get(i);
        viewHolder.tv_name.setText(android.get(i).getName());
        viewHolder.tv_version.setText(android.get(i).getVer());
        viewHolder.tv_api_level.setText(android.get(i).getApi());
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(),"click on item: "+myListData.getVer(), Toast.LENGTH_LONG).show();
              //  Toast.makeText(view.getContext(),"click on item: "+myListData.getVer(), Toast.LENGTH_LONG).show();
               // Toast.makeText(view.getContext(),"click on item: "+viewHolder.tv_name.getText(), Toast.LENGTH_LONG).show();

                 Toast.makeText(view.getContext(),"click on item: "+myListData.getApi(), Toast.LENGTH_LONG).show();

            }
        });






    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

           // tv_name = (TextView)view.findViewById(R.id.tv_name);
            //tv_version = (TextView)view.findViewById(R.id.tv_version);
           // tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }

}

