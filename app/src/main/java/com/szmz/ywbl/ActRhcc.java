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

import com.baidu.location.BDLocation;
import com.bigkoo.pickerview.TimePickerView;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jph.takephoto.model.TResult;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.MyNewPhoto;
import com.szmz.entity.YwblDzdaSalvation;
import com.szmz.entity.YwblSaveDataRequest;
import com.szmz.entity.request.JZ_YWBL_ADDDATA_RE;
import com.szmz.entity.request.JZ_YWBL_RHCC_RE;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.FileUtil;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.ImageUtil;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.GridViewInScrollView;
import com.szmz.widget.ImageGridAdapter;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import org.xutils.ex.DbException;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 入户抽查
 */
public class ActRhcc extends ActLocationBase {

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
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.addressLayout)
    LinearLayout addressLayout;
    @BindView(R.id.refreshAddressLayout)
    LinearLayout refreshAddressLayout;
    @BindView(R.id.sqrTv)
    TextView sqrTv;
    @BindView(R.id.sqrLayout)
    LinearLayout sqrLayout;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;
    @BindView(R.id.dcrTv)
    EditText dcrTv;
    @BindView(R.id.dcrLayout)
    LinearLayout dcrLayout;
    @BindView(R.id.fzrTv)
    EditText fzrTv;
    @BindView(R.id.fzrLayout)
    LinearLayout fzrLayout;
    @BindView(R.id.rhccjgEd)
    EditText rhccjgEd;
    @BindView(R.id.timeTv)
    TextView timeTv;
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
    private YwblDzdaSalvation checkSalvation;
    private TimePickerView pvTime;

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_rhcc;
    }

    @Override
    protected void initUI() {
        super.initUI();
        initTimePicker();
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(time);
            }
        });
        sqrLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActRhcc.this, ActYwbl_dzda_person.class);
                intent.putExtra("YwblDzdaSalvation", checkSalvation);
                intent.putExtra("Type", 3);
                intent.putExtra("isMore", false);
                intent.putExtra("isChose", true);
                startActivityForResult(intent, 1330);
            }
        });
        setLeftVisible(true);
        setTitle("入户抽查");
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
                    doSubmit(false);
                } else {
                    doSubmit(true);
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
        timeTv.setText(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
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
        refreshAddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTv.setText(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
                refreshLocation();
            }
        });

        YwblSaveDataRequest ywblSaveDataRequest = (YwblSaveDataRequest) getIntent().getSerializableExtra("YwblSaveDataRequest");
        if (ywblSaveDataRequest != null) {
            JZ_YWBL_RHCC_RE request = GsonUtil.deser(ywblSaveDataRequest.getJsonStr(), JZ_YWBL_RHCC_RE.class);
            path = new Gson().fromJson(ywblSaveDataRequest.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
            }.getType());
            paths.addAll(path);
            adapter.setImgPaths(paths);
            adapter.notifyDataSetChanged();
            checkSalvation = new YwblDzdaSalvation();
            checkSalvation.setId(ywblSaveDataRequest.getId().replace("RHCC", ""));
            checkSalvation.setName(ywblSaveDataRequest.getName());
            time.setText(request.countySpotCheckTime);
            dcrTv.setText(request.countySpotCheckUser);
            fzrTv.setText(request.countySpotCheckChargeUser);
            rhccjgEd.setText(request.countySpotCheckResult);
            location = new BDLocation();
            location.setAddrStr(request.coordinate);
        }
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
    protected void receivedLocation(double lng, double lat, BDLocation loc) {
        super.receivedLocation(lng, lat, loc);
        if (loc != null) {
            addressTv.setText(loc.getAddress().address);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1330 && resultCode == 1331) {
            if (data != null) {
                checkSalvation = (YwblDzdaSalvation) data.getSerializableExtra("YwblDzdaSalvation");
                if (checkSalvation != null) {
                    sqrTv.setText(checkSalvation.getName());
                }
            }
        }

    }

    private void doSubmit(boolean isSave) {
        if (checkSalvation == null) {
            doToast("请选择申请人!");
            return;
        }
        String timeStr = time.getText().toString().trim();
        if (TextUtils.isEmpty(timeStr)) {
            doToast("入户抽查时间不能为空!");
            return;
        }
        String dcry = dcrTv.getText().toString().trim();
        if (TextUtils.isEmpty(dcry)) {
            doToast("请填写入户抽查人员!");
            return;
        }
        String fzry = fzrTv.getText().toString().trim();
        if (TextUtils.isEmpty(fzry)) {
            doToast("请填写入户抽查负责人!");
            return;
        }
        String rhccjg = rhccjgEd.getText().toString().trim();
        if (TextUtils.isEmpty(rhccjg)) {
            doToast("请输入入户抽查结果!");
            return;
        }

        if (location == null) {
            doToast("未获取到地址信息!");
            return;
        }

        JZ_YWBL_RHCC_RE request = new JZ_YWBL_RHCC_RE(checkSalvation.getFamilyId(), timeStr, dcry, rhccjg, fzry, location.getAddrStr());
        if (isSave) {
            YwblSaveDataRequest saveData = new YwblSaveDataRequest();
            saveData.setId("RHCC" + checkSalvation.getFamilyId());
            saveData.setJsonStr(GsonUtil.ser(request));
            saveData.setImageJsonStr(GsonUtil.ser(path));
            saveData.setName(checkSalvation.getName());
            saveData.setAddress(location.getAddrStr());
            saveData.setType(3);
            saveData.setUserID(App.getInstance().getLoginUser().getIdJZ());
            try {
                dbManager.saveOrUpdate(saveData);
                doToast("保存成功!");
                finish();
            } catch (DbException e) {
                e.printStackTrace();
                doToast("保存失败");
            }
        } else {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddCountySpot(request);
            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
                @Override
                public void doSuccess(CommResponse result) {

                    for (MyNewPhoto photo : path) {
                        doUpLoadImage(checkSalvation.getFamilyId(), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), location.getAddrStr());
                    }
                    doToast("上报成功!");
                    finish();

                }


            }, true);

            apiUtil.excute();
        }


    }

    private void doUpLoadImage(String familyId, String url, String address) {
        JZ_YWBL_ADDDATA_RE request = new JZ_YWBL_ADDDATA_RE(familyId, "20203031", ImageUtil.pictureToBase64(url), address);
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
