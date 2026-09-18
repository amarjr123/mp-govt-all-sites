package com.amarp123.mpgovtallsites;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    static class Site {
        String name, category, desc, url;
        Site(String n, String c, String d, String u) { name=n; category=c; desc=d; url=u; }
    }

    final ArrayList<Site> sites = new ArrayList<>();
    LinearLayout list;
    EditText search;
    TextView count;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        seed();
        build();
        render("");
    }

    void seed() {
        sites.add(new Site("MP e-Service", "मुख्य पोर्टल", "मध्य प्रदेश की नागरिक सेवाओं का central portal", "https://www.services.mp.gov.in/eservice/"));
        sites.add(new Site("MP e-Seva", "मुख्य पोर्टल", "सेवाएँ, योजनाएँ, आवेदन, ट्रैकिंग और डाउनलोड", "https://eseva.mp.gov.in/"));
        sites.add(new Site("MPOnline", "मुख्य पोर्टल", "ऑनलाइन आवेदन, भर्ती, counselling, university और bill services", "https://www.mponline.gov.in/"));
        sites.add(new Site("MP Government", "मुख्य पोर्टल", "मध्य प्रदेश शासन का मुख्य पोर्टल", "https://mp.gov.in/"));
        sites.add(new Site("Samagra", "Samagra", "Samagra ID और परिवार/सदस्य संबंधी सेवाएँ", "https://samagra.gov.in/"));
        sites.add(new Site("MP Bhulekh", "भूमि एवं राजस्व", "भू-अभिलेख, खसरा, खतौनी आदि", "https://mpbhulekh.gov.in/"));
        sites.add(new Site("MPESB", "सरकारी नौकरी", "भर्ती और परीक्षा संबंधी सेवाएँ", "https://esb.mp.gov.in/"));
        sites.add(new Site("MP Board (MPBSE)", "शिक्षा", "परीक्षा, परिणाम और विद्यार्थी सेवाएँ", "https://www.mpbse.nic.in/"));
        sites.add(new Site("MP Scholarship", "शिक्षा", "छात्रवृत्ति संबंधी सेवाएँ", "https://scholarshipportal.mp.nic.in/"));
        sites.add(new Site("CM Helpline", "शिकायत", "शिकायत, मांग/सुझाव और शिकायत की स्थिति", "https://cmhelpline.mp.gov.in/"));
        sites.add(new Site("MP High Court", "न्याय एवं कानून", "केस, judgement, e-services और court information", "https://mphc.gov.in/"));
        sites.add(new Site("MP Transport", "परिवहन", "परिवहन विभाग की सेवाएँ", "https://transport.mp.gov.in/"));
        sites.add(new Site("MP Police", "पुलिस", "मध्य प्रदेश पुलिस की official website", "https://mppolice.gov.in/"));
        sites.add(new Site("Agriculture Department", "कृषि", "कृषि विभाग और किसान संबंधी जानकारी", "https://agriculture.mp.gov.in/"));
        sites.add(new Site("Education Portal", "शिक्षा", "स्कूल शिक्षा विभाग की सेवाएँ", "https://educationportal.mp.gov.in/"));
        sites.add(new Site("Health Department", "स्वास्थ्य", "स्वास्थ्य विभाग की जानकारी और सेवाएँ", "https://health.mp.gov.in/"));
        sites.add(new Site("Forest Department", "वन", "वन विभाग की official website", "https://forest.mp.gov.in/"));
        sites.add(new Site("Urban Administration", "नगर एवं आवास", "नगरीय प्रशासन एवं विकास विभाग", "https://urban.mp.gov.in/"));
        sites.add(new Site("MP Tourism", "पर्यटन", "मध्य प्रदेश पर्यटन", "https://www.mptourism.com/"));
        sites.add(new Site("MP Public Service Commission", "सरकारी नौकरी", "MPPSC की official website", "https://mppsc.mp.gov.in/"));
    }

    void build() {
        ScrollView scroll = new ScrollView(this);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(16), dp(12), dp(16), dp(24));
        page.setBackgroundColor(Color.rgb(247,249,252));

        TextView title = new TextView(this);
        title.setText("MP Govt All Sites");
        title.setTextSize(26);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(Color.rgb(13,71,161));
        page.addView(title);

        TextView sub = new TextView(this);
        sub.setText("मध्य प्रदेश की सरकारी वेबसाइट्स एक जगह");
        sub.setTextSize(14);
        sub.setTextColor(Color.DKGRAY);
        sub.setPadding(0,0,0,dp(12));
        page.addView(sub);

        search = new EditText(this);
        search.setHint("🔍  सेवा या वेबसाइट खोजें...");
        search.setSingleLine(true);
        search.setBackgroundColor(Color.WHITE);
        search.setPadding(dp(14), 0, dp(14), 0);
        page.addView(search, new LinearLayout.LayoutParams(-1, dp(52)));

        count = new TextView(this);
        count.setTextSize(13);
        count.setTextColor(Color.GRAY);
        count.setPadding(0, dp(10), 0, dp(6));
        page.addView(count);

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        page.addView(list);

        TextView disclaimer = new TextView(this);
        disclaimer.setText("नोट: यह एक independent directory app है। यह मध्य प्रदेश सरकार का official app नहीं है। वेबसाइट्स की सामग्री संबंधित official websites द्वारा नियंत्रित होती है।");
        disclaimer.setTextSize(11);
        disclaimer.setTextColor(Color.GRAY);
        disclaimer.setPadding(dp(4), dp(18), dp(4), 0);
        page.addView(disclaimer);

        scroll.addView(page);
        setContentView(scroll);

        search.addTextChangedListener(new android.text.TextWatcher() {
            public void beforeTextChanged(CharSequence s,int st,int c,int a){}
            public void onTextChanged(CharSequence s,int st,int b,int c){ render(s.toString()); }
            public void afterTextChanged(android.text.Editable e){}
        });
    }

    void render(String q) {
        if (list == null) return;
        list.removeAllViews();
        int shown = 0;
        String x = q.toLowerCase(Locale.ROOT).trim();
        for (Site s : sites) {
            if (!x.isEmpty() && !(s.name+" "+s.category+" "+s.desc).toLowerCase(Locale.ROOT).contains(x)) continue;
            list.addView(card(s));
            shown++;
        }
        count.setText(shown + " websites");
    }

    View card(final Site s) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(14), dp(12), dp(14), dp(12));
        box.setBackgroundColor(Color.WHITE);

        TextView name = new TextView(this);
        name.setText(s.name);
        name.setTextSize(18);
        name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        name.setTextColor(Color.rgb(25,25,25));
        box.addView(name);

        TextView cat = new TextView(this);
        cat.setText(s.category);
        cat.setTextSize(12);
        cat.setTextColor(Color.rgb(21,101,192));
        box.addView(cat);

        TextView desc = new TextView(this);
        desc.setText(s.desc);
        desc.setTextSize(13);
        desc.setTextColor(Color.DKGRAY);
        desc.setPadding(0, dp(4), 0, dp(8));
        box.addView(desc);

        Button open = new Button(this);
        open.setText("Official Website खोलें");
        open.setAllCaps(false);
        open.setOnClickListener(v -> {
            try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s.url))); }
            catch (Exception e) { Toast.makeText(this, "Browser नहीं मिला", Toast.LENGTH_SHORT).show(); }
        });
        box.addView(open);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(0, 0, 0, dp(10));
        box.setLayoutParams(lp);
        return box;
    }

    int dp(int v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
}
