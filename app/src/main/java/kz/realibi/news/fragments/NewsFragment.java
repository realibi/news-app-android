package kz.realibi.news.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import kz.realibi.news.R;
import kz.realibi.news.adapters.NewsAdapter;
import kz.realibi.news.entities.News;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<News> mNews = new ArrayList<>();

    private NewsAdapter newsAdapter;
    private final String TAG = "NewsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("news")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<News> newsFromDb = task.getResult().toObjects(News.class);
                            mNews.addAll(newsFromDb);

                            newsAdapter = new NewsAdapter(mNews);
                            recyclerView.setAdapter(newsAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
