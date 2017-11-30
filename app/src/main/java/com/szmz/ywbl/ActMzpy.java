package com.szmz.ywbl;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.jph.takephoto.model.TResult;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.MyNewPhoto;
import com.szmz.entity.YwblDzdaSalvation;
import com.szmz.entity.request.JZ_YWBL_ADDDATA_RE;
import com.szmz.entity.request.JZ_YWBL_MZPY_RE;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.FileUtil;
import com.szmz.utils.ImageUtil;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.GridViewInScrollView;
import com.szmz.widget.ImageGridAdapter;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 民主评议
 */
public class ActMzpy extends ActLocationBase {


    @BindView(R.id.pysjTv)
    TextView pysjTv;
    @BindView(R.id.fzrTv)
    EditText fzrTv;
    @BindView(R.id.jlrTv)
    EditText jlrTv;
    @BindView(R.id.ed_bz)
    ClearEditText edBz;
    @BindView(R.id.mygridview)
    GridViewInScrollView mygridview;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.img)
    PhotoView img;
    @BindView(R.id.parent)
    FrameLayout parent;
    @BindView(R.id.sqrTv)
    TextView sqrTv;
    @BindView(R.id.sqrLayout)
    LinearLayout sqrLayout;
    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;
    @BindView(R.id.pyjlEd)
    EditText pyjlEd;
    private ImageGridAdapter adapter;
    private List<MyNewPhoto> paths = new ArrayList<MyNewPhoto>();
    private List<MyNewPhoto> path = new ArrayList<MyNewPhoto>();
    private String imagePath;
    private double lat1 = 0.0;
    private double lng1 = 0.0;
    private String waterword = "";
    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);
    Info mInfo;
    private TimePickerView pvTime;
    private List<YwblDzdaSalvation> listSalvation;

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("民主评议");
        if (isOnline) {
            setRightShow("提交");
            setRightVisible(true);
        } else {
            setRightShow("保存");
            setRightVisible(true);
        }
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline) {
                    doSubmit();
                } else {
                    doToast("保存成功");
                    finish();
                }

            }
        });
        in.setDuration(300);
        out.setDuration(300);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        paths.add(new MyNewPhoto("TakePhoto"));
        adapter = new ImageGridAdapter(this, R.layout.grid_image_item,
                9, true, new ImageGridAdapter.OnDeleteListener() {
            @Override
            public void doDelete(int p) {
                path.remove(p);
                paths.remove(p);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setImgPaths(paths);
        mygridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int p, long arg3) {
                ListAdapter adapter = (ListAdapter) av.getAdapter();
                MyNewPhoto item = (MyNewPhoto) adapter.getItem(p);
                if (!"TakePhoto".equals(item.getFileUrl())) {
                    PhotoView photoView = (PhotoView) v.findViewById(R.id.img_item);
                    mInfo = photoView.getInfo();
                    x.image().bind(img, item.getFileUrl());
                    bg.startAnimation(in);
                    bg.setVisibility(View.VISIBLE);
                    parent.setVisibility(View.VISIBLE);
                    img.animaFrom(mInfo);
                } else {
                    takePhoto();
                }
            }
        });

        img.enable();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.startAnimation(out);
                img.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        parent.setVisibility(View.GONE);
                    }
                });
            }
        });
        initTimePicker();
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(pysjTv);
            }
        });
        sqrLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActMzpy.this, ActYwbl_dzda_person.class);
                intent.putExtra("YwblDzdaSalvations", (Serializable) listSalvation);
                intent.putExtra("Type", 1);
                intent.putExtra("isMore", true);
                intent.putExtra("isChose", true);
                startActivityForResult(intent, 1110);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_mzpy;
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        imagePath = result.getImage().getOriginalPath();
        if (FileUtil.isExist(imagePath)) {
            path.add(new MyNewPhoto(imagePath));
            paths.clear();
            paths.addAll(path);
            paths.add(new MyNewPhoto("TakePhoto"));
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onBackPressed() {
        if (parent.getVisibility() == View.VISIBLE) {
            bg.startAnimation(out);
            img.animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    parent.setVisibility(View.GONE);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1110 && resultCode == 1111) {
            if (data != null) {
                listSalvation = (List<YwblDzdaSalvation>) data.getSerializableExtra("YwblDzdaSalvations");
                if (listSalvation != null && listSalvation.size() > 0) {
                    sqrTv.setText(getNames(listSalvation));
                }
            }
        }

    }

    private String getNames(List<YwblDzdaSalvation> list) {
        String id = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                id = id + list.get(i).getName();
            } else {
                id = id + list.get(i).getName() + ",";
            }


        }
        return id;

    }

    private String getIDS(List<YwblDzdaSalvation> list) {
        String id = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                id = id + list.get(i).getFamilyId();
            } else {
                id = id + list.get(i).getFamilyId() + ",";
            }

        }
        return id;

    }

    private void doSubmit() {
        if (listSalvation == null || listSalvation.size() < 1) {
            doToast("请选择申请人!");
            return;
        }
        String timeStr = pysjTv.getText().toString().trim();
        if (TextUtils.isEmpty(timeStr)) {
            doToast("民主评议时间不能为空!");
            return;
        }
        String fzr = fzrTv.getText().toString().trim();
        if (TextUtils.isEmpty(fzr)) {
            doToast("请填写负责人!");
            return;
        }
        String jlr = jlrTv.getText().toString().trim();
        if (TextUtils.isEmpty(jlr)) {
            doToast("请填写记录人!");
            return;
        }
        String pyjl = pyjlEd.getText().toString().trim();
        if (TextUtils.isEmpty(pyjl)) {
            doToast("请输入民主评议结论!");
            return;
        }

        if (location == null) {
            doToast("未获取到地址信息!");
            return;
        }

        JZ_YWBL_MZPY_RE request = new JZ_YWBL_MZPY_RE(getIDS(listSalvation), timeStr, pyjl, fzr, jlr, location.getAddrStr());
        Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddStreetComment(request);
        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
            @Override
            public void doSuccess(CommResponse result) {

                for (MyNewPhoto photo : path) {
                    doUpLoadImage(getIDS(listSalvation), ImageUtil.getThumbImagePath(photo.getFileUrl(),null), location.getAddrStr());
                }
                doToast("上报成功!");
                finish();

            }


        }, true);

        apiUtil.excute();


    }

    private void doUpLoadImage(String familyId, String url, String address) {
        JZ_YWBL_ADDDATA_RE request = new JZ_YWBL_ADDDATA_RE(familyId, "20203029", ImageUtil.pictureToBase64(url), address);
        Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddData(request);
        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
                System.out.print("照片上传成功!");
            }
        }, false);
        apiUtil.excute();
    }


}
