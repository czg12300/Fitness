package cn.jake.fitness.ui.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import cn.jake.fitness.R;
import cn.jake.fitness.entity.NewsInfo;
import cn.jake.fitness.ui.adapter.NewsAdapter;
import cn.jake.fitness.ui.fragment.base.BaseFitnessFragment;

public class NewsFragment extends BaseFitnessFragment {

	private ListView mLv;
	private NewsAdapter mAdapter;

	@Override
	public View initView() {
		convertView = getLayoutInflater().inflate(R.layout.fragment_news, null);
		mLv = (ListView) convertView;
		mAdapter = new NewsAdapter(getActivity());
		mLv.setAdapter(mAdapter);
		sendUiMessage(obtainUiMessage());
		return convertView;
	}

	public static Fragment newInstance() {
		return newInstance(null);
	}

	public static Fragment newInstance(Bundle bundle) {
		Fragment fragment = new NewsFragment();
		if (bundle != null) {
			fragment.setArguments(bundle);
		}
		return fragment;
	}

	@Override
	public void handleUiMessage(Message msg) {
		ArrayList<NewsInfo> list = new ArrayList<NewsInfo>();
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("�������", "���Ƶ�����"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("��ʳ����", "ˮ������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		list.add(new NewsInfo("����ʶ", "�ߵ�������"));
		mAdapter.addAll(list);
	}
}
