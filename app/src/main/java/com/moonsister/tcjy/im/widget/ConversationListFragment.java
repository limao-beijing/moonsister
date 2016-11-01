package com.moonsister.tcjy.im.widget;


import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.IMManager;
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

    @Override
    protected void setUpView() {
        super.setUpView();
        registerForContextMenu(conversationListView);
    }

    @Override
    public void onStart() {
        super.onStart();
        IMManager.getInstance().uploadUnreadMsgCountTotal();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = false;
        }
        EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        if (tobeDeleteCons == null) {
            return true;
        }
        if (tobeDeleteCons.getType() == EMConversation.EMConversationType.GroupChat) {
            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.getUserName());
        }
        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.getUserName(), deleteMessage);
//            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
//            inviteMessgeDao.deleteMessage(tobeDeleteCons.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();

        // update unread count
        IMManager.getInstance().uploadUnreadMsgCountTotal();
        return true;
    }

}
