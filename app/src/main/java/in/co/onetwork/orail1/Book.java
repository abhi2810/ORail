package in.co.onetwork.orail1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class Book extends AppCompatActivity {
    EditText ed1,ed2;
    String coach[]={"Sleeper","3AC","2AC","1AC"};
    MaterialBetterSpinner train,type;
    String to,from,tra,ty;
    ArrayList<String> t;
    DatabaseReference tr= FirebaseDatabase.getInstance().getReference("train");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().setTitle("Book");
        train=(MaterialBetterSpinner)findViewById(R.id.train);
        type=(MaterialBetterSpinner)findViewById(R.id.type);
        ed1=(EditText)findViewById(R.id.from);
        ed2=(EditText)findViewById(R.id.to);
        ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,coach);
        type.setAdapter(a);
        t=new ArrayList<>();
        tr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    t.add(dataSnapshot1.getKey());
                }
                ArrayAdapter<String> ar=new ArrayAdapter<String>(Book.this,android.R.layout.simple_list_item_1,t);
                train.setAdapter(ar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ArrayAdapter<String> ar=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,t);
        train.setAdapter(ar);
    }
    public void next(View v){
        from=ed1.getText().toString();
        to=ed2.getText().toString();
        tra=train.getText().toString();
        ty=type.getText().toString();
        if(from.equals("")||to.equals("")){
            Toast.makeText(this, "Enter to and from", Toast.LENGTH_SHORT).show();
        }else{
            Intent i=new Intent(this,EnterDetails.class);
            i.putExtra("train",tra);
            i.putExtra("type",ty);
            i.putExtra("from",from);
            i.putExtra("to",to);
            startActivity(i);
            finish();
        }
    }
}
