package com.szmz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;


public abstract  class BaseFragment extends Fragment {

    protected MaterialDialog dialog;

    protected abstract int getLayoutId();

    protected abstract void bindDatas();

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            TextView textView = new TextView(getActivity());
            return textView;
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new MaterialDialog.Builder(getContext()).
                content("请稍后···").
                progress(true, 100)
                .cancelable(false)
                .canceledOnTouchOutside(false).build();

        bindDates(view);
        bindDatas();
    }
    protected void bindDates(View v) {


    }

    public void trans(Class cls){
        Intent intent =new Intent(getContext(),cls);
        startActivity(intent);
    }

    public void trans(Class cls,String title,String content){
        Intent intent =new Intent(getContext(),cls);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        startActivity(intent);
    }

    public String getStringByUI(View view) {

        if (view instanceof EditText) {

            return ((EditText) view).getText().toString().trim();
        } else if (view instanceof TextView) {
            return ((TextView) view).getText().toString().trim();
        }
        return "";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
