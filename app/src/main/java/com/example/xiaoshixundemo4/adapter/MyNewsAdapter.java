package com.example.xiaoshixundemo4.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaoshixundemo4.R;
import com.example.xiaoshixundemo4.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 李瑞 on 2018/3/6.
 */

public class MyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsBean.ResultBean.DataBean> listdata;
    private Context context;
    public MyNewsAdapter(List<NewsBean.ResultBean.DataBean> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder v=null;
        View view= LayoutInflater.from(context).inflate(R.layout.news,parent,false);
        v=new FirstViewHolder(view);
        return v;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(TextUtils.isEmpty(listdata.get(position).getThumbnail_pic_s02())&&TextUtils.isEmpty(listdata.get(position).getThumbnail_pic_s03())){
            Picasso.with(context).load(listdata.get(position).getThumbnail_pic_s()).into(((FirstViewHolder)holder).image1);
            ((FirstViewHolder)holder).image2.setVisibility(View.GONE);
           ((FirstViewHolder)holder).image3.setVisibility(View.GONE);
            ((FirstViewHolder)holder).title.setText(listdata.get(position).getTitle());
            ((FirstViewHolder)holder).name.setText(listdata.get(position).getAuthor_name());
        }else if(TextUtils.isEmpty(listdata.get(position).getThumbnail_pic_s03())){
            Picasso.with(context).load(listdata.get(position).getThumbnail_pic_s()).into(((FirstViewHolder)holder).image1);
          Picasso.with(context).load(listdata.get(position).getThumbnail_pic_s02()).into(((FirstViewHolder)holder).image2);
           ((FirstViewHolder)holder).image3.setVisibility(View.GONE);
            ((FirstViewHolder)holder).title.setText(listdata.get(position).getTitle());
            ((FirstViewHolder)holder).name.setText(listdata.get(position).getAuthor_name());
        }else{
            Picasso.with(context).load(listdata.get(position).getThumbnail_pic_s()).into(((FirstViewHolder)holder).image1);
            Picasso.with(context).load(listdata.get(position).getThumbnail_pic_s02()).into(((FirstViewHolder)holder).image2);
            Picasso.with(context).load(listdata.get(position).getThumbnail_pic_s03()).into(((FirstViewHolder)holder).image3);
            ((FirstViewHolder)holder).title.setText(listdata.get(position).getTitle());
            ((FirstViewHolder)holder).name.setText(listdata.get(position).getAuthor_name());
        }
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
    public class FirstViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView image1;
        private final ImageView image2;
        private final ImageView image3;
        private final TextView name;

        public FirstViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
            name = itemView.findViewById(R.id.author_name);
        }
    }
}
