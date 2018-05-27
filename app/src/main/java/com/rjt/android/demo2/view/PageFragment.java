package com.rjt.android.demo2.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjt.android.demo2.R;
import com.rjt.android.demo2.api.DummyApi;
import com.rjt.android.demo2.api.RetrofitInstance;
import com.rjt.android.demo2.pojo.Example;
import com.rjt.android.demo2.pojo.Result;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PageFragment extends Fragment {
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";
    private List<Result> resultList;
    MainAdapter mainAdapter;
    RecyclerView recyclerView;
    public static PageFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }

    public void makeGirlRequest(){
        Retrofit retrofit = RetrofitInstance.getRetrofitIns();
        retrofit.create(DummyApi.class).getExample("福利", 40, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Example>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("onSubscribe", "Subscribed");
                    }

                    @Override
                    public void onNext(Example example) {
                        resultList = example.getResults();
                        Log.d("onNext", resultList.size()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        MainAdapter mainAdapter = new MainAdapter(resultList, getActivity());
                        recyclerView.setAdapter(mainAdapter);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = view.findViewById(R.id.frag_recyclerview);
        makeGirlRequest();
        return view;
    }
}


