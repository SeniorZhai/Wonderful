package com.xgr.wonderful.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;
import com.xgr.wonderful.R;
import com.xgr.wonderful.entity.User;
import com.xgr.wonderful.ui.base.BaseHomeFragment;
import com.xgr.wonderful.utils.ActivityUtil;

public class EditSignFragment extends BaseHomeFragment{

	private Button commit;
	private EditText input;
	
	public static EditSignFragment newInstance(){
		EditSignFragment fragment = new EditSignFragment();
		return fragment;
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.change_sign;
	}

	@Override
	protected void findViews(View view) {
		input = (EditText)view.findViewById(R.id.sign_comment_content);
		commit = (Button)view.findViewById(R.id.sign_comment_commit);
	}

	@Override
	protected void setupViews(Bundle bundle) {
		
	}

	@Override
	protected void setListener() {
		commit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(input.getText().toString().trim())){
					ActivityUtil.show(getActivity(), "请先输入。。。");
				}else{
					updateSign(input.getText().toString().trim());
				}
			}
		});
	}

	@Override
	protected void fetchData() {
		
	}
	
	private void updateSign(String sign){
		User user = BmobUser.getCurrentUser(mContext, User.class);
		if(user != null && sign != null){
			user.setSignature(sign);
			user.update(mContext, new UpdateListener() {
				
				@Override
				public void onSuccess() {
					ActivityUtil.show(getActivity(), "更改信息成功。");
					getActivity().setResult(Activity.RESULT_OK);
					getActivity().finish();
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					ActivityUtil.show(getActivity(), "更改信息失败。请检查网络");
				}
			});
		}
	}

}
