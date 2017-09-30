package in.co.onetwork.orail;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewTicket extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference ticket= FirebaseDatabase.getInstance().getReference("ticket");
    TextView train,date,from,to,type,name1,name2,name3,age1,age2,age3,seat1,seat2,seat3;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);
        getSupportActionBar().setTitle("Your Ticket");
        train=(TextView)findViewById(R.id.train);
        date=(TextView)findViewById(R.id.date);
        from=(TextView)findViewById(R.id.from);
        to=(TextView)findViewById(R.id.to);
        type=(TextView)findViewById(R.id.type);
        name1=(TextView)findViewById(R.id.name1);
        age1=(TextView)findViewById(R.id.age1);
        seat1=(TextView)findViewById(R.id.seat1);
        name2=(TextView)findViewById(R.id.name2);
        age2=(TextView)findViewById(R.id.age2);
        seat2=(TextView)findViewById(R.id.seat2);
        name3=(TextView)findViewById(R.id.name3);
        age3=(TextView)findViewById(R.id.age3);
        seat3=(TextView)findViewById(R.id.seat3);
        uid=auth.getCurrentUser().getUid();
        ticket.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                train.setText(dataSnapshot.child("train").getValue(String.class));
                from.setText(dataSnapshot.child("from").getValue(String.class));
                to.setText(dataSnapshot.child("to").getValue(String.class));
                date.setText(dataSnapshot.child("date").getValue(String.class));
                type.setText(dataSnapshot.child("type").getValue(String.class));
                if(dataSnapshot.hasChild("p1")){
                    name1.setText(dataSnapshot.child("p1").child("name").getValue(String.class));
                    age1.setText(dataSnapshot.child("p1").child("age").getValue(String.class));
                    seat1.setText(dataSnapshot.child("p1").child("seat").getValue(String.class));
                }
                if(dataSnapshot.hasChild("p2")){
                    name2.setText(dataSnapshot.child("p2").child("name").getValue(String.class));
                    age2.setText(dataSnapshot.child("p2").child("age").getValue(String.class));
                    seat2.setText(dataSnapshot.child("p2").child("seat").getValue(String.class));
                }
                if(dataSnapshot.hasChild("p3")){
                    name3.setText(dataSnapshot.child("p3").child("name").getValue(String.class));
                    age3.setText(dataSnapshot.child("p3").child("age").getValue(String.class));
                    seat3.setText(dataSnapshot.child("p3").child("seat").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void delete(View v){
        AlertDialog.Builder dial = new AlertDialog.Builder(this);
        dial.setTitle("Do you want to Cancel the ticket?");
        dial.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(ViewTicket.this, "Ticket deleted!", Toast.LENGTH_SHORT).show();
                        ticket.child(uid).setValue(null);
                        finish();
                    }
                });
        dial.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDial = dial.create();
        alertDial.show();
    }
}
