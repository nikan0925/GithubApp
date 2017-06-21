package com.nk.githubapp.multitype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nk.githubapp.R;
import com.nk.githubapp.http.entity.GithubUserBean;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/6/21.
 */

public class GithubRepoViewBinder
    extends ItemViewBinder<GithubUserBean, GithubRepoViewBinder.ViewHolder> {

    private Context context;

    public GithubRepoViewBinder(Context context) {
        this.context = context;
    }

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_github_repo, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull ViewHolder holder, @NonNull GithubUserBean item) {

        Glide.with(context).load(item.getAvatar_url()).into(holder.ivAvatar);
        holder.tvUserName.setText(item.getLogin());
        holder.tvUserUrl.setText(item.getUrl());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvUserName;
        private TextView tvUserUrl;


        public ViewHolder(View itemView) {
            super(itemView);

            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvUserUrl = (TextView) itemView.findViewById(R.id.tv_user_url);
        }
    }
}
