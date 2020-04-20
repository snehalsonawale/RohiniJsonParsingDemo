package com.example.rohinijsonparsingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<Network.TeDatum> data;
    Button btnview;
   // String url="https://api.learn2crack.com/";
    RetrofitAdapter adapter;
    Spinner spinner;

    // declear it in global scope
    ArrayList<Building> buildingList=  new ArrayList <> ();
    DatabaseHelper myDb;
    String name;
    String subjectid;
    String selectedName;

/////////////////// note///////// before run edit your api code in  //////////////////////////////////////////////////


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(MainActivity.this);
       // initViews();
        loadJSON();

        spinner=(Spinner)findViewById(R.id.country_Name);
        btnview=(Button) findViewById(R.id.bgtnview);
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Cursor res = myDb.getAllData();
                Cursor res = myDb.readata(subjectid);

                /* error while compiling: SELECT * FROM student_table WHERE PKQ1.181030.001 = 1*/
                /*while compiling: SELECT * FROM student_table WHERE SUBJECTID = SURGERY AND ALLIED
                * (1) no such column: SURGERY
                *
                * */


                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }


                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                   // buffer.append("Id :"+ res.getString(0)+"\n");
                   buffer.append("subject name :"+ res.getString(1)+"\n");
                 buffer.append("subject id :"+ res.getString(2)+"\n");
                   // buffer.append("subsubject name :"+ res.getString(3)+"\n\n");
                  //  buffer.append("Subject id :"+ res.getString(4)+"\n\n");
                   // buffer.append("Sub Subject id :"+ res.getString(5)+"\n\n");
                }
                // Show all data
                showMessage("Data",buffer.toString());

            }
        });




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedName = parent.getItemAtPosition(position).toString();


                // here you can retrive you building info by positon
                Building building = buildingList.get(position);

                 subjectid = building.getId();
                String name = building.getName();

                // Now you can use your id or name to do whatever you want.
               System.out.println (subjectid);
                System.out.println (name);

                Toast.makeText(MainActivity.this, "Subject Name and Id " + name+"\n"+subjectid, Toast.LENGTH_SHORT).show();

                   boolean isInserted = myDb.insertData(building.getName(),
                           building.getId());

                if (isInserted==true)
                {
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void readdta(){


      //  recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new RetrofitAdapter(MainActivity.this);
      //  recyclerView.setAdapter(adapter);
       // loadJSON();



    }
    private void loadJSON(){

        RetrofitApi Retrofitapi = RetrofitData.getRetrofit().create(RetrofitApi.class);
        Call<Network> call = Retrofitapi.getImageData();

        call.enqueue(new Callback<Network>() {
            @Override
            public void onResponse(Call<Network> call, Response<Network> response) {

                  Log.e("RESPONCR Boydy",""+response.body());
               // ArrayList<Network.TeDatum> list = (ArrayList<Network.TeDatum>) response.body().getTeData();

                 ArrayList  arrayList = new ArrayList <> ();
                 List<Network.TeDatum> semuadosenItems = response.body().getTeData();
                for (int i = 0; i < semuadosenItems.size(); i++){


                   /* if (semuadosenItems.get (i).getSubjectId () != null && !semuadosenItems.get (i).getSubjectId ().isEmpty()) {
                        String id=semuadosenItems.get (i).getSubjectId ();
                         name =semuadosenItems.get (i).getSubjectName ();
                        Building building = new Building(id, name);
                        buildingList.add(building);

                    }
*/
                 //   arrayList.add(name);

                   String id=semuadosenItems.get (i).getSubjectId ();
                    String name =semuadosenItems.get (i).getSubjectName ();



                    Building building = new Building(id, name);
                    buildingList.add(building);
                    arrayList.add(name);

                  //  listSpinner.add(semuadosenItems.get(i).getSubjectName());

                }



                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_item, arrayList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);


                //Log.e("RESPONCR Boydy",""+response.body());
                  //ArrayList<Network.TeDatum> list = (ArrayList<Network.TeDatum>) response.body().getTeData();
                 //System.out.println("Responce body"+response.body().getTeData());
               //  System.out.println("Responce List"+list);
                // Log.e("RESPONCR PRINT",""+list);
                // adapter.addImage(list);
            }



            @Override
            public void onFailure(Call<Network> call, Throwable t) {
                Log.d("tag","Error -> " +t.toString());
            }
        });












       /* call.enqueue(new Callback<Network>() {
            @Override
            public void onResponse(Call<Network> call, Response<Network> response) {

               // Log.e("RESPONCR Boydy",""+response.body());

                Network.TeDatum teDatum = new Network.TeDatum();

                 ArrayList<Network.TeDatum> list = (ArrayList<Network.TeDatum>) response.body().getTeData();
                 System.out.println("Responce body"+response.body().getTeData());

                 for (int i=0;i<=list.size();i++)
                 {


                 }




                //Log.e("RESPONCR Boydy",""+response.body());
              //  ArrayList<Network.TeDatum> list = (ArrayList<Network.TeDatum>) response.body().getTeData();
               // System.out.println("Responce body"+response.body().getTeData());
               // System.out.println("Responce List"+list);
               // Log.e("RESPONCR PRINT",""+list);
               // adapter.addImage(list);
            }

            @Override
            public void onFailure(Call<Network> call, Throwable t) {
                Log.d("tag","Error -> " +t.toString());
            }
        });*/



    }

    }









       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<ResponceObject> call = request.getJSON();
        call.enqueue(new Callback<ResponceObject>() {
            @Override
            public void onResponse(Call<ResponceObject> call, Response<ResponceObject> response) {
                System.out.println("Responce"+response.body());
                Log.e("RESPONCR PRINT",""+response.body());



                Toast.makeText(getApplicationContext(),"click on item: "+response.body(), Toast.LENGTH_LONG).show();


               // JSONResponse jsonResponse = response.body();
                //data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                //adapter = new DataAdapter(data);
              //  recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponceObject> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });*/


