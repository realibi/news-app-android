package kz.realibi.news.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import kz.realibi.news.MainActivity;
import kz.realibi.news.R;

public class NewNewsFragment extends Fragment {
    private static final String TAG = "NewNewsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_news, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button addNewsBtn = view.findViewById(R.id.addNewsBtn);

        if(addNewsBtn != null){
            addNewsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText titleEditText = (EditText)view.findViewById(R.id.titleEditText);
                    EditText contentEditText = (EditText)view.findViewById(R.id.contentEditText);

                    String title = titleEditText.getText().toString();
                    String content = contentEditText.getText().toString();

                    if(titleEditText.length() > 0 || contentEditText.length() > 0){
                        titleEditText.setText("");
                        contentEditText.setText("");

                        Toast.makeText(view.getContext(), "Article added!", Toast.LENGTH_SHORT).show();

                        Map<String, Object> article = new HashMap<>();
                        article.put("title", title);
                        article.put("content", content);
                        article.put("likes", 0);

                        FirebaseFirestore.getInstance().collection("news").add(article).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
                    }else{
                        Toast.makeText(getContext(), "Заполните заголовок и контент!", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }


    }
}
