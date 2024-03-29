package com.toanvu.assimentandroidnangcao.news;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.toanvu.assimentandroidnangcao.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class News extends ListActivity {
    private RSSListAdapter DSTin;
    private TextView TrangThai;
    private android.os.Handler Handler;
    private KETNOI Ketnoi_Rss;
    public static final int DODAI = 500;
    public static final String CHUOI_TIEUDETIN = "chuoi";
    public static final String CHON_TINDOC = "chon";
    public static final String LINK_TIN = "link";
    public static final String UrlText = "https://moet.gov.vn/rss/Pages/index.aspx?ItemID=54";
    public static final String TRANGTHAI = "trangthai";
    ImageView thoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        List<RSSItems> items = new ArrayList<RSSItems>();
        DSTin = new RSSListAdapter(this, items);
        getListView().setAdapter(DSTin);
        TrangThai = (TextView)findViewById(R.id.trangthai);
        thoat = findViewById(R.id.thoatNews);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TaiTrang(UrlText);
        Handler = new Handler();
    }
    private class RSSListAdapter extends ArrayAdapter<RSSItems> {
        private LayoutInflater Inflater;
        public RSSListAdapter(Context context, List<RSSItems> objects) {
            super(context, 0, objects);
            Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TwoLineListItem view;
            if (convertView == null) {
                view = (TwoLineListItem) Inflater.inflate(android.R.layout.simple_list_item_2, null);
            } else {
                view = (TwoLineListItem) convertView;
            }
            RSSItems item = this.getItem(position);
            view.getText1().setText(item.getTitle());
            String description = item.getDescription().toString();
            description = removeTags(description);
            view.getText2().setText(description.substring(0, Math.min(description.length(), DODAI)));
            return view;
        }
    }
    //Xoa cac ki tu //<.*?> va thay the bang ki tu " ".
    public String removeTags(String str) {
        str = str.replaceAll("<.*?>", " ");
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        RSSItems item = DSTin.getItem(position);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink().toString()));
        startActivity(intent);
    }

    public void resetUI() {
        List<RSSItems> items = new ArrayList<RSSItems>();
        DSTin = new RSSListAdapter(this, items);
        getListView().setAdapter(DSTin);
        TrangThai.setText(UrlText);
//        UrlText.requestFocus();
    }

    public synchronized void setCurrentWorker(KETNOI ketnoi) {
        if (Ketnoi_Rss != null) Ketnoi_Rss.interrupt();
        Ketnoi_Rss = ketnoi;
    }

    public synchronized boolean isCurrentWorker(KETNOI ketnoi) {
        return (Ketnoi_Rss == ketnoi);
    }

    private void TaiTrang(CharSequence rssUrl) {
        KETNOI worker = new KETNOI(rssUrl);
        setCurrentWorker(worker);
        resetUI();
        TrangThai.setText("Downloading....");
        worker.start();
    }

    private class ThemTin implements Runnable {
        RSSItems Item;
        ThemTin(RSSItems item) {
            Item = item;
        }

        public void run() {
            DSTin.add(Item);
        }
    }

    private class KETNOI extends Thread {
        private CharSequence Url;
        public KETNOI(CharSequence url) {
            Url = url;
        }

        @Override
        public void run() {
            String trangthai = "";
            try {
                URL url = new URL(Url.toString());
                URLConnection connection = url.openConnection();
                connection.setConnectTimeout(10000);
                connection.connect();
                InputStream in = connection.getInputStream();
                XuLiTinLayVe(in, DSTin);
                trangthai="Ket noi thanh cong: Tin da duoc load ve!\n Tin mới BGD & ĐT";
            } catch (Exception e) {
                trangthai = "Khong tai duoc:" + e.getMessage();
            }
            final String temp = trangthai;
            if (isCurrentWorker(this)) {
                Handler.post(new Runnable() {
                    public void run() {
                        TrangThai.setText(temp);
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "Hoat dong cua BGD")
                .setOnMenuItemClickListener(new TaoMenu("https://moet.gov.vn/rss/Pages/index.aspx?ItemID=56"));
        menu.add(0, 0, 0, "Tin hoat dong cua doan, dang")
                .setOnMenuItemClickListener(new TaoMenu("https://moet.gov.vn/rss/Pages/index.aspx?ItemID=57"));
        menu.add(0, 0, 0, "Tin GDDT dia phuong  ")
                .setOnMenuItemClickListener(new TaoMenu("https://moet.gov.vn/rss/Pages/index.aspx?ItemID=58"));
        menu.add(0, 0, 0, "Tin GDĐT từ các trường TC, CĐ, ĐH")
                .setOnMenuItemClickListener(new TaoMenu("https://moet.gov.vn/rss/Pages/index.aspx?ItemID=59"));
        menu.add(0, 0, 0, "Reset")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        resetUI();
                        return true;
                    }
                });
        return true;
    }

    private class TaoMenu implements MenuItem.OnMenuItemClickListener {
        private CharSequence Url;
        TaoMenu(CharSequence url) {
            Url = url;
        }

        public boolean onMenuItemClick(MenuItem item) {
            UrlText.equals(Url);
//            UrlText.requestFocus();
            return true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int count = DSTin.getCount();
        ArrayList<CharSequence> strings = new ArrayList<CharSequence>();
        for (int i = 0; i < count; i++) {
            RSSItems item = DSTin.getItem(i);
            strings.add(item.getTitle());
            strings.add(item.getLink());
            strings.add(item.getDescription());
        }
        outState.putSerializable(CHUOI_TIEUDETIN, strings);
        if (getListView().hasFocus()) {
            outState.putInt(CHON_TINDOC, Integer.valueOf(getListView().getSelectedItemPosition()));
        }
        outState.putString(LINK_TIN, UrlText);
        outState.putCharSequence(TRANGTHAI, TrangThai.getText());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (state == null) return;
        List<CharSequence> strings = (ArrayList<CharSequence>)state.getSerializable(CHUOI_TIEUDETIN);
        List<RSSItems> items = new ArrayList<RSSItems>();
        for (int i = 0; i < strings.size(); i += 3) {
            items.add(new RSSItems(strings.get(i), strings.get(i + 1), strings.get(i + 2)));
        }
        DSTin = new RSSListAdapter(this, items);
        getListView().setAdapter(DSTin);
        if (state.containsKey(CHON_TINDOC)) {
            getListView().requestFocus(View.FOCUS_FORWARD);
            getListView().setSelection(state.getInt(CHON_TINDOC));
        }
        UrlText.equals(state.getCharSequence(LINK_TIN));
        TrangThai.setText(state.getCharSequence(TRANGTHAI));
    }

    void XuLiTinLayVe(InputStream dauvao, RSSListAdapter dstin) throws IOException, XmlPullParserException {
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(dauvao, null);
        int sukien;
        String title = "";
        String link = "";
        String description = "";
        sukien = xpp.getEventType();
        while (sukien != XmlPullParser.END_DOCUMENT) {
            if (sukien == XmlPullParser.START_TAG) {
                String tag = xpp.getName();
                if (tag.equals("item")) {
                    title = link = description = "";
                } else if (tag.equals("title")) {
                    xpp.next();
                    title = xpp.getText();
                } else if (tag.equals("link")) {
                    xpp.next();
                    link = xpp.getText();
                } else if (tag.equals("description")) {
                    xpp.next();
                    description = xpp.getText();
                }
            } else if (sukien == XmlPullParser.END_TAG) {
                String tag = xpp.getName();
                if (tag.equals("item")) {
                    RSSItems item = new RSSItems(title, link, description);
                    Handler.post(new ThemTin(item));
                }
            }
            sukien = xpp.next();
        }
    }
}
