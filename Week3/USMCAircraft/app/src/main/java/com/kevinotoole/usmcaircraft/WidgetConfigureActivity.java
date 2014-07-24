package com.kevinotoole.usmcaircraft;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by kevinotoole on 7/23/14.
 */
public class WidgetConfigureActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_config);

        Button scarletButton = (Button) this.findViewById(R.id.scarletBtn);
        scarletButton.setOnClickListener(this);

        Button goldButton = (Button) this.findViewById(R.id.goldBtn);
        goldButton.setOnClickListener(this);
        
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.scarletBtn:
                Bundle extras = getIntent().getExtras();

                if (extras != null) {

                    int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

                    if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {

                        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);

                        Intent buttonIntent = new Intent(view.getContext(), MyActivity.class);

                        PendingIntent pi = PendingIntent.getActivity(this, 0, buttonIntent, 0);

                        rv.setOnClickPendingIntent(R.id.widgetImage, pi);

                        AppWidgetManager.getInstance(this).updateAppWidget(widgetId, rv);

                        Intent resultValue = new Intent();
                        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                        setResult(RESULT_OK, resultValue);
                        finish();

                    }
                }

                break;

            case R.id.goldBtn:
                Bundle extras1 = getIntent().getExtras();

                if (extras1 != null) {

                    int widgetId = extras1.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

                    if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {

                        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);

                        Intent buttonIntent = new Intent(view.getContext(), MyActivity.class);

                        PendingIntent pi = PendingIntent.getActivity(this, 0, buttonIntent, 0);

                        rv.setOnClickPendingIntent(R.id.widgetImage, pi);

                        AppWidgetManager.getInstance(this).updateAppWidget(widgetId, rv);

                        Intent resultValue = new Intent();
                        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                        setResult(RESULT_OK, resultValue);
                        finish();

                    }
                }

                break;
        }
    }
}
