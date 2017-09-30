package in.co.onetwork.orail;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EnterDetails extends AppCompatActivity {
    EditText n1,a1,ad1,n2,a2,ad2,n3,a3,ad3;
    Button date;
    int k=0;
    String uid,train,type,from,to;
    long seats=0;
    int mYear, mMonth, mDay;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference ticket= FirebaseDatabase.getInstance().getReference("ticket");
    DatabaseReference trains=FirebaseDatabase.getInstance().getReference("train");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        getSupportActionBar().setTitle("Enter Details");
        n1=(EditText)findViewById(R.id.name1);
        a1=(EditText)findViewById(R.id.age1);
        ad1=(EditText)findViewById(R.id.adhar1);
        n2=(EditText)findViewById(R.id.name2);
        a2=(EditText)findViewById(R.id.age2);
        ad2=(EditText)findViewById(R.id.adhar2);
        n3=(EditText)findViewById(R.id.name3);
        a3=(EditText)findViewById(R.id.age3);
        ad3=(EditText)findViewById(R.id.adhar3);
        date=(Button)findViewById(R.id.date);
        uid=auth.getCurrentUser().getUid();
        train=getIntent().getStringExtra("train");
        type=getIntent().getStringExtra("type");
        from=getIntent().getStringExtra("from");
        to=getIntent().getStringExtra("to");
        trains.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                seats=dataSnapshot.child(train).child(type).getValue(Long.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void btickets(View v){
        //if(seats<67) {
            Passengers p1 = new Passengers();
            Passengers p2 = new Passengers();
            Passengers p3 = new Passengers();
            if (!n1.getText().toString().equals("")) {
                p1.setName(n1.getText().toString());
                p1.setAge(a1.getText().toString());
                p1.setUid(ad1.getText().toString());
                p1.setSeat(seats + "");
                seats++;
            }
            if (!n2.getText().toString().equals("")) {
                p2.setName(n2.getText().toString());
                p2.setAge(a2.getText().toString());
                p2.setUid(ad2.getText().toString());
                p2.setSeat(seats + "");
                seats++;
            }
            if (!n3.getText().toString().equals("")) {
                p3.setName(n3.getText().toString());
                p3.setAge(a3.getText().toString());
                p3.setUid(ad3.getText().toString());
                p3.setSeat(seats + "");
                seats++;
            }
            if (k != 0){
                ticket.child(uid).child("from").setValue(from);
            ticket.child(uid).child("to").setValue(to);
            ticket.child(uid).child("train").setValue(train);
            ticket.child(uid).child("type").setValue(type);
            ticket.child(uid).child("date").setValue(date.getText().toString());
            ticket.child(uid).child("p1").setValue(p1);
            ticket.child(uid).child("p2").setValue(p2);
            ticket.child(uid).child("p3").setValue(p3);
            trains.child(train).child(type).setValue(seats);
            Toast.makeText(this, "Ticket Booked", Toast.LENGTH_SHORT).show();
            finish();
            }else {
                Toast.makeText(this, "Please select a date.", Toast.LENGTH_SHORT).show();
            }
        /*}else{
            Toast.makeText(this, "Tickets are full.", Toast.LENGTH_SHORT).show();
        }*/
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pick(View v){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        k=1;

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
