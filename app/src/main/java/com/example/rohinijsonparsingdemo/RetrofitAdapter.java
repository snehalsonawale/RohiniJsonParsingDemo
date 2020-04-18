package com.example.rohinijsonparsingdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.RetrofitHolder> {

    List<Network.TeDatum> list;

    Context context;

    DatabaseHelper myDb;

    public RetrofitAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }
    @NonNull
    @Override
    public RetrofitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myDb = new DatabaseHelper(context);

        View view = LayoutInflater.from(context).inflate(R.layout.card_row,parent,false);

        return new RetrofitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitHolder viewHolder, int position) {
        final Network.TeDatum myListData = list.get(position);
        String url =   list.get(position).getFname();



        viewHolder.tv_fname.setText(list.get(position).getFname());
        viewHolder.tv_lname.setText(list.get(position).getLname());
        viewHolder.tv_subjectname.setText(list.get(position).getSubjectName());
        viewHolder.tv_subsubjectname.setText(list.get(position).getSubSubjectName());
        viewHolder.tv_subjectid.setText(String.valueOf( list.get(position).getSubjectId()) );
        viewHolder.tv_subsubjectid.setText( String.valueOf( list.get(position).getSubSubjectId()));
        viewHolder.tv_tid.setText( String.valueOf(list.get(position).getTid()));

        viewHolder.tv_subjectname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(),"click on item: "+myListData.getFname()+"\n"+myListData.getSubjectName()+"\n"+myListData.getSubjectId(),
                        Toast.LENGTH_LONG).show();



              /*  boolean isInserted = myDb.insertData(myListData.getFname(),
                        myListData.getSubjectName(),
                        myListData.getSubSubjectName(),myListData.getSubjectId(),myListData.getSubSubjectId() );

                if (isInserted==true)
                {
                    Toast.makeText(context,"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context,"Data not Inserted",Toast.LENGTH_LONG).show();

                }*/

            }
        });

        viewHolder.tv_tid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("subject name :"+ res.getString(2)+"\n");
                    buffer.append("subsubject name :"+ res.getString(3)+"\n\n");
                    buffer.append("Subject id :"+ res.getString(4)+"\n\n");
                    buffer.append("Sub Subject id :"+ res.getString(5)+"\n\n");
                }
                // Show all data
                showMessage("Data",buffer.toString());


            }
        });

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



    @Override
    public int getItemCount() {
       return list.size();
    }

    public class RetrofitHolder extends RecyclerView.ViewHolder {
        private TextView tv_name,tv_version,tv_api_level;
        private  TextView tv_fname,tv_lname,tv_subjectname,tv_subsubjectname,tv_subjectid,tv_subsubjectid,tv_tid;


        public RetrofitHolder(@NonNull View view) {
            super(view);


            tv_fname = (TextView)view.findViewById(R.id.tv_fname);
            tv_lname = (TextView)view.findViewById(R.id.tv_lname);
            tv_subjectname = (TextView)view.findViewById(R.id.tv_subjectname);
            tv_subsubjectname = (TextView)view.findViewById(R.id.tv_subsubjectname);
            tv_subjectid = (TextView)view.findViewById(R.id.tv_subjectid);
            tv_subsubjectid = (TextView)view.findViewById(R.id.tv_subsubjectid);
            tv_tid = (TextView)view.findViewById(R.id.tv_tid);
        }
    }
    public void addImage(List<Network.TeDatum> data){

        list.addAll(data);
        notifyDataSetChanged();
    }
}
