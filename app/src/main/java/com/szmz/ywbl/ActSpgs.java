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
import com.szmz.entity.request.JZ_YWBL_SPGS_RE;
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
 * 审批公示
 */
public class ActSpgs extends ActLocationBase {


    @BindView(R.id.ed_wxms)
    ClearEditText edWxms;
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
    @BindView(R.id.timeStartTv)
    TextView timeStartTv;
    @BindView(R.id.timeStartLayout)
    LinearLayout timeStartLayout;
    @BindView(R.id.timeEndTv)
    TextView timeEndTv;
    @BindView(R.id.timeEndLayout)
    LinearLayout timeEndLayout;
    @BindView(R.id.gsjlrEd)
    EditText gsjlrEd;
    @BindView(R.id.gsjgEd)
    EditText gsjgEd;
    @BindView(R.id.gsyynrEd)
    EditText gsyynrEd;
    @BindView(R.id.spjdrqTv)
    TextView spjdrqTv;
    @BindView(R.id.spryEd)
    EditText spryEd;
    @BindView(R.id.spfzrEd)
    EditText spfzrEd;
    @BindView(R.id.spjdrqLayout)
    LinearLayout spjdrqLayout;
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
        setTitle("审批公示");
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
        timeStartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(timeStartTv);
            }
        });
        timeEndLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(timeEndTv);
            }
        });
        spjdrqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(spjdrqTv);
            }
        });
        sqrLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActSpgs.this, ActYwbl_dzda_person.class);
                intent.putExtra("YwblDzdaSalvations", (Serializable) listSalvation);
                intent.putExtra("Type", 4);
                intent.putExtra("isMore", true);
                intent.putExtra("isChose", true);
                startActivityForResult(intent, 1440);
            }
        });
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
    protected int getLayoutId() {
        return R.layout.activity_act_spgs;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1440 && resultCode == 1441) {
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
        String timeStartStr = timeStartTv.getText().toString().trim();
        if (TextUtils.isEmpty(timeStartStr)) {
            doToast("公示起始日期不能为空!");
            return;
        }
        String timeEndStr = timeEndTv.getText().toString().trim();
        if (TextUtils.isEmpty(timeEndStr)) {
            doToast("公示截止日期不能为空!");
            return;
        }


        String jlr = gsjlrEd.getText().toString().trim();
        if (TextUtils.isEmpty(jlr)) {
            doToast("请填写公示记录人!");
            return;
        }
        String spjdrq = spjdrqTv.getText().toString().trim();
        if (TextUtils.isEmpty(timeEndStr)) {
            doToast("审批决定日期不能为空!");
            return;
        }
        String spry = spryEd.getText().toString().trim();
        if (TextUtils.isEmpty(timeEndStr)) {
            doToast("请输入审批人员!");
            return;
        }
        String spfzr = spfzrEd.getText().toString().trim();
        if (TextUtils.isEmpty(timeEndStr)) {
            doToast("请输入审批负责人!");
            return;
        }
        String gsjg = gsjgEd.getText().toString().trim();
        if (TextUtils.isEmpty(gsjg)) {
            doToast("请输入公示结果!");
            return;
        }
        String gsyynr = gsyynrEd.getText().toString().trim();
        if (TextUtils.isEmpty(gsyynr)) {
            doToast("请输入异议内容!");
            return;
        }

        if (location == null) {
            doToast("未获取到地址信息!");
            return;
        }

        JZ_YWBL_SPGS_RE request = new JZ_YWBL_SPGS_RE(getIDS(listSalvation), timeStartStr, timeEndStr, gsjg, jlr, gsyynr, location.getAddrStr(), spjdrq, spry, spfzr);
        Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddCountyPublic(request);
        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
            @Override
            public void doSuccess(CommResponse result) {

                for (MyNewPhoto photo : path) {
                    doUpLoadImage(getIDS(listSalvation), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), location.getAddrStr());
                }
                doToast("上报成功!");
                finish();

            }


        }, true);

        apiUtil.excute();


    }

    private void doUpLoadImage(String familyId, String url, String address) {
        JZ_YWBL_ADDDATA_RE request = new JZ_YWBL_ADDDATA_RE(familyId, "20203032", ImageUtil.pictureToBase64(url), address);
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
