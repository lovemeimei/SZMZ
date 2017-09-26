package com.szmz.ywbl;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.jph.takephoto.model.TResult;
import com.szmz.R;
import com.szmz.entity.MyNewPhoto;
import com.szmz.utils.FileUtil;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.GridViewInScrollView;
import com.szmz.widget.ImageGridAdapter;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.szmz.utils.UIUtil.doToast;

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

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("民主评议");
        setRightShow("保存");
        setRightVisible(true);
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("保存成功");
                finish();

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
}
