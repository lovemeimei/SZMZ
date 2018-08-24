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

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.szmz.entity.YwblDict;
import com.szmz.entity.YwblDzdaSalvation;
import com.szmz.entity.YwblSaveDataRequest;
import com.szmz.entity.request.JZ_YWBL_ADDDATA_RE;
import com.szmz.entity.request.JZ_YWBL_SPGS_RE;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.DateUtil;
import com.szmz.utils.FileUtil;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.ImageUtil;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.GridViewInScrollView;
import com.szmz.widget.ImageGridAdapter;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import org.xutils.ex.DbException;
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
    TextView gsjgEd;
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
    private YwblDict gsjgDict = null;
    private List<YwblDict> listGsjgDict = new ArrayList<>();

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }

    @Override
    protected void initUI() {
        super.initUI();
        listGsjgDict.add(new YwblDict("30600401", "无异议"));
        listGsjgDict.add(new YwblDict("30600402", "有异议"));
        gsjgEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ActSpgs.this).title("请选择").items(listGsjgDict).itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                        gsjgDict = listGsjgDict.get(position);
                        gsjgEd.setText(gsjgDict.getName());

                    }
                }).show();
            }
        });
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
                intent.putExtra("Type", 5);
                intent.putExtra("isMore", true);
                intent.putExtra("isChose", true);
                startActivityForResult(intent, 1440);
            }
        });
        YwblSaveDataRequest ywblSaveDataRequest = (YwblSaveDataRequest) getIntent().getSerializableExtra("YwblSaveDataRequest");
        if (ywblSaveDataRequest != null) {
            JZ_YWBL_SPGS_RE request = GsonUtil.deser(ywblSaveDataRequest.getJsonStr(), JZ_YWBL_SPGS_RE.class);
            path = new Gson().fromJson(ywblSaveDataRequest.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
            }.getType());
            paths.addAll(path);
            adapter.setImgPaths(paths);
            adapter.notifyDataSetChanged();
            listSalvation = new ArrayList<>();
            String ids = request.familyIds.replace("SHGS", "");
            String[] split = ids.split(",");
            String names = ywblSaveDataRequest.getName();
            String[] splitName = names.split(",");
            for (int i = 0; i < split.length; i++) {
                YwblDzdaSalvation checkSalvation = new YwblDzdaSalvation();
                checkSalvation.setId(split[i]);
                checkSalvation.setName(splitName[i]);
            }

            timeStartTv.setText(request.countyPublicStartTime);
            timeEndTv.setText(request.countyPublicEndTime);
            gsjlrEd.setText(request.countyPublicUser);
            spjdrqTv.setText(request.countyApproveTime);
            for (int i = 0; i < listGsjgDict.size(); i++) {
                if (listGsjgDict.get(i).getCode().equals(request.countyPublicResult)) {
                    gsjgDict = listGsjgDict.get(i);
                    gsjgEd.setText(gsjgDict.getName());
                }
            }

            gsyynrEd.setText(request.countyPublicObjectionInfo);
            spryEd.setText(request.countyApproveUser);
            spfzrEd.setText(request.countyApproveChargeUser);
            location = new BDLocation();
            location.setAddrStr(request.coordinate);
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String str = "经纬度：" + location.getLongitude() + "," + location.getLatitude();
        String[] waterWord = {"拍照人：" + getUser().getUserName(), str, "地址：" + location.getAddrStr(), "时间：" + DateUtil.getCurrentTime2()};
        imagePath = ImageUtil.getWaterImagePath(this, result.getImage().getOriginalPath(), null, waterWord);
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

    private void doSubmit(boolean isSave) {
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
        if (jlr.length() > 10) {
            doToast("公示记录人填写数据过长，不能超过10个字符!");
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
        if (spry.length() > 10) {
            doToast("审批人员填写数据过长，不能超过10个字符!");
            return;
        }
        String spfzr = spfzrEd.getText().toString().trim();
        if (TextUtils.isEmpty(timeEndStr)) {
            doToast("请输入审批负责人!");
            return;
        }
        if (spfzr.length() > 10) {
            doToast("审批负责人填写数据过长，不能超过10个字符!");
            return;
        }
        if (gsjgDict == null) {
            doToast("请选择公示结果!");
            return;
        }
        String gsyynr = gsyynrEd.getText().toString().trim();
        if (TextUtils.isEmpty(gsyynr)) {
            doToast("请输入异议内容!");
            return;
        }
        if (gsyynr.length() > 200) {
            doToast("异议内容填写数据过长，不能超过200个字符!");
            return;
        }
        if (location == null) {
            doToast("未获取到地址信息!");
            return;
        }

        JZ_YWBL_SPGS_RE request = new JZ_YWBL_SPGS_RE(getIDS(listSalvation), timeStartStr, timeEndStr, gsjgDict.getCode(), jlr, gsyynr, location.getAddrStr(), spjdrq, spry, spfzr);
        if (isSave) {
            YwblSaveDataRequest saveData = new YwblSaveDataRequest();
            saveData.setId("SPGS" + getIDS(listSalvation));
            saveData.setJsonStr(GsonUtil.ser(request));
            saveData.setImageJsonStr(GsonUtil.ser(path));
            saveData.setName(getNames(listSalvation));
            saveData.setAddress(location.getAddrStr());
            saveData.setType(5);
            saveData.setUserID(App.getInstance().getLoginUser().getIdJZ());
            try {
                dbManager.saveOrUpdate(saveData);
                doToast("保存成功!");
                finish();
            } catch (DbException e) {
                e.printStackTrace();
                doToast("保存失败!");
            }
        } else {
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


    }

    private void doUpLoadImage(String familyId, String url, String address) {
        JZ_YWBL_ADDDATA_RE request = new JZ_YWBL_ADDDATA_RE(familyId, "20203032", ImageUtil.pictureToBase64(url), address);
        Call<CommResponse> call = App.getApiProxySM().getJZ_AddData(request);
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
