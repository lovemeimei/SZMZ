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
import com.baidu.location.Poi;
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
import com.szmz.entity.request.JZ_YWBL_MZPY_RE;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    TextView pyjlEd;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.qdTv)
    TextView qdTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.addressLayout)
    LinearLayout addressLayout;
    @BindView(R.id.refreshAddressLayout)
    LinearLayout refreshAddressLayout;
    private List<YwblDict> listPyjl = new ArrayList<>();
    private YwblDict pyjlDict = null;
    private ImageGridAdapter adapter;
    private List<MyNewPhoto> paths = new ArrayList<MyNewPhoto>();
    private List<MyNewPhoto> path = new ArrayList<MyNewPhoto>();
    private String imagePath;
    private double lat1 = 0.0;
    private double lng1 = 0.0;
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

        refreshAddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTv.setText(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
                refreshLocation();
            }
        });
        qdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listSalvation == null) {
                    doToast("请先选择申请人!");
                    return;
                }
                Intent intent = new Intent(context, ActMap.class);
                intent.putExtra("familyId", getIDS(listSalvation));
                intent.putExtra("dicId", "20203029");
                startActivity(intent);
            }
        });
        listPyjl.add(new YwblDict("30600501", "符合"));
        listPyjl.add(new YwblDict("30600502", "不符合"));
        pyjlEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ActMzpy.this).title("请选择").items(listPyjl).itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                        pyjlDict = listPyjl.get(position);
                        pyjlEd.setText(pyjlDict.getName());


                    }
                }).show();
            }
        });
        setLeftVisible(true);
        setTitle("民主评议");
        timeTv.setText(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
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
                intent.putExtra("Type", 2);
                intent.putExtra("isMore", true);
                intent.putExtra("isChose", true);
                startActivityForResult(intent, 1110);
            }
        });
        YwblSaveDataRequest ywblSaveDataRequest = (YwblSaveDataRequest) getIntent().getSerializableExtra("YwblSaveDataRequest");
        if (ywblSaveDataRequest != null) {
            JZ_YWBL_MZPY_RE request = GsonUtil.deser(ywblSaveDataRequest.getJsonStr(), JZ_YWBL_MZPY_RE.class);
            path = new Gson().fromJson(ywblSaveDataRequest.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
            }.getType());
            paths.addAll(path);
            adapter.setImgPaths(paths);
            adapter.notifyDataSetChanged();
            listSalvation = new ArrayList<>();
            String ids = request.familyIds.replace("MZPY", "");
            String[] split = ids.split(",");
            String names = ywblSaveDataRequest.getName();
            String[] splitName = names.split(",");
            for (int i = 0; i < split.length; i++) {
                YwblDzdaSalvation checkSalvation = new YwblDzdaSalvation();
                checkSalvation.setId(split[i]);
                checkSalvation.setName(splitName[i]);
            }

            pysjTv.setText(request.streetCommentTime);
            jlrTv.setText(request.streetCommentUser);
            fzrTv.setText(request.streetCommentChargeUser);
            for (int i = 0; i < listPyjl.size(); i++) {
                if (listPyjl.get(i).getCode().equals(request.streetCommentResult)) {
                    pyjlDict = listPyjl.get(i);
                    pyjlEd.setText(pyjlDict.getName());
                }
            }

            location = new BDLocation();
            location.setAddrStr(request.coordinate);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_mzpy;
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

    private void doSubmit(boolean isSave) {
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
        if (fzr.length() > 10) {
            doToast("负责人填写数据过长，不能超过10个字符!");
            return;
        }
        String jlr = jlrTv.getText().toString().trim();
        if (TextUtils.isEmpty(jlr)) {
            doToast("请填写记录人!");
            return;
        }
        if (jlr.length() > 10) {
            doToast("记录人填写数据过长，不能超过10个字符!");
            return;
        }
        if (pyjlDict == null) {
            doToast("请选择民主评议结论!");
            return;
        }

        if (location == null) {
            doToast("未获取到地址信息!");
            return;
        }

        JZ_YWBL_MZPY_RE request = new JZ_YWBL_MZPY_RE(getIDS(listSalvation), timeStr, pyjlDict.getCode(), fzr, jlr, location.getAddrStr());
        if (isSave) {
            YwblSaveDataRequest saveData = new YwblSaveDataRequest();
            saveData.setId("MZPY" + getIDS(listSalvation));
            saveData.setJsonStr(GsonUtil.ser(request));
            saveData.setImageJsonStr(GsonUtil.ser(path));
            saveData.setName(getNames(listSalvation));
            saveData.setAddress(location.getAddrStr());
            saveData.setType(2);
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
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddStreetComment(request);
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

    @Override
    protected void receivedLocation(double lng, double lat, BDLocation loc) {
        super.receivedLocation(lng, lat, loc);
        if (loc != null) {
            List<Poi> poiList = loc.getPoiList();
            if (poiList != null && poiList.size() > 0) {
                addressTv.setText(loc.getAddress().address + "(" + poiList.get(0).getName() + ")");
            } else {
                addressTv.setText(loc.getAddress().address);
            }


        }
    }
}
