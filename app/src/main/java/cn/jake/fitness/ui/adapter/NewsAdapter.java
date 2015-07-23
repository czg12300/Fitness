package cn.jake.fitness.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.jake.fitness.R;
import cn.jake.fitness.entity.NewsInfo;
import cn.jake.fitness.ui.adapter.base.BaseListAdapter;

public class NewsAdapter extends BaseListAdapter<NewsInfo> {

	public NewsAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.item_news, null);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_content);
			holder.tvLabel = (TextView) convertView.findViewById(R.id.tv_label);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (mDataList.get(position) != null) {
			holder.tvTitle.setText(mDataList.get(position).title);
			holder.tvLabel.setText(mDataList.get(position).label);
		}
		if (needChangeTitle(position)) {
			holder.tvLabel.setVisibility(View.VISIBLE);
		} else {
			holder.tvLabel.setVisibility(View.GONE);
		}
		return convertView;
	}

	private final class ViewHolder {
		public TextView tvLabel;
		public TextView tvTitle;
	}

	protected boolean needChangeTitle(int position) {
		if (position == 0) {
			return true;
		}
		if (position > 1) {
			if (mDataList.get(position - 1) != null && mDataList.get(position) != null) {
				if (!TextUtils.equals(mDataList.get(position - 1).label, mDataList.get(position).label)) {
					return true;
				}
			}
		}
		return false;
	}

}
