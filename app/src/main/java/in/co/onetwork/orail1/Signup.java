package in.co.onetwork.orail1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference merchants= FirebaseDatabase.getInstance().getReference().child("Users").getRef();
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email,password,name,age,address;
    EditText ed1,ed2,ed3,ed4,ed5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Sign Up");
        setContentView(R.layout.activity_signup);
        ed1=(EditText) findViewById(R.id.mName);
        ed2=(EditText) findViewById(R.id.age);
        ed3=(EditText) findViewById(R.id.add);
        ed4=(EditText)findViewById(R.id.mobn);
        ed5=(EditText)findViewById(R.id.pass);
        mAuthListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(Signup.this, "Signed in", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Signup.this, "Signed out", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    public void createAccount(View v) {
        String cpass = ((EditText) findViewById(R.id.cpass)).getText().toString();
        name = ed1.getText().toString();
        age = ed2.getText().toString();
        address = ed3.getText().toString();
        email = ed4.getText().toString().trim();
        password = ed5.getText().toString();
        if (name.equals("") || age.equals("") || address.equals("") || email.equals("") || password.equals("") || cpass.equals("")) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            if (password.equals(cpass)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Failed Signing up"+task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    String Uid=task.getResult().getUser().getUid();
                                    User m=new User(Uid,name,age,address,email);
                                    merchants.child(Uid).setValue(m);
                                    Toast.makeText(Signup.this, "Signed up Successfully.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }else{
                Toast.makeText(this, "Passwords don't match.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
