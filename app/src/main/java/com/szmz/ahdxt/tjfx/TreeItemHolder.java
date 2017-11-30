package com.szmz.ahdxt.tjfx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.szmz.R;
import com.szmz.entity.HD_XZQH;
import com.szmz.utils.ImageUtil;
import com.unnamed.b.atv.model.TreeNode;

import java.util.List;

/**
 * Created by bz on 2017/10/11.
 */

public class TreeItemHolder extends TreeNode.BaseNodeViewHolder<TreeItemHolder.TreeItem> {
    private TextView tvValue;
    private PrintView arrowView;
    private Context context;
    private OnClickChildListener listener;

    public TreeItemHolder(Context context, OnClickChildListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }


    @Override
    public View createNodeView(final TreeNode node, final TreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.tree_node_item, null, false);
        tvValue = (TextView) view.findViewById(R.id.tv_name);
        tvValue.setText(value.text);
        arrowView = (PrintView) view.findViewById(R.id.arrow_icon);
        TextView button = (TextView) view.findViewById(R.id.chose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.doClick(value.item);
                }
            }
        });
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.rootView);
        layout.setPadding(node.getLevel() * ImageUtil.dip2px(context, 10) + ImageUtil.dip2px(context, 10), 0, 0, 0);
        List<TreeNode> children = node.getChildren();
        if (children == null || children.size() < 1) {
            button.setVisibility(View.GONE);
            arrowView.setVisibility(View.GONE);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        listener.doClick(value.item);
                    }
                }
            });
        } else {
            button.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }

    public static class TreeItem {
        public String text;
        public HD_XZQH item;

        public TreeItem(HD_XZQH item) {
            this.text = item.getAreaName();
            this.item = item;
        }
    }

    public interface OnClickChildListener {
        void doClick(HD_XZQH item);
    }
}

