package com.baway.liuheng.mywork.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baway.liuheng.mywork.R;
import com.baway.liuheng.mywork.bean.UserBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2017/11/8.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MyAdapter> {
    Context context;
    List<UserBean.SongListEntity> ub;
    boolean all;
    SharedPreferences flag;
    boolean layout;


    public RecyAdapter(Context context, List<UserBean.SongListEntity> ub, boolean all) {
        this.context = context;
        this.ub = ub;
        this.all = all;
        flag=context.getSharedPreferences("flag",Context.MODE_APPEND);
        layout=flag.getBoolean("layout",false);
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rlv_item, null);
        MyAdapter myAdapter = new MyAdapter(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            listener.onItemClick(view, (Integer) view.getTag());
            }
        });
        return myAdapter;
    }
    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        if (layout){
            holder.rlv_cb.setVisibility(View.VISIBLE);
        }else {
            holder.rlv_cb.setVisibility(View.GONE);
        }
        holder.itemView.setTag(position);
        holder.title.setText(ub.get(position).getTitle());
        //Fresco加载图片
        DraweeController controller= Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(ub.get(position).getPic_big()))
                .setAutoPlayAnimations(true)
                .build();
        holder.sdv.setController(controller);
        if (all){
            holder.rlv_cb.setChecked(true);
        }else {
            holder.rlv_cb.setChecked(false);

        }


    }

    @Override
    public int getItemCount() {
        return ub.size();
    }

    class MyAdapter extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView title;
        private final CheckBox rlv_cb;
        public MyAdapter(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv);
            title = itemView.findViewById(R.id.title);
            rlv_cb = itemView.findViewById(R.id.rlv_cb);
        }
    }
}
