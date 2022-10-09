package eu.alexcuar.oportoatuspies;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String ShowOrHideWebViewInitialUse = "show";
    ProgressBar spinner;
    WebView web_view;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_view =(WebView)findViewById(R.id.oportoatuspies);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        textView = (TextView)findViewById(R.id.textView);
        web_view.setWebViewClient(new CustomWebViewClient());

        web_view.requestFocus();

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        web_view.getSettings().setUseWideViewPort(true);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.loadUrl("https://oportoatuspies.glideapp.io/");
    }
    // This allows for a splash screen
    // (and hide elements once the page loads)
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {

            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(webview.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            ShowOrHideWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);

            view.setVisibility(web_view.VISIBLE);

            super.onPageFinished(view, url);

        }
    }
    @Override
    public void onBackPressed() {
        if (web_view.canGoBack())
            web_view.goBack();
        else
            super.onBackPressed();

    }
}