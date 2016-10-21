package com.moonsister.tcjy.im.widget;


import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.moonsister.tcjy.utils.ActivityUtils;

/**
 * Created by jb on 2016/10/19.
 */

public class ConversationListFragment extends EaseConversationListFragment {

    @Override
    protected void initView() {
        super.initView();
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                HxUserDao dao = new HxUserDao();
                EaseUser user = dao.getUser(conversation.getUserName());
                ActivityUtils.startAppConversationActivity(conversation.getUserName(), user == null ? "" : user.getNick(), user == null ? "" : user.getAvatar());
            }
        });
    }
}
