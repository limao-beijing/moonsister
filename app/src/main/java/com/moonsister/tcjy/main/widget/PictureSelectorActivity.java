//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.moonsister.tcjy.main.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moonsister.tcjy.R;
import com.hickey.tool.view.image.AlbumBitmapCacheHelper;
import com.hickey.tool.view.image.CommonUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PictureSelectorActivity extends Activity {
    public static final int REQUEST_PREVIEW = 0;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    private GridView mGridView;
    private ImageButton mBtnBack;
    private Button mBtnSend;
    private PictureSelectorActivity.PicTypeBtn mPicType;
    private PictureSelectorActivity.PreviewBtn mPreviewBtn;
    private View mCatalogView;
    private ListView mCatalogListView;
    private List<PictureSelectorActivity.PicItem> mAllItemList;
    private Map<String, List<PictureSelectorActivity.PicItem>> mItemMap;
    private List<String> mCatalogList;
    private String mCurrentCatalog = "";
    private Uri mTakePictureUri;
    private boolean mSendOrigin = false;
    private int perWidth;
    private int maxPic = 9;

    public PictureSelectorActivity() {
    }

    @TargetApi(23)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(1);
        this.setContentView(R.layout.picsel_activity);
        Intent intent = getIntent();
        if (intent != null) {
            int max = intent.getIntExtra("max", -1);
            if (max > 0)
                maxPic = 9 - max;
        }
        this.mGridView = (GridView) this.findViewById(R.id.gridlist);
        this.mBtnBack = (ImageButton) this.findViewById(R.id.back);
        this.mBtnBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PictureSelectorActivity.this.finish();
            }
        });
        this.mBtnSend = (Button) this.findViewById(R.id.send);
        this.mPicType = (PictureSelectorActivity.PicTypeBtn) this.findViewById(R.id.pic_type);
        this.mPicType.init(this);
        this.mPicType.setEnabled(false);
        this.mPreviewBtn = (PictureSelectorActivity.PreviewBtn) this.findViewById(R.id.preview);
        this.mPreviewBtn.init(this);
        this.mPreviewBtn.setEnabled(false);
        this.mCatalogView = this.findViewById(R.id.catalog_window);
        this.mCatalogListView = (ListView) this.findViewById(R.id.catalog_listview);
        if (VERSION.SDK_INT >= 23) {
            int checkPermission = this.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
            if (checkPermission != 0) {
                if (this.shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE")) {
                    this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 100);
                } else {
                    (new Builder(this)).setMessage("您需要在设置里打开存储空间权限。").setPositiveButton("确认", new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            PictureSelectorActivity.this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 100);
                        }
                    }).setNegativeButton("取消", (android.content.DialogInterface.OnClickListener) null).create().show();
                }

                return;
            }
        }
        this.initView();
    }

    private void initView() {
        this.updatePictureItems();
        this.mGridView.setAdapter(new PictureSelectorActivity.GridViewAdapter());
        this.mGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    ArrayList list = new ArrayList();
                    if (PictureSelectorActivity.this.mCurrentCatalog.isEmpty()) {
                        list.addAll(PictureSelectorActivity.this.mAllItemList);
                    } else {
                        list.addAll((Collection) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCurrentCatalog));
                    }

                    Intent intent = new Intent(PictureSelectorActivity.this, PicturePreviewActivity.class);
                    intent.putExtra("picList", list);
                    intent.putExtra("index", position - 1);
                    intent.putExtra("sendOrigin", PictureSelectorActivity.this.mSendOrigin);
                    PictureSelectorActivity.this.startActivityForResult(intent, 0);
                }
            }
        });
        this.mBtnSend.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent data = new Intent();
                ArrayList list = new ArrayList();
                Iterator i$ = PictureSelectorActivity.this.mItemMap.keySet().iterator();

                while (i$.hasNext()) {
                    String key = (String) i$.next();
                    Iterator i$1 = ((List) PictureSelectorActivity.this.mItemMap.get(key)).iterator();

                    while (i$1.hasNext()) {
                        PictureSelectorActivity.PicItem item = (PictureSelectorActivity.PicItem) i$1.next();
                        if (item.selected) {
                            list.add(Uri.parse("file://" + item.uri));
                        }
                    }
                }

                data.putExtra("sendOrigin", PictureSelectorActivity.this.mSendOrigin);
                data.putExtra("android.intent.extra.RETURN_RESULT", list);
                PictureSelectorActivity.this.setResult(-1, data);
                PictureSelectorActivity.this.finish();
            }
        });
        this.mPicType.setEnabled(true);
        this.mPicType.setTextColor(this.getResources().getColor(R.color.picsel_toolbar_send_text_normal));
        this.mPicType.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PictureSelectorActivity.this.mCatalogView.setVisibility(View.VISIBLE);
            }
        });
        this.mPreviewBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ArrayList list = new ArrayList();
                Iterator intent = PictureSelectorActivity.this.mItemMap.keySet().iterator();

                while (intent.hasNext()) {
                    String key = (String) intent.next();
                    Iterator i$ = ((List) PictureSelectorActivity.this.mItemMap.get(key)).iterator();

                    while (i$.hasNext()) {
                        PictureSelectorActivity.PicItem item = (PictureSelectorActivity.PicItem) i$.next();
                        if (item.selected) {
                            list.add(item);
                        }
                    }
                }

                Intent intent1 = new Intent(PictureSelectorActivity.this, PicturePreviewActivity.class);
                intent1.putExtra("sendOrigin", PictureSelectorActivity.this.mSendOrigin);
                intent1.putExtra("picList", list);
                PictureSelectorActivity.this.startActivityForResult(intent1, 0);
            }
        });
        this.mCatalogView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 1 && PictureSelectorActivity.this.mCatalogView.getVisibility() == View.VISIBLE) {
                    PictureSelectorActivity.this.mCatalogView.setVisibility(View.GONE);
                }

                return true;
            }
        });
        this.mCatalogListView.setAdapter(new PictureSelectorActivity.CatalogAdapter());
        this.mCatalogListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String catalog;
                if (position == 0) {
                    catalog = "";
                } else {
                    catalog = (String) PictureSelectorActivity.this.mCatalogList.get(position - 1);
                }

                if (catalog.equals(PictureSelectorActivity.this.mCurrentCatalog)) {
                    PictureSelectorActivity.this.mCatalogView.setVisibility(View.GONE);
                } else {
                    PictureSelectorActivity.this.mCurrentCatalog = catalog;
                    TextView textView = (TextView) view.findViewById(R.id.name);
                    PictureSelectorActivity.this.mPicType.setText(textView.getText().toString());
                    PictureSelectorActivity.this.mCatalogView.setVisibility(View.GONE);
                    ((PictureSelectorActivity.CatalogAdapter) PictureSelectorActivity.this.mCatalogListView.getAdapter()).notifyDataSetChanged();
                    ((PictureSelectorActivity.GridViewAdapter) PictureSelectorActivity.this.mGridView.getAdapter()).notifyDataSetChanged();
                }
            }
        });
        AlbumBitmapCacheHelper.init(this);
        this.perWidth = (((WindowManager) ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay().getWidth() - CommonUtils.dip2px(this, 4.0F)) / 3;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            if (resultCode == 1) {
                this.setResult(-1, data);
                this.finish();
            } else {
                ArrayList list;
                switch (requestCode) {
                    case 0:
                        this.mSendOrigin = data.getBooleanExtra("sendOrigin", false);
                        list = (ArrayList) data.getSerializableExtra("picList");
                        Iterator item2 = list.iterator();

                        while (item2.hasNext()) {
                            PictureSelectorActivity.PicItem intent1 = (PictureSelectorActivity.PicItem) item2.next();
                            PictureSelectorActivity.PicItem item1 = this.findByUri(intent1.uri);
                            if (item1 != null) {
                                item1.selected = intent1.selected;
                            }
                        }

                        ((PictureSelectorActivity.GridViewAdapter) this.mGridView.getAdapter()).notifyDataSetChanged();
                        ((PictureSelectorActivity.CatalogAdapter) this.mCatalogListView.getAdapter()).notifyDataSetChanged();
                        this.updateToolbar();
                        break;
                    case 1:
                        list = new ArrayList();
                        PictureSelectorActivity.PicItem item = new PictureSelectorActivity.PicItem();
                        item.uri = this.mTakePictureUri.getPath();
                        list.add(item);
                        Intent intent = new Intent(this, PicturePreviewActivity.class);
                        intent.putExtra("picList", list);
                        this.startActivityForResult(intent, 0);
                        MediaScannerConnection.scanFile(this, new String[]{this.mTakePictureUri.getPath()}, (String[]) null, new OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                                PictureSelectorActivity.this.updatePictureItems();
                            }
                        });
                }

            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && this.mCatalogView != null && this.mCatalogView.getVisibility() == View.INVISIBLE) {
            this.mCatalogView.setVisibility(View.GONE);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    protected void requestCamera() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!path.exists()) {
            path.mkdirs();
        }

        String name = System.currentTimeMillis() + ".jpg";
        File file = new File(path, name);
        this.mTakePictureUri = Uri.fromFile(file);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", this.mTakePictureUri);
        this.startActivityForResult(intent, 1);
    }

    private void updatePictureItems() {
        String[] projection = new String[]{"_data", "date_added"};
        String orderBy = "datetaken DESC";
        Cursor cursor = this.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, projection, (String) null, (String[]) null, orderBy);
        this.mAllItemList = new ArrayList();
        this.mCatalogList = new ArrayList();
        this.mItemMap = new ArrayMap();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                PictureSelectorActivity.PicItem item = new PictureSelectorActivity.PicItem();
                item.uri = cursor.getString(0);
                this.mAllItemList.add(item);
                int last = item.uri.lastIndexOf("/");
                String catalog;
                if (last == 0) {
                    catalog = "/";
                } else {
                    int itemList = item.uri.lastIndexOf("/", last - 1);
                    catalog = item.uri.substring(itemList + 1, last);
                }

                if (this.mItemMap.containsKey(catalog)) {
                    ((List) this.mItemMap.get(catalog)).add(item);
                } else {
                    ArrayList itemList1 = new ArrayList();
                    itemList1.add(item);
                    this.mItemMap.put(catalog, itemList1);
                    this.mCatalogList.add(catalog);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    private int getTotalSelectedNum() {
        int sum = 0;
        Iterator i$ = this.mItemMap.keySet().iterator();

        while (i$.hasNext()) {
            String key = (String) i$.next();
            Iterator i$1 = ((List) this.mItemMap.get(key)).iterator();

            while (i$1.hasNext()) {
                PictureSelectorActivity.PicItem item = (PictureSelectorActivity.PicItem) i$1.next();
                if (item.selected) {
                    ++sum;
                }
            }
        }

        return sum;
    }

    private void updateToolbar() {
        int sum = this.getTotalSelectedNum();
        if (sum == 0) {
            this.mBtnSend.setEnabled(false);
            this.mBtnSend.setTextColor(this.getResources().getColor(R.color.picsel_toolbar_send_text_disable));
            this.mBtnSend.setText(R.string.picsel_toolbar_send);
            this.mPreviewBtn.setEnabled(false);
            this.mPreviewBtn.setText(R.string.picsel_toolbar_preview);
        } else if (sum <= 9) {
            this.mBtnSend.setEnabled(true);
            this.mBtnSend.setTextColor(this.getResources().getColor(R.color.picsel_toolbar_send_text_normal));
            this.mBtnSend.setText(String.format(this.getResources().getString(R.string.picsel_toolbar_send_num), new Object[]{Integer.valueOf(sum)}) + maxPic + ")");
            this.mPreviewBtn.setEnabled(true);
            this.mPreviewBtn.setText(String.format(this.getResources().getString(R.string.picsel_toolbar_preview_num), new Object[]{Integer.valueOf(sum)}));
        }

    }

    private PictureSelectorActivity.PicItem getItemAt(int index) {
        int sum = 0;
        Iterator i$ = this.mItemMap.keySet().iterator();

        while (i$.hasNext()) {
            String key = (String) i$.next();

            for (Iterator i$1 = ((List) this.mItemMap.get(key)).iterator(); i$1.hasNext(); ++sum) {
                PictureSelectorActivity.PicItem item = (PictureSelectorActivity.PicItem) i$1.next();
                if (sum == index) {
                    return item;
                }
            }
        }

        return null;
    }

    private PictureSelectorActivity.PicItem getItemAt(String catalog, int index) {
        if (!this.mItemMap.containsKey(catalog)) {
            return null;
        } else {
            int sum = 0;

            for (Iterator i$ = ((List) this.mItemMap.get(catalog)).iterator(); i$.hasNext(); ++sum) {
                PictureSelectorActivity.PicItem item = (PictureSelectorActivity.PicItem) i$.next();
                if (sum == index) {
                    return item;
                }
            }

            return null;
        }
    }

    private PictureSelectorActivity.PicItem findByUri(String uri) {
        Iterator i$ = this.mItemMap.keySet().iterator();

        while (i$.hasNext()) {
            String key = (String) i$.next();
            Iterator i$1 = ((List) this.mItemMap.get(key)).iterator();

            while (i$1.hasNext()) {
                PictureSelectorActivity.PicItem item = (PictureSelectorActivity.PicItem) i$1.next();
                if (item.uri.equals(uri)) {
                    return item;
                }
            }
        }

        return null;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults[0] == 0) {
                    if (permissions[0].equals("android.permission.READ_EXTERNAL_STORAGE")) {
                        this.initView();
                    } else if (permissions[0].equals("android.permission.CAMERA")) {
                        this.requestCamera();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    protected void onDestroy() {
        AlbumBitmapCacheHelper.getInstance().uninit();
        super.onDestroy();
    }

    public static class SelectBox extends ImageView {
        private boolean mIsChecked;

        public SelectBox(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.setImageResource(R.mipmap.select_check_nor);
        }

        public void setChecked(boolean check) {
            this.mIsChecked = check;
            this.setImageResource(this.mIsChecked ? R.mipmap.select_check_sel : R.mipmap.select_check_nor);
        }

        public boolean getChecked() {
            return this.mIsChecked;
        }
    }

    public static class PreviewBtn extends LinearLayout {
        private TextView mText;

        public PreviewBtn(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void init(Activity root) {
            this.mText = (TextView) root.findViewById(R.id.preview_text);
        }

        public void setText(int id) {
            this.mText.setText(id);
        }

        public void setText(String text) {
            this.mText.setText(text);
        }

        public void setEnabled(boolean enabled) {
            super.setEnabled(enabled);
            int color = enabled ? R.color.picsel_toolbar_send_text_normal : R.color.picsel_toolbar_send_text_disable;
            this.mText.setTextColor(this.getResources().getColor(color));
        }

        public boolean onTouchEvent(MotionEvent event) {
            if (this.isEnabled()) {
                switch (event.getAction()) {
                    case 0:
                        this.mText.setVisibility(INVISIBLE);
                        break;
                    case 1:
                        this.mText.setVisibility(VISIBLE);
                }
            }

            return super.onTouchEvent(event);
        }
    }

    public static class PicTypeBtn extends LinearLayout {
        TextView mText;

        public PicTypeBtn(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void init(Activity root) {
            this.mText = (TextView) root.findViewById(R.id.type_text);
        }

        public void setText(String text) {
            this.mText.setText(text);
        }

        public void setTextColor(int color) {
            this.mText.setTextColor(color);
        }

        public boolean onTouchEvent(MotionEvent event) {
            if (this.isEnabled()) {
                switch (event.getAction()) {
                    case 0:
                        this.mText.setVisibility(INVISIBLE);
                        break;
                    case 1:
                        this.mText.setVisibility(VISIBLE);
                }
            }

            return super.onTouchEvent(event);
        }
    }

    public static class PicItem implements Serializable {
        String uri;
        boolean selected;

        public PicItem() {
        }
    }

    private class CatalogAdapter extends BaseAdapter {
        private LayoutInflater mInflater = PictureSelectorActivity.this.getLayoutInflater();

        public CatalogAdapter() {
        }

        public int getCount() {
            return PictureSelectorActivity.this.mItemMap.size() + 1;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            PictureSelectorActivity.CatalogAdapter.ViewHolder holder;
            if (convertView == null) {
                view = this.mInflater.inflate(R.layout.picsel_catalog_listview, parent, false);
                holder = new PictureSelectorActivity.CatalogAdapter.ViewHolder();
                holder.image = (ImageView) view.findViewById(R.id.image);
                holder.name = (TextView) view.findViewById(R.id.name);
                holder.number = (TextView) view.findViewById(R.id.number);
                holder.selected = (ImageView) view.findViewById(R.id.selected);
                view.setTag(holder);
            } else {
                holder = (PictureSelectorActivity.CatalogAdapter.ViewHolder) convertView.getTag();
            }

            String path;
            if (holder.image.getTag() != null) {
                path = (String) holder.image.getTag();
                AlbumBitmapCacheHelper.getInstance().removePathFromShowlist(path);
            }

            int num = 0;
            boolean showSelected = false;
            String name;
            Bitmap bitmap;
            BitmapDrawable bd;
            if (position == 0) {
                if (PictureSelectorActivity.this.mItemMap.size() == 0) {
                    holder.image.setImageResource(R.mipmap.picsel_empty_pic);
                } else {
                    path = ((PictureSelectorActivity.PicItem) ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCatalogList.get(0))).get(0)).uri;
                    AlbumBitmapCacheHelper.getInstance().addPathToShowlist(path);
                    holder.image.setTag(path);
                    bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(path, PictureSelectorActivity.this.perWidth, PictureSelectorActivity.this.perWidth, new AlbumBitmapCacheHelper.ILoadImageCallback() {
                        public void onLoadImageCallBack(Bitmap bitmap, String path1, Object... objects) {
                            if (bitmap != null) {
                                BitmapDrawable bd = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                                View v = PictureSelectorActivity.this.mGridView.findViewWithTag(path1);
                                if (v != null) {
                                    v.setBackgroundDrawable(bd);
                                }

                            }
                        }
                    }, new Object[]{Integer.valueOf(position)});
                    if (bitmap != null) {
                        bd = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                        holder.image.setBackgroundDrawable(bd);
                    } else {
                        holder.image.setBackgroundResource(R.mipmap.grid_image_default);
                    }
                }

                name = PictureSelectorActivity.this.getResources().getString(R.string.picsel_catalog_allpic);
                holder.number.setVisibility(View.GONE);
                showSelected = PictureSelectorActivity.this.mCurrentCatalog.isEmpty();
            } else {
                path = ((PictureSelectorActivity.PicItem) ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCatalogList.get(position - 1))).get(0)).uri;
                name = (String) PictureSelectorActivity.this.mCatalogList.get(position - 1);
                num = ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCatalogList.get(position - 1))).size();
                holder.number.setVisibility(View.VISIBLE);
                showSelected = name.equals(PictureSelectorActivity.this.mCurrentCatalog);
                AlbumBitmapCacheHelper.getInstance().addPathToShowlist(path);
                holder.image.setTag(path);
                bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(path, PictureSelectorActivity.this.perWidth, PictureSelectorActivity.this.perWidth, new AlbumBitmapCacheHelper.ILoadImageCallback() {
                    public void onLoadImageCallBack(Bitmap bitmap, String path1, Object... objects) {
                        if (bitmap != null) {
                            BitmapDrawable bd = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                            View v = PictureSelectorActivity.this.mGridView.findViewWithTag(path1);
                            if (v != null) {
                                v.setBackgroundDrawable(bd);
                            }

                        }
                    }
                }, new Object[]{Integer.valueOf(position)});
                if (bitmap != null) {
                    bd = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                    holder.image.setBackgroundDrawable(bd);
                } else {
                    holder.image.setBackgroundResource(R.mipmap.grid_image_default);
                }
            }

            holder.name.setText(name);
            holder.number.setText(String.format(PictureSelectorActivity.this.getResources().getString(R.string.picsel_catalog_number), new Object[]{Integer.valueOf(num)}));
            holder.selected.setVisibility(showSelected ? View.VISIBLE : View.INVISIBLE);
            return view;
        }

        private class ViewHolder {
            ImageView image;
            TextView name;
            TextView number;
            ImageView selected;

            private ViewHolder() {
            }
        }
    }

    private class GridViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater = PictureSelectorActivity.this.getLayoutInflater();

        public GridViewAdapter() {
        }

        public int getCount() {
            int sum = 1;
            String key;
            if (PictureSelectorActivity.this.mCurrentCatalog.isEmpty()) {
                for (Iterator i$ = PictureSelectorActivity.this.mItemMap.keySet().iterator(); i$.hasNext(); sum += ((List) PictureSelectorActivity.this.mItemMap.get(key)).size()) {
                    key = (String) i$.next();
                }
            } else {
                sum += ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCurrentCatalog)).size();
            }

            return sum;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        @TargetApi(23)
        public View getView(int position, View convertView, ViewGroup parent) {
            if (position == 0) {
                View item1 = this.mInflater.inflate(R.layout.picsel_grid_camera, parent, false);
                ImageButton view1 = (ImageButton) item1.findViewById(R.id.camera_mask);
                view1.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (VERSION.SDK_INT >= 23) {
                            int checkPermission = v.getContext().checkSelfPermission("android.permission.CAMERA");
                            if (checkPermission != 0) {
                                if (PictureSelectorActivity.this.shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
                                    PictureSelectorActivity.this.requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
                                } else {
                                    (new Builder(PictureSelectorActivity.this)).setMessage("您需要在设置里打开相机权限。").setPositiveButton("确认", new android.content.DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            PictureSelectorActivity.this.requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
                                        }
                                    }).setNegativeButton("取消", (android.content.DialogInterface.OnClickListener) null).create().show();
                                }

                                return;
                            }
                        }

                        PictureSelectorActivity.this.requestCamera();
                    }
                });
                return item1;
            } else {
                final PictureSelectorActivity.PicItem item;
                if (PictureSelectorActivity.this.mCurrentCatalog.isEmpty()) {
                    item = (PictureSelectorActivity.PicItem) PictureSelectorActivity.this.mAllItemList.get(position - 1);
                } else {
                    item = PictureSelectorActivity.this.getItemAt(PictureSelectorActivity.this.mCurrentCatalog, position - 1);
                }

                View view = convertView;
                final PictureSelectorActivity.GridViewAdapter.ViewHolder holder;
                if (convertView != null && convertView.getTag() != null) {
                    holder = (PictureSelectorActivity.GridViewAdapter.ViewHolder) convertView.getTag();
                } else {
                    view = this.mInflater.inflate(R.layout.picsel_grid_item, parent, false);
                    holder = new PictureSelectorActivity.GridViewAdapter.ViewHolder();
                    holder.image = (ImageView) view.findViewById(R.id.image);
                    holder.mask = view.findViewById(R.id.mask);
                    holder.checkBox = (PictureSelectorActivity.SelectBox) view.findViewById(R.id.checkbox);
                    view.setTag(holder);
                }

                String path;
                if (holder.image.getTag() != null) {
                    path = (String) holder.image.getTag();
                    AlbumBitmapCacheHelper.getInstance().removePathFromShowlist(path);
                }

                path = item.uri;
                AlbumBitmapCacheHelper.getInstance().addPathToShowlist(path);
                holder.image.setTag(path);
                Bitmap bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(path, PictureSelectorActivity.this.perWidth, PictureSelectorActivity.this.perWidth, new AlbumBitmapCacheHelper.ILoadImageCallback() {
                    public void onLoadImageCallBack(Bitmap bitmap, String path1, Object... objects) {
                        if (bitmap != null) {
                            BitmapDrawable bd = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                            View v = PictureSelectorActivity.this.mGridView.findViewWithTag(path1);
                            if (v != null) {
                                v.setBackgroundDrawable(bd);
                            }

                        }
                    }
                }, new Object[]{Integer.valueOf(position)});
                if (bitmap != null) {
                    BitmapDrawable bd = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                    holder.image.setBackgroundDrawable(bd);
                } else {
                    holder.image.setBackgroundResource(R.mipmap.grid_image_default);
                }

                holder.checkBox.setChecked(item.selected);
                holder.checkBox.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (!holder.checkBox.getChecked() && PictureSelectorActivity.this.getTotalSelectedNum() == maxPic) {
                            Toast.makeText(PictureSelectorActivity.this.getApplicationContext(), R.string.picsel_selected_max, Toast.LENGTH_SHORT).show();
                        } else {
                            holder.checkBox.setChecked(!holder.checkBox.getChecked());
                            item.selected = holder.checkBox.getChecked();
                            if (item.selected) {
                                holder.mask.setBackgroundColor(PictureSelectorActivity.this.getResources().getColor(R.color.picsel_grid_mask_pressed));
                            } else {
                                holder.mask.setBackgroundDrawable(PictureSelectorActivity.this.getResources().getDrawable(R.drawable.sp_grid_mask));
                            }

                            PictureSelectorActivity.this.updateToolbar();
                        }
                    }
                });
                if (item.selected) {
                    holder.mask.setBackgroundColor(PictureSelectorActivity.this.getResources().getColor(R.color.picsel_grid_mask_pressed));
                } else {
                    holder.mask.setBackgroundDrawable(PictureSelectorActivity.this.getResources().getDrawable(R.drawable.sp_grid_mask));
                }

                return view;
            }
        }

        private class ViewHolder {
            ImageView image;
            View mask;
            PictureSelectorActivity.SelectBox checkBox;

            private ViewHolder() {
            }
        }
    }
}
