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
import com.szmz.entity.YwblSaveDataRequest;
import com.szmz.entity.request.JZ_YWBL_ADDDATA_RE;
import com.szmz.entity.request.JZ_YWBL_DCHS_RE;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 调查核实页面
 */
public class ActDchs extends ActLocationBase {


    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.dchsqk_ed)
    EditText dchsqkEd;
    @BindView(R.id.dchsjl_ed)
    EditText dchsjlEd;
    @BindView(R.id.bz_ed)
    ClearEditText bzEd;
    @BindView(R.id.mygridview)
    GridViewInScrollView mygridview;
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;
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
    @BindView(R.id.dcrTv)
    EditText dcrTv;
    @BindView(R.id.dcrLayout)
    LinearLayout dcrLayout;
    @BindView(R.id.fzrTv)
    EditText fzrTv;
    @BindView(R.id.fzrLayout)
    LinearLayout fzrLayout;
    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;

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
                intent.setClass(ActDchs.this, ActYwbl_dzda_person.class);
                intent.putExtra("YwblDzdaSalvation", checkSalvation);
                intent.putExtra("Type", 0);
                intent.putExtra("isMore", false);
                intent.putExtra("isChose", true);
                startActivityForResult(intent, 1000);
            }
        });


        setLeftVisible(true);
        setTitle("调查核实");
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
        return R.layout.activity_act_dchs;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
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
            doToast("调查时间不能为空!");
            return;
        }
        String dcry = dcrTv.getText().toString().trim();
        if (TextUtils.isEmpty(dcry)) {
            doToast("请填写调查人!");
            return;
        }
        String fzry = fzrTv.getText().toString().trim();
        if (TextUtils.isEmpty(fzry)) {
            doToast("请填写负责人!");
            return;
        }
        String dchsqk = dchsqkEd.getText().toString().trim();
        if (TextUtils.isEmpty(dchsqk)) {
            doToast("请输入调查核实情况!");
            return;
        }

        String dchsjl = dchsjlEd.getText().toString().trim();
        if (TextUtils.isEmpty(dchsjl)) {
            doToast("请输入调查核实结论!");
            return;
        }
        if (location == null) {
            doToast("未获取到地址信息!");
            return;
        }
        JZ_YWBL_DCHS_RE request = new JZ_YWBL_DCHS_RE(checkSalvation.getFamilyId(), App.getInstance().getLoginUser().getIdJZ(), timeStr, dchsqk, dchsjl, dcry, fzry, location.getAddrStr());
        if (isSave) {
            YwblSaveDataRequest saveData = new YwblSaveDataRequest();
            saveData.setId("DCHS" + checkSalvation.getFamilyId());
            saveData.setJsonStr(GsonUtil.ser(request));
            saveData.setImageJsonStr(GsonUtil.ser(path));
            saveData.setName(checkSalvation.getName());
            saveData.setAddress(location.getAddrStr());
            saveData.setType(1);
            try {
                dbManager.saveOrUpdate(saveData);
                doToast("保存成功!");
                finish();
            } catch (DbException e) {
                e.printStackTrace();
                doToast("保存失败!");
            }

        } else {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddStreetCheck(request);
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
        JZ_YWBL_ADDDATA_RE request = new JZ_YWBL_ADDDATA_RE(familyId, "20203028", ImageUtil.pictureToBase64(url), address);
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

